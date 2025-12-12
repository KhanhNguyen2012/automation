package starter.config;

import java.util.Objects;
import org.openqa.selenium.WebDriver;

public class SerenityConfig {
  public static WebDriver getWebDriver() {
    String type = Objects.toString(System.getProperty("driver"), "chrome");
    DriverConfig config;
    switch (type) {
      case "chrome":
        config = new ChromeDriverConfig();
        break;
      case "remote":
      default:
        config = new WebRemoteConfig();
        break;
    }

    return config.getWebDriver();
  }
}
