package vn.com.fecredit.icollect.datatable.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReplaceName {
  private static ReplaceName replaceName;
  private static String time;

  private ReplaceName() {}

  private ReplaceName(String time) {
    this.time = time;
  }

  public static ReplaceName getInstance() {
    if (replaceName == null) {
      String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));
      replaceName = new ReplaceName(time);
    }

    return replaceName;
  }

  public static String replaceNameWithDate(String name) {
    ReplaceName.getInstance();
    String result = "";

    if (name.contains("<date>")) {
      result = name.replace("<date>", time);
    } else if (name.contains("<date1>")) {
      result = name.replace("<date1>", time) + "--01";
    } else if (name.contains("<date2>")) {
      result = name.replace("<date2>", time) + "--02";
    } else {
      result = name;
    }

    return result;
  }
}
