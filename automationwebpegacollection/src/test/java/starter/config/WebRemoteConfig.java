package starter.config;

import java.net.URL;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebRemoteConfig implements DriverConfig {
  @Override
  public WebDriver getWebDriver() {
    try {
      String remoteUrl = "http://localhost:4444/wd/hub";

      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName("chrome");

      ChromeOptions options = new ChromeOptions();
      options.addArguments(
          "--remote-allow-origins=*",
          "--test-type",
          "--no-sandbox",
          "--ignore-certificate-errors",
          "--window-size=1920,1080",
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
      capabilities.merge(options);

      RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
      driver.setFileDetector(new LocalFileDetector());

      return new WebDriverFacade(driver, new WebDriverFactory());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
