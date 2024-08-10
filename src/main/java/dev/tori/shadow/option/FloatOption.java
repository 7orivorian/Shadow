package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class BoolOption extends Option<Boolean> {

    public BoolOption(String key, Boolean value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        setValue(jsonElement.getAsBoolean());
    }

    @Override
    public boolean isValid(Boolean value) {
        return value != null;
    }
}