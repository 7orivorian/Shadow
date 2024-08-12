/*
 * Copyright (c) 2024 7orivorian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.tori.shadow.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;
import dev.tori.shadow.option.list.OptionList;
import dev.tori.shadow.option.number.*;
import dev.tori.shadow.util.OptionHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Options {

    @Nullable
    public static Option<?> ofJson(@NotNull final JsonElement json) {
        if (json.isJsonObject()) {
            return ofJsonObject(generateRandKey(), json.getAsJsonObject());
        } else if (json.isJsonArray()) {
            return ofJsonArray(generateRandKey(), json.getAsJsonArray());
        } else if (json.isJsonPrimitive()) {
            return ofJsonPrimitive(generateRandKey(), json.getAsJsonPrimitive());
        }
        // Failed to parse
        return null;
    }

    @NotNull
    public static OptionGroup ofJsonObject(@Nullable String key, @NotNull JsonObject json) {
        OptionHashMap map = new OptionHashMap();
        for (String k : json.keySet()) {
            JsonElement element = json.get(k);
            if (element.isJsonObject()) {
                map.put(ofJsonObject(k, element.getAsJsonObject()));
            } else if (element.isJsonArray()) {
                map.put(ofJsonArray(k, element.getAsJsonArray()));
            } else if (element.isJsonPrimitive()) {
                map.put(ofJsonPrimitive(k, element.getAsJsonPrimitive()));
            }
        }
        return new OptionGroup(key, map);
    }

    @NotNull
    public static OptionList ofJsonArray(@Nullable String key, @NotNull JsonArray json) {
        OptionHashMap map = new OptionHashMap();
        for (JsonElement element : json) {
            if (element.isJsonObject()) {
                map.put(ofJsonObject(key, element.getAsJsonObject()));
            } else if (element.isJsonArray()) {
                map.put(ofJsonArray(key, element.getAsJsonArray()));
            } else if (element.isJsonPrimitive()) {
                map.put(ofJsonPrimitive(key, element.getAsJsonPrimitive()));
            }
        }
        return new OptionList(key, map);
    }

    @Nullable
    public static Option<?> ofJsonPrimitive(@Nullable String key, @NotNull JsonPrimitive json) {
        if (json.isBoolean()) {
            return new BoolOption(key, json.getAsBoolean());
        } else if (json.isString()) {
            return new StringOption(key, json.getAsString());
        } else if (json.isNumber()) {
            Number number = json.getAsNumber();
            if (number instanceof LazilyParsedNumber lazy) {
                String string = lazy.toString();
                try {
                    int i = Integer.parseInt(string);
                    return new IntOption(key, i);
                } catch (NumberFormatException e0) {
                    try {
                        long l = Long.parseLong(string);
                        return new LongOption(key, l);
                    } catch (NumberFormatException e1) {
                        try {
                            float f = Float.parseFloat(string);
                            return new FloatOption(key, f);
                        } catch (NumberFormatException e2) {
                            try {
                                double d = Double.parseDouble(string);
                                return new DoubleOption(key, d);
                            } catch (NumberFormatException e3) {
                                return new BigDecimalOption(key, new BigDecimal(string));
                            }
                        }
                    }
                }
            }
        }
        // Failed to parse
        return null;
    }

    public static String generateRandKey() {
        return UUID.randomUUID().toString();
    }
}