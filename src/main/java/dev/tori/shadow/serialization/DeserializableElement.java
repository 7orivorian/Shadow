package dev.tori.shadow.serialization;

import com.google.gson.JsonElement;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
@FunctionalInterface
public interface DeserializableElement<E> {

    E deserializeElement(JsonElement jsonElement);
}