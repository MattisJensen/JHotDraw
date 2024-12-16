package org.jhotdraw.draw;

import org.jhotdraw.util.Methods;

public class CloneUtil {
    private CloneUtil() {}

    public static <T> T cloneValue(T value, Class<T> typeToken) throws NoSuchMethodException {
        return value == null ? null : typeToken.cast(Methods.invoke(value, "clone"));
    }
}