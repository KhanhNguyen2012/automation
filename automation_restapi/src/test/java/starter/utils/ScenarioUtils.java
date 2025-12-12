package starter.utils;

import io.cucumber.java.Scenario;

public class ScenarioUtils {

  public static String getFeatureNameFromUri(Scenario scenario) {
    String uri = scenario.getUri().toString(); // e.g. "file:/path/to/Login.feature"
    String[] parts = uri.split("/");
    return parts[parts.length - 1]; // Returns "Login.feature"
  }
}
