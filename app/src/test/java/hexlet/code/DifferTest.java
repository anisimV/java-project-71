package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String EXPECTED_RESULT = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

    @Test
    void testGenerateFlatJson() throws Exception {
        String file1 = "file1.json";
        String file2 = "file2.json";
        String result = Differ.generate(file1, file2);
        assertEquals(EXPECTED_RESULT.trim(), result.trim());
    }

    @Test
    void testGenerateFlatYaml() throws Exception {
        String file1 = "file1.yml";
        String file2 = "file2.yml";
        String result = Differ.generate(file1, file2);
        assertEquals(EXPECTED_RESULT.trim(), result.trim());
    }
}
