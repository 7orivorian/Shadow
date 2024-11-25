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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 2.0.0
 */
public class Options {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static Option<Optional<?>> of(String key, Optional<?> value) {
        return new Option<>(key, value);
    }

    public static Option<Boolean> of(String key, boolean value) {
        return new Option<>(key, value);
    }

    public static Option<String> of(String key, String value) {
        return new Option<>(key, value);
    }

    public static Option<Integer> of(String key, int value) {
        return new Option<>(key, value);
    }

    public static Option<Long> of(String key, long value) {
        return new Option<>(key, value);
    }

    public static Option<Float> of(String key, float value) {
        return new Option<>(key, value);
    }

    public static Option<Double> of(String key, double value) {
        return new Option<>(key, value);
    }

    public static Option<BigDecimal> of(String key, BigDecimal value) {
        return new Option<>(key, value);
    }

    public static Option<List<?>> array(String key, List<?> value) {
        return new Option<>(key, value);
    }

    public static Option<List<Option<?>>> of(String key, List<Option<?>> value) {
        return new Option<>(key, value);
    }
}