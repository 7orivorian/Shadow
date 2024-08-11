package dev.tori.shadow.option.number;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import dev.tori.shadow.option.Option;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class NumberOption extends Option<Number> {

    public NumberOption(String key, Number value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        setValue(jsonElement.getAsNumber());
    }

    @Override
    public boolean isValid(Number value) {
        return value != null;
    }
}