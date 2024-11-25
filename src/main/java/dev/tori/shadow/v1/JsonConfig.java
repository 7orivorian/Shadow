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

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 2.0.0
 */
public class JsonConfig {

    private final transient @NotNull String name;
    private final @NotNull HashMap<String, Option<?>> options;

    public JsonConfig(@NotNull String name) {
        this(name, new HashMap<>());
    }

    @Contract(pure = true)
    public JsonConfig(@NotNull String name, @NotNull HashMap<String, Option<?>> options) {
        this.name = name;
        this.options = options;
    }

    @NotNull
    public String name() {
        return name;
    }

    @NotNull
    public Map<String, Option<?>> optionMap() {
        return options;
    }

    @NotNull
    public Collection<Option<?>> options() {
        return options.values();
    }

    public void add(@NotNull Option<?> option) {
        options.put(option.key(), option);
    }

    public void remove(@NotNull Option<?> option) {
        this.remove(option.key());
    }

    public void remove(String key) {
        options.remove(key);
    }

    public void addAll(@NotNull List<Option<?>> options) {
        options.forEach(this::add);
    }

    public void clear() {
        options.clear();
    }

    @NotNull
    public JsonObject serialize() {
        return JsonSerializer.serialize(options.values());
    }

    public void deserialize(@NotNull JsonObject json) {
        JsonConfig config = JsonSerializer.deserialize(name, json);
        this.clear();
        this.options.putAll(config.options);
    }
}