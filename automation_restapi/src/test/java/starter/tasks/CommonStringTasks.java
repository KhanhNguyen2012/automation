package starter.tasks;

import net.serenitybdd.screenplay.Task;
import starter.tasks.string.VerifyValueAtPath;
import starter.tasks.string.VerifyValueAtPathContain;
import starter.tasks.string.VerifyValueAtPathNotEmpty;

public class CommonStringTasks {

  public static Task verifyValueAtPath(String path, String value) {
    String title = String.format("Verify value as path '%s' is equal '%s'", path, value);
    return Task.called(title)
        .whereTheActorAttemptsTo(new VerifyValueAtPath(path, value))
        .withNoReporting();
  }

  public static Task verifyValueAtPathNotEmpty(String path) {
    String title = String.format("Verify value as path '%s' is not empty", path);
    return Task.called(title)
        .whereTheActorAttemptsTo(new VerifyValueAtPathNotEmpty(path))
        .withNoReporting();
  }

  public static Task verifyValueAtPathContain(String path, String value) {
    String title = String.format("Verify value at path '%s' contain '%s'", path, value);
    return Task.called(title)
        .whereTheActorAttemptsTo(new VerifyValueAtPathContain(path, value))
        .withNoReporting();
  }
}
