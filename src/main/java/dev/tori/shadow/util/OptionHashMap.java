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