package starter.stepdefinitions;

import static starter.constants.UrlConstants.*;

import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import java.util.Map;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import org.apache.commons.lang3.StringUtils;

public class ConfigStepDefinitions {

  @BeforeAll
  public static void before_or_after_all() {
    OnStage.setTheStage(Cast.ofStandardActors());
  }

  @After("@AuthenticationUsername")
  public void afterAuthenticationUsernameSuccessfully(Scenario scenario) {
    if (!scenario.isFailed()
        && StringUtils.equals(scenario.getName(), "Authenticate successfully")) {
      Actor actor = OnStage.theActorInTheSpotlight();
      Response response = actor.recall(LAST_RESPONSE_KEY);
      String tokenUsernamePassword =
          response.jsonPath().getObject("response.id_token", String.class);
      actor.remember(TOKEN_USERNAME_KEY, tokenUsernamePassword);
      Map<String, String> bodyRequest = actor.recall(LAST_BODY_REQUEST_KEY);

      actor.remember(USERNAME_KEY, bodyRequest.get("username"));
    }
  }

  @After("@AuthenticationOTP")
  public void afterAuthenticationOTP(Scenario scenario) {
    if (!scenario.isFailed()
        && StringUtils.equals(scenario.getName(), "Authenticate successfully")) {
      Actor actor = OnStage.theActorInTheSpotlight();
      Response response = actor.recall(LAST_RESPONSE_KEY);
      String tokenUsernamePassword =
          response.jsonPath().getObject("response.id_token", String.class);
      actor.remember(TOKEN_VALID_KEY, tokenUsernamePassword);
    }
  }

  @After("@AuthenticationQRCode")
  public void afterAuthenticationQRCode(Scenario scenario) {
    if (!scenario.isFailed()
        && StringUtils.equals(scenario.getName(), "Authenticate successfully")) {
      Actor actor = OnStage.theActorInTheSpotlight();
      Response response = actor.recall(LAST_RESPONSE_KEY);
      String tokenUsernamePassword = response.jsonPath().getObject("data.token", String.class);
      actor.remember(TOKEN_VALID_KEY, tokenUsernamePassword);
    }
  }
}
