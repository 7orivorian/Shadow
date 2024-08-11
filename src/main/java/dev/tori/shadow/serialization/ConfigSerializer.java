package dev.tori.shadow.serialization;

import com.google.gson.*;
import dev.tori.shadow.config.Config;
import dev.tori.shadow.option.Option;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.function.Consumer;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class ConfigSerializer {

    public static void write(@NotNull Config config, @NotNull File file) {
        write(serialize(config), file);
    }

    public static void write(@NotNull JsonObject json, @NotNull File file) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .setPrettyPrinting()
                .create();
        write(json, file, gson);
    }

    public static void write(@NotNull JsonObject json, @NotNull File file, @NotNull Gson gson) {
        write(json, file, gson, Throwable::printStackTrace);
    }

    public static void write(@NotNull JsonObject json, @NotNull File file, @NotNull Gson gson, @NotNull Consumer<FileNotFoundException> consumer) {
        String content = gson.toJson(json);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
            writer.flush();
        } catch (FileNotFoundException e) {
            consumer.accept(e);
        }
    }

    public static JsonObject serialize(@NotNull Config config) {
        JsonObject json = new JsonObject();
        config.forEachOption(option -> {
            JsonElement serialized = option.serialize();
            if (serialized.isJsonNull()) {
                System.out.println("Serialized " + option.key() + " is JsonNull (" + serialized + ")");
            }
            json.add(option.key(), serialized);
            System.out.println(option);
        });
        return json;
    }

    public static void readToConfig(@NotNull File file, @NotNull Config config) throws FileNotFoundException {
        JsonObject json = read(file);
        config.forEachOption(option -> {
            if (json.has(option.key())) {
                JsonElement element = json.get(option.key());
                if (element.isJsonArray()) {
                    option.deserialize(element.getAsJsonArray());
                } else if (element.isJsonObject()) {
                    option.deserialize(element.getAsJsonObject());
                } else if (element.isJsonPrimitive()) {
                    option.deserialize(element.getAsJsonPrimitive());
                } else if (element.isJsonNull()) {
                    option.deserialize(element.getAsJsonNull());
                }
            }
        });
    }

    public static JsonObject read(@NotNull File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
    }

    public static Config createConfig(JsonObject json) {
        Config cfg = new Config();

        json.keySet().forEach(key -> {
            cfg.addOption(create(json.get(key)));
        });

        return cfg;
    }

    public static Option create(JsonElement element) {
        return null;
    }
}