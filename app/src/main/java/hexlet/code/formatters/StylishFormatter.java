package hexlet.code.formatters;

import hexlet.code.Comparator;

import java.util.List;
import java.util.StringJoiner;
import java.util.Map;


public class StylishFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");

        for (Map<String, Object> difference : differences) {
            String name = (String) difference.get("name");
            String status = (String) difference.get("status");
            Object oldValue = difference.get("oldValue");
            Object newValue = difference.get("newValue");

            switch (status) {
                case Comparator.STATUS_ADDED:
                    result.add("  + " + name + ": " + newValue);
                    break;
                case Comparator.STATUS_REMOVED:
                    result.add("  - " + name + ": " + oldValue);
                    break;
                case Comparator.STATUS_UPDATE:
                    result.add("  - " + name + ": " + oldValue);
                    result.add("  + " + name + ": " + newValue);
                    break;
                case Comparator.STATUS_SAME:
                    result.add("    " + name + ": " + oldValue);
                    break;
                default:
                    break;
            }
        }
        result.add("}");
        return result.toString();
    }
}
