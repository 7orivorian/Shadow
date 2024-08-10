package dev.tori.shadow.option.list;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.tori.shadow.option.ListOption;
import dev.tori.shadow.option.OptionGroup;

import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionGroupList extends ListOption<OptionGroup> {

    public OptionGroupList(String key, List<OptionGroup> value) {
        super(key, value);
    }

    @Override
    public OptionGroup deserializeElement(JsonElement jsonElement) {
        if (!jsonElement.isJsonArray()) {
            throw new IllegalArgumentException("JsonArray required");
        }
        clearList();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        jsonArray.forEach(element -> this.value.add(deserializeElement(element)));
        return null;
    }
}