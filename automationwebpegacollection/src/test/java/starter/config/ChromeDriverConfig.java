package starter.config;

import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import starter.constants.DriverConstants;

public class ChromeDriverConfig implements DriverConfig {
  @Override
  public WebDriver getWebDriver() {
    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("win")) {
      System.setProperty("webdriver.chrome.driver", DriverConstants.WINDOW_CHROME_DRIVER);
    } else if (os.contains("mac")) {
      System.setProperty("webdriver.chrome.driver", DriverConstants.MAC_CHROME_DRIVER);
    }

    ChromeOptions options = new ChromeOptions();
    options.addArguments(
        "--remote-allow-origins=*",
        "--test-type",
        "--no-sandbox",
        "--ignore-certificate-errors",
        "start-maximized",
        "--incognito",
        "--disable-infobars",
        "--disable-gpu",
        "--disable-default-apps",
        "--disable-popup-blocking",
        "--disable-dev-shm-usage",
        "--disable-extensions",
        "--disable-web-security",
        "--disable-translate",
        "--disable-logging",
        "--disable-infobars",
        "--host-resolver-rules=MAP fonts.gstatic.com 127.0.0.1,MAP fonts.googleapis.com 127.0.0.1");
    options.setAcceptInsecureCerts(true);

    return new WebDriverFacade(new ChromeDriver(options), new WebDriverFactory());
  }
}
