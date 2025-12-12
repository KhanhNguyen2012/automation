package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import starter.tasks.CommonStringTasks;

public class CommonStepStringDefinitions {

  private Scenario scenario;

  @Before
  public void before(Scenario scenario) {
    this.scenario = scenario;
  }

  @Then("at {string} i'll see the value is {string}")
  public void atILlSeeTheValueIs(String path, String value) {
    OnStage.theActorInTheSpotlight().attemptsTo(CommonStringTasks.verifyValueAtPath(path, value));
  }

  @Then("at {string} i'll see the value is not empty")
  public void atILlSeeTheValueIsNotEmpty(String path) {
    OnStage.theActorInTheSpotlight().attemptsTo(CommonStringTasks.verifyValueAtPathNotEmpty(path));
  }

  @Then("at {string} i'll see the value contain {string}")
  public void atILlSeeTheValueContain(String path, String value) {
    OnStage.theActorInTheSpotlight()
        .attemptsTo(CommonStringTasks.verifyValueAtPathContain(path, value));
  }
}
