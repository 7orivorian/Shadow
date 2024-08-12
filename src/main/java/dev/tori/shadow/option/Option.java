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

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public abstract class Option<T> {

    protected final String key;
    protected final T initial;
    protected final boolean fixed;
    protected T value;

    @Contract(pure = true)
    public Option(String key, T value) {
        this(key, value, value);
    }

    @Contract(pure = true)
    public Option(String key, T value, boolean fixed) {
        this(key, value, value, fixed);
    }

    @Contract(pure = true)
    public Option(String key, @Nullable T value, @Nullable T initial) {
        this(key, value, initial, true);
    }

    @Contract(pure = true)
    public Option(String key, @Nullable T value, @Nullable T initial, boolean fixed) {
        this.key = key;
        this.value = value;
        this.initial = initial;
        this.fixed = fixed;
    }

    public abstract JsonElement serialize();

    public abstract void deserialize(JsonElement jsonElement);

    public boolean isValid(@Nullable T value) {
        return true;
    }

    public String key() {
        return this.key;
    }

    public T value() {
        return this.value;
    }

    public boolean setValue(@Nullable T value) {
        if (this.isValid(value)) {
            this.value = value;
            return true;
        }
        return false;
    }

    @Nullable
    public T initial() {
        return this.initial;
    }

    public void reset() {
        this.value = this.initial;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
               "key='" + key + '\'' +
               //", initial=" + initial +
               ", value=" + value +
               '}';
    }
}