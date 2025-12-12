package starter.tasks.api;

import static starter.constants.UrlConstants.LAST_RESPONSE_KEY;

import io.restassured.response.Response;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

@Getter
@Setter
@AllArgsConstructor
public class VerifyStatusCode implements Task {

  private final String statusCodeExpect;

  @Override
  public <T extends Actor> void performAs(T actor) {
    Response response = actor.recall(LAST_RESPONSE_KEY);
    int actualCode = response.getStatusCode();

    int statusCode =
        statusCodeExpect.contains(",")
            ? Arrays.stream(statusCodeExpect.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(code -> code == actualCode)
                .findFirst()
                .orElse(-1)
            : Integer.parseInt(statusCodeExpect);

    Performable performable = Ensure.that(actualCode).isEqualTo(statusCode);
    actor.attemptsTo(performable);
  }
}
