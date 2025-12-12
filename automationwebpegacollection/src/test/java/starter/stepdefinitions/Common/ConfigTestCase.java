package starter.stepdefinitions.Common;

import io.cucumber.java.*;
import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import org.openqa.selenium.WebDriver;
import starter.constants.CommonConstants;

public class ConfigTestCase {
  public static Map<String, WebDriver> webDriverMap = new HashMap<>();
  public static final ThreadLocal<String> currentActorName = new ThreadLocal<>();

  @BeforeAll
  public static void beforeAll() {}

  @Before
  public void before(Scenario scenario) {
    Serenity.setSessionVariable(CommonConstants.SCENARIO_NAME).to(scenario.getName());
    OnStage.setTheStage(new Cast());
  }

  @After
  public void after() {
    Actor actor = OnStage.theActorInTheSpotlight();
    String actorName = currentActorName.get();
    if (actorName != null) {
      ActorLockManager.unlock(actorName);
      currentActorName.remove();
    }
  }

  @AfterAll
  public static void afterAll() {
    webDriverMap.forEach(
        (name, webDriver) -> {
          webDriver.quit();
        });
  }
}
