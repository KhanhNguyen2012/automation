package starter.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderUtil {
  public static List<String> readTxtFile(String filePath, String queryType) throws IOException {
    return Files.lines(Paths.get(filePath))
        .map(String::trim)
        .filter(line -> !line.isEmpty() && line.toUpperCase().contains(queryType.toUpperCase()))
        .collect(Collectors.toList());
  }

  public static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(filePath)) {
      fos.write(bytes);
    }
  }

  public static File createTempFileFromName(String filename) throws IOException {
    String prefix =
        filename.contains(".") ? filename.substring(0, filename.lastIndexOf('.')) : filename;
    String suffix = filename.contains(".") ? filename.substring(filename.lastIndexOf('.')) : null;
    return Files.createTempFile(prefix + "_", suffix).toFile();
  }
}
