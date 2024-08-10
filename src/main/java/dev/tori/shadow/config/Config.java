package dev.tori.shadow;

import dev.tori.shadow.option.Option;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void addOption(final Option<?> option) {
        this.options.add(option);
    }

    public void addOptions(final Option<?>... options) {
        this.options.addAll(Arrays.asList(options));
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
}