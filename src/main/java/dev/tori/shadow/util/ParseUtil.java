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

package dev.tori.shadow.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;
import dev.tori.shadow.option.OptionGroup;
import org.jetbrains.annotations.ApiStatus;

import java.math.BigDecimal;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@ApiStatus.Internal
public class ParseUtil {

    public static OptionGroup parseOptionGroupFromJsonElement(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            OptionHashMap map = new OptionHashMap();
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            for (String key : jsonObject.keySet()) {
                JsonElement element = jsonObject.get(key);
                if (element.isJsonPrimitive()) {
                    JsonPrimitive primitive = element.getAsJsonPrimitive();
                    if (primitive.isBoolean()) {
                        map.put(key, primitive.getAsBoolean());
                    } else if (primitive.isString()) {
                        map.put(key, primitive.getAsString());
                    } else if (primitive.isNumber()) {
                        Number number = primitive.getAsNumber();
                        if (number instanceof LazilyParsedNumber lazy) {
                            String string = lazy.toString();
                            try {
                                int i = Integer.parseInt(string);
                                map.put(key, i);
                            } catch (NumberFormatException e0) {
                                try {
                                    long l = Long.parseLong(string);
                                    map.put(key, l);
                                } catch (NumberFormatException e1) {
                                    try {
                                        float f = Float.parseFloat(string);
                                        map.put(key, f);
                                    } catch (NumberFormatException e2) {
                                        try {
                                            double d = Double.parseDouble(string);
                                            map.put(key, d);
                                        } catch (NumberFormatException e3) {
                                            map.put(key, new BigDecimal(string));
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                }
            }
            return new OptionGroup("", map);
        }
        throw new IllegalArgumentException();
    }
}