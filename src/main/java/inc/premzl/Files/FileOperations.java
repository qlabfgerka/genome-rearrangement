package inc.premzl.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperations {
    public static List<Integer> readFile(String path) throws IOException {
        return Files.lines(Paths.get(path)).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
