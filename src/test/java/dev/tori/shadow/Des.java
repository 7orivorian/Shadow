package dev.tori.shadow;

import dev.tori.shadow.config.Config;
import dev.tori.shadow.serialization.ConfigSerializer;
import dev.tori.shadow.util.OptionHashMap;
import dev.tori.shadow.util.PrettyPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class Des {

    public static void main(String[] args) throws FileNotFoundException {
        MyConfig config = new MyConfig();

        String workingDir = System.getProperty("user.dir");
        File input = new File(new File(workingDir, "output"), "in.json");
        input.getParentFile().mkdirs();

        ConfigSerializer.readToConfig(input, config);

        //System.out.println(config);
        PrettyPrinter.printAsPrettyString(config);
    }

    public static class MyConfig extends Config {

        public MyConfig() {
            addOption("entrypoint", "");
            addOption("mappings", "");
            addOption("handler_folder", (String) null);
            addStringList("plugins", new ArrayList<>(), false);
            addOption("verbose", false);
            addOption("mixins", false);
            addOption("log_output", "");
            addOption("version", "");
            addOption("dependencies", new OptionHashMap(), false);
        }
    }
}