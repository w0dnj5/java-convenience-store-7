package store.file;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    private static final String DELIMITER = ",";
    private final FileReader fileReader;

    public FileHandler(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public List<Map<String, String>> convert(String uri) {
        List<String> lines = fileReader.readFile(uri);
        List<String> keys = split(lines.getFirst());
        return lines.stream()
                .skip(1)
                .map(line -> toMap(keys, split(line)))
                .toList();
    }

    public Map<String, String> toMap(List<String> keys, List<String> values) {
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    public List<String> split(String line) {
        return Arrays.stream(line.split(DELIMITER)).toList();
    }

}
