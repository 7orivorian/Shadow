package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import dev.tori.shadow.util.OptionHashMap;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionGroup extends Option<OptionHashMap> {

    public OptionGroup(String key, OptionHashMap value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonArray = new JsonObject();
        value.values().forEach(option -> jsonArray.add(option.key(), option.serialize()));
        return jsonArray;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (!jsonElement.isJsonObject()) {
            throw new IllegalArgumentException("Expected JsonArray");
        }
        if (value == null) {
            value = new OptionHashMap();
        }
        JsonObject jsonArray = jsonElement.getAsJsonObject();
        for (Option<?> option : value.values()) {
            option.deserialize(jsonArray.get(option.key()));
        }
    }
}