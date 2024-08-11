package dev.tori.shadow.option.list;

import com.google.gson.JsonElement;

import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class BoolList extends AbstractListOption<Boolean> {

    public BoolList(String key, List<Boolean> value) {
        super(key, value);
    }

    @Override
    public Boolean deserializeElement(JsonElement jsonElement) {
        return jsonElement.getAsBoolean();
    }
}