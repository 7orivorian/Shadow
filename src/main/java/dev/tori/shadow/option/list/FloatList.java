package dev.tori.shadow.option.list;

import com.google.gson.JsonElement;
import dev.tori.shadow.option.ListOption;

import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class FloatList extends ListOption<Float> {

    public FloatList(String key, List<Float> value) {
        super(key, value);
    }

    @Override
    public Float deserializeElement(JsonElement jsonElement) {
        return jsonElement.getAsFloat();
    }
}