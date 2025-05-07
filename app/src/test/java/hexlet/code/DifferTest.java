package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {
    private static String expectedPlainJson;
    private static String expectedStylishJson;
    private static String expectedJsonJson;
    private static String expectedPlainYml;
    private static String expectedStylishYml;
    private static String expectedJsonYml;


    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedJsonJson = readFixture("json_expected.json");
        expectedPlainJson = readFixture("plain_expected_json.txt");
        expectedStylishJson = readFixture("stylish_expected_json.txt");

        expectedJsonYml = readFixture("json_expected.json");
        expectedPlainYml = readFixture("plain_expected_yml.txt");
        expectedStylishYml = readFixture("stylish_expected_yml.txt");
    }

    private void performTest(String format, String expected, String formatter) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();
        assertThat(Differ.generate(filePath1, filePath2, formatter)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void generateTestForStylishFormatter(String format) throws Exception {
        String expectedStylish = format.equals("json") ? expectedStylishJson : expectedStylishYml;
        performTest(format, expectedStylish, "stylish");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void generateTestForPlainFormatter(String format) throws Exception {
        String expectedStylish = format.equals("json") ? expectedPlainJson : expectedPlainYml;
        performTest(format, expectedStylish, "plain");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void generateTestForJsonFormatter(String format) throws Exception {
        String expectedStylish = format.equals("json") ? expectedJsonJson : expectedJsonYml;
        performTest(format, expectedStylish, "json");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTestWithoutExplicitFormatter(String format) throws Exception {
        String expectedStylish = format.equals("json") ? expectedStylishJson : expectedStylishYml;
        assertThat(Differ.generate(getFixturePath("file1." + format).toString(),
                getFixturePath("file2." + format).toString())).isEqualTo(expectedStylish);
    }
}

