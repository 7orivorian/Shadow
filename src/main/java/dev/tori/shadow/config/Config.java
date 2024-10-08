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

package dev.tori.shadow.config;

import com.google.gson.JsonElement;
import dev.tori.shadow.option.BoolOption;
import dev.tori.shadow.option.Option;
import dev.tori.shadow.option.OptionGroup;
import dev.tori.shadow.option.StringOption;
import dev.tori.shadow.option.list.*;
import dev.tori.shadow.option.number.*;
import dev.tori.shadow.serialization.DeserializableElement;
import dev.tori.shadow.util.OptionHashMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Config {

    private final OptionHashMap options;

    @Contract(pure = true)
    public Config() {
        this.options = new OptionHashMap();
    }

    public OptionHashMap options() {
        return this.options;
    }

    public Option<?> get(@NotNull String key) {
        return options().get(key);
    }

    public boolean getBool(@NotNull String key) {
        if (options.get(key) instanceof BoolOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a BoolOption");
    }

    public String getString(@NotNull String key) {
        if (options.get(key) instanceof StringOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a StringOption");
    }

    /* Numbers */

    public int getInt(@NotNull String key) {
        if (options.get(key) instanceof IntOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a IntOption");
    }

    public long getLong(@NotNull String key) {
        if (options.get(key) instanceof LongOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a LongOption");
    }

    public float getFloat(@NotNull String key) {
        if (options.get(key) instanceof FloatOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a FloatOption");
    }

    public double getDouble(@NotNull String key) {
        if (options.get(key) instanceof DoubleOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a DoubleOption");
    }

    public Number getNumber(@NotNull String key) {
        if (options.get(key) instanceof NumberOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a NumberOption");
    }

    public BigDecimal getBigDecimal(@NotNull String key) {
        if (options.get(key) instanceof BigDecimalOption o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a BigDecimalOption");
    }

    /* List options */

    public List<Boolean> getBoolList(@NotNull String key) {
        if (options.get(key) instanceof BoolList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a BoolList");
    }

    public List<String> getStringList(@NotNull String key) {
        if (options.get(key) instanceof StringList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a StringList");
    }

    public List<Integer> getIntList(@NotNull String key) {
        if (options.get(key) instanceof IntList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a IntList");
    }

    public List<Long> getLongList(@NotNull String key) {
        if (options.get(key) instanceof LongList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a LongList");
    }

    public List<Float> getFloatList(@NotNull String key) {
        if (options.get(key) instanceof FloatList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a FloatList");
    }

    public List<Double> getDoubleList(@NotNull String key) {
        if (options.get(key) instanceof DoubleList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a DoubleList");
    }

    /* Options with children */

    public OptionHashMap getOptionsInGroup(@NotNull String key) {
        if (options.get(key) instanceof OptionGroup o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a OptionGroup");
    }

    public OptionHashMap getOptionInList(@NotNull String key) {
        if (options.get(key) instanceof OptionList o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a OptionList");
    }

    public OptionHashMap getOptionIn(@NotNull String key) {
        if (options.get(key) instanceof OptionList o) {
            return o.value();
        } else if (options.get(key) instanceof OptionGroup o) {
            return o.value();
        }
        throw new IllegalArgumentException("key " + key + " is not a OptionList or OptionGroup");
    }

    public void forEachOption(BiConsumer<? super String, ? super Option<?>> action) {
        this.options.forEach(action);
    }

    public void clear() {
        this.options.clear();
    }

    public void addOption(final String key, final boolean value) {
        addOption(new BoolOption(key, value));
    }

    public void addOption(final String key, final String value) {
        addOption(new StringOption(key, value));
    }

    public void addOption(final String key, final float value) {
        addOption(new FloatOption(key, value));
    }

    public void addBoolList(final String key, final List<Boolean> value) {
        addOption(new BoolList(key, value));
    }

    public void addDoubleList(final String key, final List<Double> value) {
        addOption(new DoubleList(key, value));
    }

    public void addFloatList(final String key, final List<Float> value) {
        addOption(new FloatList(key, value));
    }

    public void addStringList(final String key, final List<String> value) {
        addStringList(key, value, true);
    }

    public void addStringList(final String key, final List<String> value, final boolean fixed) {
        addOption(new StringList(key, value, fixed));
    }

    public <E> void addOption(final String key, final List<E> value, final DeserializableElement<E> deserializableElement) {
        addOption(new AbstractListOption<>(key, value) {
            @Override
            public E deserializeElement(JsonElement jsonElement) {
                return deserializableElement.deserializeElement(jsonElement);
            }
        });
    }

    public void addOption(final String key, final OptionHashMap value) {
        addOption(new OptionList(key, value));
    }

    public void addOption(final String key, final OptionHashMap value, final boolean fixed) {
        addOption(new OptionList(key, value, fixed));
    }

    public void addOption(final Option<?> option) {
        this.options.put(option);
    }

    public void addOptions(final Option<?>... options) {
        for (Option<?> option : options) {
            addOption(option);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
               "options=" + options +
               '}';
    }
}