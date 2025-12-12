package starter.tasks.string;

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
public class VerifyValueAtPathNotEmpty implements Task {

  private String path;

  @Override
  public <T extends Actor> void performAs(T actor) {
    Performable performable =
        Ensure.that(SerenityRest.lastResponse().jsonPath().getObject(path, String.class))
            .isNotEmpty();
    actor.attemptsTo(performable);
  }
}
