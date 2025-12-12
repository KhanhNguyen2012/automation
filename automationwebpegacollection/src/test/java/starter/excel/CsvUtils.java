package starter.excel;

import au.com.bytecode.opencsv.CSVWriter;
import io.cucumber.datatable.DataTable;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import starter.constants.PathConstants;

public class CsvUtils {
  public static Path writeDataTableToCsv(DataTable dataTable, String baseFilename) {
    List<Map<String, String>> dataRows = dataTable.asMaps(String.class, String.class);
    if (dataRows.isEmpty()) {
      throw new IllegalArgumentException("DataTable is empty!");
    }

    // Format timestamp
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    // File: <base>_<timestamp>.csv
    String filenameWithTimestamp =
        String.format("%s_%s.csv", baseFilename.replace(".csv", ""), timestamp);

    Path dirPath = Paths.get(PathConstants.FILLED_RESOURCE_PATH);
    try {
      Files.createDirectories(dirPath);
    } catch (IOException e) {
      throw new RuntimeException("Cannot create output directory", e);
    }

    Path outputPath = dirPath.resolve(filenameWithTimestamp);

    try (CSVWriter writer =
        new CSVWriter(
            new FileWriter(outputPath.toFile()),
            CSVWriter.DEFAULT_SEPARATOR,
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END)) {
      // Header
      Set<String> headerSet = dataRows.get(0).keySet();
      String[] headers = headerSet.toArray(new String[0]);
      writer.writeNext(headers);

      // Rows
      for (Map<String, String> row : dataRows) {
        String[] values =
            Arrays.stream(headers)
                .map(
                    key -> {
                      String value = row.getOrDefault(key, "");
                      if ("<current_date>".equalsIgnoreCase(value)) {
                        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
                      }
                      return value;
                    })
                .toArray(String[]::new);
        writer.writeNext(values);
      }

      return outputPath;

    } catch (IOException e) {
      throw new RuntimeException("Error writing CSV file", e);
    }
  }

  public static void deleteFileIfExists(Path path) {
    try {
      if (Files.exists(path)) {
        Files.delete(path);
      }
    } catch (IOException e) {
      System.err.println("❌ Failed to delete file: " + path + ", Reason: " + e.getMessage());
    }
  }
}
