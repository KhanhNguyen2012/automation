package starter.tasks.date_time;

import static starter.utils.DateTimeUtil.parseCurrentDateToString;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

@Getter
@Setter
@AllArgsConstructor
public class VerifyValueAtPathIsCurrentDateAndFormat implements Task {

  private String path;
  private String format;

  @Override
  public <T extends Actor> void performAs(T actor) {
    String currentDate = parseCurrentDateToString(format);
    Performable performable =
        Ensure.that(SerenityRest.lastResponse().jsonPath().getObject(path, String.class))
            .isEqualTo(currentDate);
    actor.attemptsTo(performable);
  }
}
