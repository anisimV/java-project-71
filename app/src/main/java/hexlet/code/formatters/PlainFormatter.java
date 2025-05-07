package hexlet.code.formatters;

import hexlet.code.Comparator;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> difference : differences) {
            String nodeOutput = processNode(difference);
            if (!nodeOutput.isEmpty()) {
                result.append(nodeOutput).append("\n");
            }
        }
        return result.toString().trim();
    }

    private static String processNode(Map<String, Object> difference) {
        String result = "";
        String name = (String) difference.get("name");
        String status = (String) difference.get("status");
        Object oldValue = difference.get("oldValue");
        Object newValue = difference.get("newValue");

        switch (status) {
            case Comparator.STATUS_UPDATE:
                result = String.format("Property '%s' was updated. From %s to %s", name,
                        formatValue(oldValue), formatValue(newValue));
                break;
            case Comparator.STATUS_REMOVED:
                result = String.format("Property '%s' was removed", name);
                break;
            case Comparator.STATUS_ADDED:
                result = String.format("Property '%s' was added with value: %s", name,
                        formatValue(newValue));
                break;
            default:
                break;
        }
        return result;
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof Boolean || value instanceof Integer) {
            return value.toString();
        }
        return "'" + value + "'";
    }
}
