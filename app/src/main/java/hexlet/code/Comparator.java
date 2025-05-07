package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Objects;
import java.util.TreeSet;
import java.util.HashMap;

public class Comparator {

    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_SAME = "same";
    public static final String STATUS_UPDATE = "updated";

    public static List<Map<String, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key : keys) {
            Map<String, Object> difference = new HashMap<>();
            difference.put("name", key);

            if (!file1.containsKey(key)) {
                difference.put("status", STATUS_ADDED);
                difference.put("oldValue", null);
                difference.put("newValue", file2.get(key));
            } else if (!file2.containsKey(key)) {
                difference.put("status", STATUS_REMOVED);
                difference.put("oldValue", file1.get(key));
                difference.put("newValue", null);
            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                difference.put("status", STATUS_SAME);
                difference.put("oldValue", file1.get(key));
                difference.put("newValue", file2.get(key));
            } else {
                difference.put("status", STATUS_UPDATE);
                difference.put("oldValue", file1.get(key));
                difference.put("newValue", file2.get(key));
            }
            result.add(difference);
        }
        return result;
    }
}
