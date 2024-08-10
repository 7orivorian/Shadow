package dev.tori.shadow.option.list;

import com.google.gson.JsonElement;
import dev.tori.shadow.option.ListOption;

import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class DoubleList extends ListOption<Double> {

    public DoubleList(String key, List<Double> value) {
        super(key, value);
    }

    @Override
    public Double deserializeElement(JsonElement jsonElement) {
        return jsonElement.getAsDouble();
    }
}