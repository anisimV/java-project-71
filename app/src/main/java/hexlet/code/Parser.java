package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String resourcePath) throws Exception {
        ClassLoader classLoader = Parser.class.getClassLoader();
        File file = new File(classLoader.getResource(resourcePath).getFile());

        String content = Files.readString(file.toPath());

        // Определяем формат по расширению файла
        if (resourcePath.endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, Map.class);
        } else if (resourcePath.endsWith(".yml") || resourcePath.endsWith(".yaml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(content, Map.class);
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }
    }
}
