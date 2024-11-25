/*
 * Copyright (c) 2024 7orivorian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.tori.shadow.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 2.0.0
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String workingDir = System.getProperty("user.dir");
        File file = new File(new File(workingDir, "output"), "file.json");
        file.getParentFile().mkdirs();

        JsonConfig serialized = serialize(file);
        JsonConfig deserialized = deserialize(file);

        if (serialized != null && deserialized != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            System.out.println(gson.toJson(deserialized));
        }
    }

    private static JsonConfig serialize(File file) {
        Option<String> stringOption = Options.of("string", "test");
        Option<Boolean> booleanOption = Options.of("bool", false);
        Option<Integer> integerOption = Options.of("int", 10);

        Option<List<Option<?>>> subListOption = Options.of("sub_list", List.of(stringOption, booleanOption));
        Option<List<Option<?>>> emptyListOption = Options.of("empty_list", List.of());
        Option<List<Option<?>>> listOption = Options.of("list", List.of(subListOption, emptyListOption, booleanOption));

        ArrayList<String> strings = new ArrayList<>() {{
            add("test");
            add("test");
            add("test2");
            add("test3");
        }};
        Option<List<?>> array = Options.array("array", strings);

        // Add all options to config
        JsonConfig config = new JsonConfig("MyConfig");
        config.addAll(List.of(booleanOption, listOption, integerOption, array));

        JsonSerializer.write(config, file);
        return config;
    }

    private static JsonConfig deserialize(File file) throws FileNotFoundException {
        // Read Json from file
        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return null;
        }

        JsonObject jsonObject = JsonSerializer.read(file);

        JsonConfig config = JsonSerializer.deserialize("MyConfig", jsonObject);
        System.out.println(config);
        return config;
    }
}