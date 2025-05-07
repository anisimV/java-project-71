package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String format) throws Exception {
        return switch (format) {
            case "json" -> parseJson(content);
            case "yaml", "yml" -> parseYaml(content);
            default -> throw new Exception("Unsupported file format: " + format);
        };
    }

    private static Map<String, Object> parseJson(String content) throws Exception {
        return JSON_MAPPER.readValue(content, new TypeReference<>() {
        });
    }

    private static Map<String, Object> parseYaml(String content) throws Exception {
        return YAML_MAPPER.readValue(content, new TypeReference<>() {
        });
    }
}

