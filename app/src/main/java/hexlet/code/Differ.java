package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);

        String format1 = getFileType(filepath1);
        String format2 = getFileType(filepath2);

        Map<String, Object> parseFile1 = Parser.parse(content1, format1);
        Map<String, Object> parseFile2 = Parser.parse(content2, format2);

        List<Map<String, Object>> result = Comparator.compare(parseFile1, parseFile2);

        return Formatter.format(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    private static String getFileType(String filePath) {
        String[] pathArray = filePath.split("\\.");
        return pathArray[pathArray.length - 1];
    }
}
