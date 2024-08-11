package dev.tori.shadow.option.number;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import dev.tori.shadow.option.Option;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class DoubleOption extends Option<Double> {

    public DoubleOption(String key, Double value) {
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
        setValue(jsonElement.getAsDouble());
    }

    @Override
    public boolean isValid(Double value) {
        return value != null;
    }
}