package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);

        // создаем отсортированное множество всех ключей из обоих файлов
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet()); // добавляем ключи из первого файла
        allKeys.addAll(data2.keySet()); // добавляем ключи из второго файла

        // строим строку-результат с помощью StringBuilder
        StringBuilder result = new StringBuilder("{\n");

        // проходим по каждому ключу в алфавитном порядке
        for (String key : allKeys) {
            Object val1 = data1.get(key); // значение из первого файла (может быть null)
            Object val2 = data2.get(key); // значение из второго файла (может быть null)

            // если ключ отсутствует во втором файле - он был удалён
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
            } else if (!data1.containsKey(key)) { // если ключ отсутствует в первом файле - он был добавлен
                result.append("  + ").append(key).append(": ").append(val2).append("\n");
            } else if (Objects.equals(val1, val2)) { // если ключи равны - ключ не изменяется
                result.append("    ").append(key).append(": ").append(val1).append("\n");
            } else { // если ключи разные - ключ был изменён
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
                result.append("  + ").append(key).append(": ").append(val2).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
