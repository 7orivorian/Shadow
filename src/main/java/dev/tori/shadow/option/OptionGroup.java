package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionGroup extends Option<HashMap<String, Option<?>>> {

    public OptionGroup(String key, HashMap<String, Option<?>> value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        JsonObject jsonObject = new JsonObject();
        value.forEach((key, option) -> jsonObject.add(option.key(), option.serialize()));
        return jsonObject;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (!jsonElement.isJsonObject()) {
            throw new IllegalArgumentException("JsonObject required");
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        jsonObject.keySet().forEach(key -> {
            JsonElement value = jsonObject.get(key);
            this.value.get(key).deserialize(value);
        });
    }
}