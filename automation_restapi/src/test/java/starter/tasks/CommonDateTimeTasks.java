package starter.tasks;

import net.serenitybdd.screenplay.Task;
import starter.tasks.date_time.VerifyValueAtPathIsCurrentDateAndFormat;

public class CommonDateTimeTasks {

  public static Task verifyValueAtPathIsCurrentDateAndFormat(String path, String format) {
    String title =
        String.format("Verify value as path '%s' is current and format '%s'", path, format);
    return Task.called(title)
        .whereTheActorAttemptsTo(new VerifyValueAtPathIsCurrentDateAndFormat(path, format))
        .withNoReporting();
  }
}
