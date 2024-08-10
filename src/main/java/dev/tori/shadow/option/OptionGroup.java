package dev.tori.shadow.option;

import com.google.gson.JsonElement;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class ParentOption<T> extends Option<Option<T>> {

    public ParentOption(String key, Option<T> value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        return null;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {

    }
}