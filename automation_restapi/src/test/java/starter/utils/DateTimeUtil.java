package starter.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
  public static String parseCurrentDateToString(String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return formatter.format(LocalDateTime.now());
  }
}
