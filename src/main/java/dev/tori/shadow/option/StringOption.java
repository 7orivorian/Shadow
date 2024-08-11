package dev.tori.shadow.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class StringOption extends Option<String> {

    public StringOption(String key, @Nullable String value) {
        super(key, value);
    }

    @Override
    public JsonElement serialize() {
        if (value == null) {
            System.out.println("null string value");
            return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(value);
    }

    @Override
    public void deserialize(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            setValue(null);
            return;
        }
        setValue(jsonElement.getAsString());
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public String toString() {
        return "StringOption{" +
               "key='" + key + '\'' +
               //", initial='" + initial + '\'' +
               ", value='" + value + '\'' +
               '}';
    }
}