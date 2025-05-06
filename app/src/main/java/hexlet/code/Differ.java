package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);

        // создаем отсортированое множество всей ключей из обоиъ файлов

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet()); // добавляем ключи из первого файла
        allKeys.addAll(data2.keySet()); // добавляем ключи из второго файла

        // строим строку-результат с помощью StringBuilder
        StringBuilder result = new StringBuilder("{\n");

        // проходим по каждому ключу в алфавитном порядке

        for (String key : allKeys) {
            Object val1 = data1.get(key); // значение из первого файла (может быть null
            Object val2 = data2.get(key); // значение из первого файла (может быть null

            // если ключ отсутствует во втором файле - он был удален
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
                // если ключ отсутствует в первом файле - он был добавлен
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(val2).append("\n");
                // если ключи равны - ключ не изменяется
            } else if (Objects.equals(val1, val2)) {
                result.append("    ").append(key).append(": ").append(val1).append("\n");
            } else {
                // если ключ есть в обоих файлах, но значения разные - ключ был изменен
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
                result.append("  + ").append(key).append(": ").append(val2).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static Map<String, Object> getData(String resourcePath) throws Exception {
        ClassLoader classLoader = Differ.class.getClassLoader();
        File file = new File(classLoader.getResource(resourcePath).getFile());

        String content = Files.readString(file.toPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
