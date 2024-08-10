package dev.tori.shadow.util;

import dev.tori.shadow.option.Option;

import java.util.HashMap;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 1.0.0
 */
public class OptionHashMap extends HashMap<String, Option<?>> {

    public Option<?> put(Option<?> option) {
        return super.put(option.key(), option);
    }
}