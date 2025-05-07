package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> differences, String formatName) throws Exception {
        return switch (formatName) {
            case "plain" -> PlainFormatter.format(differences);
            case "stylish" -> StylishFormatter.format(differences);
            case "json" -> JsonFormatter.format(differences);
            default -> throw new IllegalArgumentException("Unknown formatName: " + formatName);
        };
    }
}
