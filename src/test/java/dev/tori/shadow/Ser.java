package dev.tori.shadow;

import dev.tori.shadow.config.Config;
import dev.tori.shadow.option.OptionGroup;
import dev.tori.shadow.serialization.ConfigSerializer;
import dev.tori.shadow.util.OptionHashMap;
import dev.tori.shadow.util.PrettyPrinter;

import java.io.File;
import java.util.ArrayList;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Ser {

    public static void main(String[] args) {
        Config config = new Config();

        config.addOption("entrypoint", "net.minecraft.client.main.Main");
        config.addOption("mappings", "path to mappings folder where it contains all 3 or folder the url");
        config.addOption("handler_folder", (String) null);
        config.addStringList("plugins", new ArrayList<>());
        config.addOption("verbose", true);
        config.addOption("mixins", true);
        config.addOption("log_output", "location to output logs, aka omnimc logs if its needed");
        config.addOption("version", "1.0.0");
        config.addOption("dependencies", new OptionHashMap(){{
            put(new OptionGroup("", new OptionHashMap(){{
                put("url", "https://assets.omnimc.org/jars.jar");
                put("file", "https://assets.omnimc.org/jars.jar");
            }}));
        }});

        PrettyPrinter.printAsPrettyString(config);

        String workingDir = System.getProperty("user.dir");
        File output = new File(new File(workingDir, "output"), "file.json");
        output.getParentFile().mkdirs();
        ConfigSerializer.write(config, output);
    }
}