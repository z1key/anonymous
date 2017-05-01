package org.z1key.projects.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10.01.2017.
 */
public class Responser {
    public static Map<String, Object> success(Object object) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("data", object);
        return result;
    }

    public static Map<String, Object> successEmpty() {
        Map<String,Object> result = new HashMap<>();
        result.put("status", "success");
        return result;
    }

    public static Map<String, Object> error(String error) {
        Map<String,Object> result = new HashMap<>();
        result.put("status", "error");
        result.put("error", error);
        return result;
    }
}
