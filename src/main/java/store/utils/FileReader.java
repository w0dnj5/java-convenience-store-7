package store.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    public List<String> readFile(String uri) {
        Path path = Paths.get(uri);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
