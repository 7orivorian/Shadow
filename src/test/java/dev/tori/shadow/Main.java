package dev.tori.shadow;

import com.google.gson.JsonElement;
import dev.tori.shadow.config.Config;
import dev.tori.shadow.option.*;
import dev.tori.shadow.option.list.OptionGroupList;
import dev.tori.shadow.serialization.JsonSerializer;
import dev.tori.shadow.util.OptionHashMap;

import java.io.File;
import java.util.ArrayList;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Main {

    public static void main(String[] args) {
        Config config = new Config();

        config.addOption(new BoolOption("bool", true));
        config.addOption(new StringOption("string", "pider"));
        config.addOption(new ListOption<Boolean>("list_bool", new ArrayList<>() {{
            add(true);
            add(false);
        }}) {
            @Override
            public Boolean deserializeElement(JsonElement jsonElement) {
                return jsonElement.getAsBoolean();
            }
        });
        config.addOption(new ListOption<Float>("list_float", new ArrayList<>() {{
            add(1.2345f);
            add(123.123f);
        }}) {
            @Override
            public Float deserializeElement(JsonElement jsonElement) {
                return jsonElement.getAsFloat();
            }
        });
        config.addOption(new ListOption<String>("list_string", new ArrayList<>() {{
            add("entry0");
            add("entry1");
        }}) {
            @Override
            public String deserializeElement(JsonElement jsonElement) {
                return jsonElement.getAsString();
            }
        });
        config.addOption(new OptionGroup("option_group", new OptionHashMap() {{
            put(new BoolOption("bool", true));
            put(new BoolOption("bool2", false));
            put(new BoolOption("bool3", true));
            put(new StringOption("string", "pider"));
            put(new StringOption("web", "no"));
            put(new StringOption("kil", "no"));
        }}));
        config.addOption(new OptionGroupList("option_group_list", new ArrayList<>() {{
            add(new OptionGroup("bool_group", new OptionHashMap() {{
                put(new BoolOption("bool0", true));
                put(new BoolOption("bool1", false));
                put(new BoolOption("bool2", true));
            }}));
            add(new OptionGroup("string_group", new OptionHashMap() {{
                put(new StringOption("string0", "pider"));
                put(new StringOption("string1", "no"));
                put(new StringOption("string2", "yens"));
            }}));
            add(new OptionGroup("float_group", new OptionHashMap() {{
                put(new FloatOption("float0", 0.0f));
                put(new FloatOption("float1", 123.0f));
                put(new FloatOption("float2", -25.25f));
            }}));
        }}));
        config.addOption(new OptionGroup("wtf", new OptionHashMap() {{
            put(new OptionGroup("wtf_squared", new OptionHashMap() {{
                put(new OptionGroup("wtf_cubed", new OptionHashMap() {{
                    put(new BoolOption("why", true));
                }}));
            }}));
            put(new OptionGroup("wtf_squared1", new OptionHashMap() {{
                put(new BoolOption("bool", false));
            }}));
        }}));

        String workingDir = System.getProperty("user.dir");
        File output = new File(new File(workingDir, "output"), "file.json");
        output.getParentFile().mkdirs();
        JsonSerializer.write(config, output);
    }
}