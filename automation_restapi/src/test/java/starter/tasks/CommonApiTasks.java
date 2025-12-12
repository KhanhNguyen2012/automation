package starter.tasks;

import static starter.restinteractions.CommonRestInteractions.getRestInteraction;

import java.util.List;
import java.util.Map;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import starter.constants.UrlConstants;
import starter.tasks.api.PrepareHeader;
import starter.tasks.api.VerifyStatusCode;
import vn.com.fecredit.icollect.datatable.model.RequestHeaders;

public class CommonApiTasks {

  public static Task prepareHeader(List<RequestHeaders> requestHeaders) {
    return Task.called("Prepare Request headers")
        .whereTheActorAttemptsTo(new PrepareHeader(requestHeaders))
        .withNoReporting();
  }

  public static Task callApi(
      List<RequestHeaders> requestHeaders,
      String endPoint,
      String method,
      List<Map<String, String>> data) {
    String title =
        String.format(
            "Call api '%s' with method '%s'", UrlConstants.RESTFUL_API_URI + endPoint, method);
    RestInteraction restInteraction = getRestInteraction(requestHeaders, endPoint, method, data);
    return Task.called(title).whereTheActorAttemptsTo(restInteraction).withNoReporting();
  }

  public static Task verifyStatusCode(String statusCodeExpect) {
    String title = "Verify response status is " + statusCodeExpect;
    return Task.called(title)
        .whereTheActorAttemptsTo(new VerifyStatusCode(statusCodeExpect))
        .withNoReporting();
  }
}
