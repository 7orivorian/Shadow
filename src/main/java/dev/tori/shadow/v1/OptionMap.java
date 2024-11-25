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

package dev.tori.shadow.v1;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionMap {

    private final HashMap<String, Option<?>> map;

    public OptionMap() {
        this.map = new HashMap<>();
    }

    public static OptionMap of(Collection<Option<?>> options) {
        OptionMap optionMap = new OptionMap();
        options.forEach(optionMap::put);
        return optionMap;
    }

    public void put(Option<?> option) {
        map.put(option.key(), option);
    }

    public HashMap<String, Option<?>> get() {
        return map;
    }

    public Option<?> get(String key) {
        return map.get(key);
    }

    public Option<?> get(int index) {
        return map.values().stream().toList().get(index);
    }

    public void forEach(BiConsumer<String, Option<?>> action) {
        map.forEach(action);
    }

    public Collection<Option<?>> values() {
        return map.values();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}