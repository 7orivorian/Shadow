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
import dev.tori.shadow.option.*;
import dev.tori.shadow.option.list.*;
import dev.tori.shadow.option.number.*;
import dev.tori.shadow.serialization.DeserializableElement;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionHashMap extends HashMap<String, Option<?>> {

    public void put(Option<?> option) {
        super.put(option.key(), option);
    }

    public void put(final String key, final boolean value) {
        put(new BoolOption(key, value));
    }

    public void put(final String key, final String value) {
        put(new StringOption(key, value));
    }

    public void put(final String key, final Number value) {
        put(new NumberOption(key, value));
    }

    public void put(final String key, final int value) {
        put(new IntOption(key, value));
    }

    public void put(final String key, final long value) {
        put(new LongOption(key, value));
    }

    public void put(final String key, final float value) {
        put(new FloatOption(key, value));
    }

    public void put(final String key, final double value) {
        put(new DoubleOption(key, value));
    }

    public void put(final String key, final BigDecimal value) {
        put(new BigDecimalOption(key, value));
    }

    public void putBoolList(final String key, final List<Boolean> value) {
        put(new BoolList(key, value));
    }

    public void putDoubleList(final String key, final List<Double> value) {
        put(new DoubleList(key, value));
    }

    public void putFloatList(final String key, final List<Float> value) {
        put(new FloatList(key, value));
    }

    public void putStringList(final String key, final List<String> value) {
        put(new StringList(key, value));
    }

    public <E> void putList(final String key, final List<E> value, final DeserializableElement<E> deserializableElement) {
        put(new AbstractListOption<E>(key, value) {
            @Override
            public E deserializeElement(JsonElement jsonElement) {
                return deserializableElement.deserializeElement(jsonElement);
            }
        });
    }
}