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

import com.google.gson.*;
import com.google.gson.internal.LazilyParsedNumber;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 2.0.0
 */
public class JsonSerializer {

    @Contract(value = " -> fail", pure = true)
    private JsonSerializer() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    @NotNull
    public static JsonObject serialize(@NotNull Option<Collection<Option<?>>> option) {
        return serialize(option.value());
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static JsonObject serialize(@NotNull Collection<Option<?>> options) {
        JsonObject json = new JsonObject();

        for (Option<?> option : options) {
            if (option.value() instanceof Boolean) {
                json.addProperty(option.key(), (Boolean) option.value());
            } else if (option.value() instanceof String) {
                json.addProperty(option.key(), (String) option.value());
            } else if (option.value() instanceof Integer) {
                json.addProperty(option.key(), (Integer) option.value());
            } else if (option.value() instanceof Float) {
                json.addProperty(option.key(), (Float) option.value());
            } else if (option.value() instanceof List<?> list) {
                JsonElement listJson;
                if (!list.isEmpty()) {
                    Object o = list.get(0);
                    if (o instanceof Option) {
                        listJson = serialize((List<Option<?>>) option.value());
                    } else if (o instanceof String) {
                        listJson = serializeCollection((List<String>) option.value());
                    } else if (o instanceof Boolean) {
                        listJson = serializeCollection((List<Boolean>) option.value());
                    } else if (o instanceof Number) {
                        listJson = serializeCollection((List<Number>) option.value());
                    } else {
                        throw new IllegalArgumentException("Unsupported list content type");
                    }
                    json.add(option.key(), listJson);
                }
            } else {
                throw new IllegalArgumentException("Unsupported type: " + option.value().getClass().getName());
            }
        }
        return json;
    }

    @NotNull
    public static JsonArray serializeCollection(@NotNull Collection<?> list) {
        JsonArray jsonArray = new JsonArray();
        for (Object item : list) {
            if (item instanceof Boolean) {
                jsonArray.add((Boolean) item);
            } else if (item instanceof String) {
                jsonArray.add((String) item);
            } else if (item instanceof Byte) {
                jsonArray.add((Byte) item);
            } else if (item instanceof Short) {
                jsonArray.add((Short) item);
            } else if (item instanceof Integer) {
                jsonArray.add((Integer) item);
            } else if (item instanceof Long) {
                jsonArray.add((Long) item);
            } else if (item instanceof Float) {
                jsonArray.add((Float) item);
            } else if (item instanceof Double) {
                jsonArray.add((Double) item);
            } else if (item instanceof JsonObject) {
                jsonArray.add((JsonObject) item);
            } else if (item instanceof JsonSerializable<?> js) {
                jsonArray.add(js.serialize());
            } else {
                throw new IllegalArgumentException("Unsupported type: " + item.getClass().getName());
            }
        }
        return jsonArray;
    }

    @NotNull
    public static JsonConfig deserialize(@NotNull String name, @NotNull JsonObject json) {
        JsonConfig config = new JsonConfig(name);

        json.asMap().forEach((key, value) -> {
            Option<?> option = null;
            if (value.isJsonObject()) {
                option = Options.of(key, deserializeObject(value.getAsJsonObject()));
            } else if (value.isJsonArray()) {
                option = Options.array(key, deserializeArray(value.getAsJsonArray()));
            } else if (value.isJsonPrimitive()) {
                JsonPrimitive p = value.getAsJsonPrimitive();
                if (p.isBoolean()) {
                    option = Options.of(key, p.getAsBoolean());
                } else if (p.isString()) {
                    option = Options.of(key, p.getAsString());
                } else if (p.isNumber()) {
                    Number number = p.getAsNumber();
                    if (number instanceof LazilyParsedNumber lazy) {
                        String string = lazy.toString();
                        try {
                            int i = Integer.parseInt(string);
                            option = Options.of(key, i);
                        } catch (NumberFormatException e0) {
                            try {
                                long l = Long.parseLong(string);
                                option = Options.of(key, l);
                            } catch (NumberFormatException e1) {
                                try {
                                    float f = Float.parseFloat(string);
                                    option = Options.of(key, f);
                                } catch (NumberFormatException e2) {
                                    try {
                                        double d = Double.parseDouble(string);
                                        option = Options.of(key, d);
                                    } catch (NumberFormatException e3) {
                                        option = Options.of(key, new BigDecimal(string));
                                    }
                                }
                            }
                        }
                    } else {
                        throw new NumberFormatException();
                    }
                }
            } else if (value.isJsonNull()) {
                option = Options.of(key, Optional.empty());
            }

            if (option != null) {
                config.add(option);
            }
        });

        return config;
    }

    @NotNull
    public static List<Option<?>> deserializeObject(@NotNull JsonObject json) {
        List<Option<?>> list = new ArrayList<>();
        json.asMap().forEach((k, v) -> {
            if (v.isJsonObject()) {
                list.add(Options.of(k, deserializeObject(v.getAsJsonObject())));
            } else if (v.isJsonArray()) {
                list.add(Options.array(k, deserializeArray(v.getAsJsonArray())));
            } else if (v.isJsonPrimitive()) {
                JsonPrimitive p = v.getAsJsonPrimitive();
                if (p.isBoolean()) {
                    list.add(Options.of(k, p.getAsBoolean()));
                } else if (p.isString()) {
                    list.add(Options.of(k, p.getAsString()));
                } else if (p.isNumber()) {
                    Number number = p.getAsNumber();
                    if (number instanceof LazilyParsedNumber lazy) {
                        String string = lazy.toString();
                        try {
                            int i = Integer.parseInt(string);
                            list.add(Options.of(k, i));
                        } catch (NumberFormatException e0) {
                            try {
                                long l = Long.parseLong(string);
                                list.add(Options.of(k, l));
                            } catch (NumberFormatException e1) {
                                try {
                                    float f = Float.parseFloat(string);
                                    list.add(Options.of(k, f));
                                } catch (NumberFormatException e2) {
                                    try {
                                        double d = Double.parseDouble(string);
                                        list.add(Options.of(k, d));
                                    } catch (NumberFormatException e3) {
                                        list.add(Options.of(k, new BigDecimal(string)));
                                    }
                                }
                            }
                        }
                    } else {
                        throw new NumberFormatException();
                    }
                }
            }
        });
        return list;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeArray(@NotNull JsonArray json) {
        List<T> list = new ArrayList<>();
        if (!json.isEmpty()) {
            json.forEach(e -> {
                if (e.isJsonObject()) {
                    list.add((T) deserializeObject(e.getAsJsonObject()));
                } else if (e.isJsonArray()) {
                    list.addAll(deserializeArray(e.getAsJsonArray()));
                } else if (e.isJsonPrimitive()) {
                    JsonPrimitive p = e.getAsJsonPrimitive();
                    if (p.isBoolean()) {
                        list.add((T) Boolean.valueOf(p.getAsBoolean()));
                    } else if (p.isString()) {
                        list.add((T) p.getAsString());
                    } else if (p.isNumber()) {
                        Number number = p.getAsNumber();
                        if (number instanceof LazilyParsedNumber lazy) {
                            String string = lazy.toString();
                            try {
                                int i = Integer.parseInt(string);
                                list.add((T) Integer.valueOf(i));
                            } catch (NumberFormatException e0) {
                                try {
                                    long l = Long.parseLong(string);
                                    list.add((T) Long.valueOf(l));
                                } catch (NumberFormatException e1) {
                                    try {
                                        float f = Float.parseFloat(string);
                                        list.add((T) Float.valueOf(f));
                                    } catch (NumberFormatException e2) {
                                        try {
                                            double d = Double.parseDouble(string);
                                            list.add((T) Double.valueOf(d));
                                        } catch (NumberFormatException e3) {
                                            list.add((T) new BigDecimal(string));
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                } else if (e.isJsonNull()) {
                    list.add(null);
                }
            });
        }
        return list;
    }

    public static void write(@NotNull JsonConfig config, @NotNull File file) {
        write(config.serialize(), file);
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

    public static void write(@NotNull JsonObject json, @NotNull File file, @NotNull Gson gson, @NotNull Consumer<FileNotFoundException> exceptionConsumer) {
        String content = gson.toJson(json);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
            writer.flush();
        } catch (FileNotFoundException e) {
            exceptionConsumer.accept(e);
        }
    }

    @NotNull
    public static JsonObject read(@NotNull File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
    }
}