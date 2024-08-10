package dev.tori.shadow.option;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class StringList extends ListOption<String> {

    public StringList(String key, List<String> value) {
        super(key, value);
    }

    @Override
    public String deserializeElement(JsonElement jsonElement) {
        return jsonElement.getAsString();
    }
}