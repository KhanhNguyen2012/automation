package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import starter.constants.UrlConstants;

public class CommonStepDefinitions {

  private Scenario scenario;

  @Before
  public void before(Scenario scenario) {
    this.scenario = scenario;
  }

  @Given("I'm {string}")
  public void iM(String name) {
    OnStage.theActor(name).whoCan(CallAnApi.at(UrlConstants.RESTFUL_API_URI));
  }
}
