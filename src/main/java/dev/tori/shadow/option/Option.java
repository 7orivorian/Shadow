package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Contract;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public abstract class Option<T> {

    protected final String key;
    protected final T initial;
    protected T value;

    @Contract(pure = true)
    public Option(String key, T value) {
        this(key, value, value);
    }

    @Contract(pure = true)
    public Option(String key, T value, T initial) {
        this.key = key;
        this.value = value;
        this.initial = initial;
    }

    public abstract JsonElement serialize();

    public abstract void deserialize(JsonElement jsonElement);

    public boolean isValid(T value) {
        return true;
    }

    public String key() {
        return this.key;
    }

    public T value() {
        return this.value;
    }

    public boolean setValue(T value) {
        if (this.isValid(value)) {
            this.value = value;
            return true;
        }
        return false;
    }

    public T initial() {
        return this.initial;
    }

    public void reset() {
        this.value = this.initial;
    }
}