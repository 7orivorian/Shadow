package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class StringOption extends Option<String> {

    public StringOption(String key, String value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        setValue(jsonElement.getAsString());
    }

    @Override
    public boolean isValid(String value) {
        return value != null;
    }
}