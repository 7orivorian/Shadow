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

package dev.tori.shadow.option.list;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import dev.tori.shadow.option.Option;
import dev.tori.shadow.util.OptionHashMap;

import java.util.ArrayList;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionList extends Option<OptionHashMap> {

    public OptionList(String key, OptionHashMap value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        JsonArray jsonArray = new JsonArray();
        value.values().forEach(option -> jsonArray.add(option.serialize()));
        return jsonArray;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (!jsonElement.isJsonArray()) {
            throw new IllegalArgumentException("Expected JsonArray");
        }
        if (value == null) {
            value = new OptionHashMap();
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        ArrayList<Option<?>> options = new ArrayList<>(value.values());
        for (int i = 0; i < options.size(); i++) {
            options.get(i).deserialize(jsonArray.get(i));
        }
    }
}