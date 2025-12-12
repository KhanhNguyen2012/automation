package starter.restinteractions;

import java.io.File;
import java.util.List;
import java.util.Map;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Options;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import org.apache.commons.lang3.StringUtils;
import vn.com.fecredit.icollect.datatable.model.RequestData;
import vn.com.fecredit.icollect.datatable.model.RequestHeaders;
import vn.com.fecredit.icollect.datatable.util.ConverterUtil;
import vn.com.fecredit.icollect.datatable.util.ReplaceName;

public class CommonRestInteractions {

  public static RestInteraction getRestInteraction(
      List<RequestHeaders> requestHeaders,
      String endpoint,
      String method,
      List<Map<String, String>> data) {

    RequestData requestData = null;
    if (!data.isEmpty()) {
      requestData = ConverterUtil.convert(RequestData.class, data).get(0);
    }

    String endpointParams = endpoint;
    if (requestData != null && StringUtils.isNotEmpty(requestData.getParams())) {
      endpointParams = addRequestParams(endpoint, requestData);
    }

    RestInteraction restInteraction = buildRestInteractionByMethod(method, endpointParams);
    if (requestData != null && StringUtils.isNotEmpty(requestData.getBody())) {
      addBody(restInteraction, requestData.getBody());
    }

    addHeaders(restInteraction, requestHeaders);
    return restInteraction;
  }

  public static RestInteraction getRestInteractionToUploadFile(
      List<RequestHeaders> requestHeaders,
      String endPoint,
      String method,
      List<Map<String, String>> rows) {

    RestInteraction restInteraction = buildRestInteractionByMethod(method, endPoint);
    addHeaders(restInteraction, requestHeaders);

    restInteraction.with(
        request -> {
          for (Map<String, String> row : rows) {
            String contentType = row.getOrDefault("content-type", "text/plain");
            boolean isFile = StringUtils.equals(contentType, "file");
            if (isFile) {
              request.multiPart(row.get("fieldName"), new File(row.get("value")));
            } else {
              request.multiPart(row.get("fieldName"), row.get("value"), contentType);
            }
          }
          return request;
        });

    return restInteraction;
  }

  private static String addRequestParams(String endpoint, RequestData requestData) {
    if (!requestData.getParams().isEmpty()) {
      endpoint += "?" + ReplaceName.replaceNameWithDate(requestData.getParams());
    }
    return endpoint;
  }

  private static void addHeaders(
      RestInteraction restInteraction, List<RequestHeaders> requestHeaders) {
    for (RequestHeaders requestHeader : requestHeaders) {
      restInteraction.with(
          request -> request.header(requestHeader.getHeader(), requestHeader.getValue()));
    }
  }

  private static void addBody(RestInteraction restInteraction, String body) {
    restInteraction.with(request -> request.body(body));
  }

  public static RestInteraction buildRestInteractionByMethod(String method, String endpointParams) {
    switch (method) {
      case "GET":
        return Get.resource(endpointParams);
      case "POST":
        return Post.to(endpointParams);
      default:
        return Options.to(endpointParams);
    }
  }
}
