package dev.tori.shadow.option.list;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import dev.tori.shadow.option.Option;
import dev.tori.shadow.serialization.DeserializableElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public abstract class AbstractListOption<E> extends Option<List<E>> implements DeserializableElement<E> {

    public AbstractListOption(String key, List<E> value) {
        super(key, value, new ArrayList<>(value));
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        JsonArray jsonArray = new JsonArray();
        value.forEach(element -> {
            if (element instanceof Boolean e) {
                jsonArray.add(e);
            } else if (element instanceof Number e) {
                jsonArray.add(e);
            } else if (element instanceof Character e) {
                jsonArray.add(e);
            } else if (element instanceof String e) {
                jsonArray.add(e);
            }
        });
        return jsonArray;
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (!jsonElement.isJsonArray()) {
            throw new IllegalArgumentException("JsonArray required");
        }
        clearList();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        jsonArray.forEach(element -> this.value.add(deserializeElement(element)));
    }

    @Override
    public boolean isValid(List<E> value) {
        return super.isValid(value);
    }

    @Override
    public void reset() {
        this.value.clear();
        this.value.addAll(initial);
    }

    public void add(E element) {
        this.value.add(element);
    }

    public void addAll(List<E> elements) {
        this.value.addAll(elements);
    }

    public void remove(E element) {
        this.value.remove(element);
    }

    public void remove(int index) {
        this.value.remove(index);
    }

    public void clearList() {
        this.value.clear();
    }
}