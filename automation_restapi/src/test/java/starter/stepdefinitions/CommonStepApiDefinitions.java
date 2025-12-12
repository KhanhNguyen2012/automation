package starter.stepdefinitions;

import static starter.constants.UrlConstants.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import starter.tasks.CommonApiTasks;
import starter.utils.AuthenUtil;
import vn.com.fecredit.icollect.datatable.model.RequestHeaders;
import vn.com.fecredit.icollect.datatable.util.ConverterUtil;

public class CommonStepApiDefinitions {

  private Scenario scenario;

  @Before
  public void before(Scenario scenario) {
    this.scenario = scenario;
  }

  @Given("I prepare information of request headers")
  public void iPrepareInformationOfHeaders(List<Map<String, String>> data) {

    List<RequestHeaders> requestHeaders = ConverterUtil.convert(RequestHeaders.class, data);
    Actor actor = OnStage.theActorInTheSpotlight();
    actor.attemptsTo(CommonApiTasks.prepareHeader(requestHeaders));
  }

  @When("I call api {string} and method {string}")
  public void iCallServiceApiByAndMethod(
      String endPoint, String method, List<Map<String, String>> data) {

    Response response = null;
    Actor actor = OnStage.theActorInTheSpotlight();
    String keyActorApi = AuthenUtil.getKeyActorApi(actor, endPoint, method);

    if (actor.recall(keyActorApi) != null) {
      response = actor.recall(keyActorApi);
    } else {
      List<RequestHeaders> requestHeaders = actor.recall(HEADER_LIST_KEY);
      actor.attemptsTo(CommonApiTasks.callApi(requestHeaders, endPoint, method, data));
      response = SerenityRest.lastResponse();
      actor.remember(keyActorApi, response);
    }

    ObjectMapper mapper = new ObjectMapper();
    try {
      Map body = mapper.readValue(data.get(0).get("body"), Map.class);
      actor.remember(LAST_BODY_REQUEST_KEY, body);
    } catch (Exception e) {

    }
    actor.remember(LAST_RESPONSE_KEY, response);
  }

  @Then("the response status is {string}")
  public void theResponseStatusIs(String statusCodeExpect) {
    OnStage.theActorInTheSpotlight().attemptsTo(CommonApiTasks.verifyStatusCode(statusCodeExpect));
  }
}
