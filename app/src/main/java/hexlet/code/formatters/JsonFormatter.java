package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String format(List<Map<String, Object>> differences) throws Exception {
        return OBJECT_MAPPER.writeValueAsString(differences);
    }
}
