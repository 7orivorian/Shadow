package dev.tori.shadow.option.list;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import dev.tori.shadow.option.Option;
import dev.tori.shadow.util.OptionHashMap;

import java.util.ArrayList;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionList extends Option<OptionHashMap> {

    public OptionList(String key, OptionHashMap value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        JsonArray jsonArray = new JsonArray();
        value.values().forEach(option -> jsonArray.add(option.serialize()));
        return jsonArray;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (!jsonElement.isJsonArray()) {
            throw new IllegalArgumentException("Expected JsonArray");
        }
        if (value == null) {
            value = new OptionHashMap();
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        ArrayList<Option<?>> options = new ArrayList<>(value.values());
        for (int i = 0; i < options.size(); i++) {
            options.get(i).deserialize(jsonArray.get(i));
        }
    }
}