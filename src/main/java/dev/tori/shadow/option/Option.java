package dev.tori.shadow;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public abstract class Option<T> {

    private final T initial;
    private T value;

    public Option(T value) {
        this.initial = value;
        this.value = initial;
    }

    public T value() {
        return value;
    }

    public void value(T value) {
        this.value = value;
    }

    public T initial() {
        return initial;
    }

    public void reset() {
        value = initial;
    }
}