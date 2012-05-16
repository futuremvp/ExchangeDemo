package au.com.realestate.features.support;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static Map map = new HashMap();

    public static void put(String key, Object object) {
        map.put(key, object);
    }

    public static Object get(String key) {
        return map.get(key);
    }
}
