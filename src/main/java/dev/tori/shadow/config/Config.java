package dev.tori.shadow.config;

import com.google.gson.JsonElement;
import dev.tori.shadow.option.*;
import dev.tori.shadow.option.list.*;
import dev.tori.shadow.option.number.FloatOption;
import dev.tori.shadow.serialization.DeserializableElement;
import dev.tori.shadow.util.OptionHashMap;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Config {

    private final ArrayList<Option<?>> options;

    @Contract(pure = true)
    public Config() {
        this.options = new ArrayList<>();
    }

    public ArrayList<Option<?>> options() {
        return this.options;
    }

    public void forEachOption(final Consumer<Option<?>> consumer) {
        this.options.forEach(consumer);
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
        addOption(new StringList(key, value));
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

    public void addOption(final Option<?> option) {
        this.options.add(option);
    }

    public void addOptions(final Option<?>... options) {
        this.options.addAll(Arrays.asList(options));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
               "options=" + options +
               '}';
    }
}