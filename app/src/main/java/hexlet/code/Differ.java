package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        return "File1: " + data1 + "\nFile2: " + data2;
    }

    private static Map<String, Object> getData(String resourcePath) throws Exception {
        ClassLoader classLoader = Differ.class.getClassLoader();
        File file = new File(classLoader.getResource(resourcePath).getFile());

        String content = Files.readString(file.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}

