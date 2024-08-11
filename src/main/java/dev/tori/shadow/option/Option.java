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
    @Nullable
    protected final T initial;
    @Nullable
    protected T value;

    @Contract(pure = true)
    public Option(String key, T value) {
        this(key, value, value);
    }

    @Contract(pure = true)
    public Option(String key, @Nullable T value, @Nullable T initial) {
        this.key = key;
        this.value = value;
        this.initial = initial;
    }

    public abstract JsonElement serialize();

    public abstract void deserialize(JsonElement jsonElement);

    public boolean isValid(@Nullable T value) {
        return true;
    }

    public String key() {
        return this.key;
    }

    @Nullable
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