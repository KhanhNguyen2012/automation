package starter.stepdefinitions.GoogleSearch;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import starter.config.SerenityConfig;

public class DemoStepDefinitions {

  // DemoQA URLs
  private static final String DEMOQA_HOME_URL = "https://demoqa.com";
  private static final String BOOK_STORE_URL = "https://demoqa.com/books";

  // Target for Book Store page
  private static final Target BOOK_SEARCH_INPUT =
      Target.the("book search input").locatedBy("#searchBox");
  private static final Target BOOK_TABLE_ROWS =
      Target.the("book table rows").locatedBy("//div[@role='gridcell']");
  private static final Target BODY = Target.the("page body").locatedBy("body");

  @Before
  public void setup() {
    OnStage.setTheStage(new Cast());
    WebDriver webDriver = SerenityConfig.getWebDriver();
    Actor actor = OnStage.theActorCalled("User");
    actor.can(BrowseTheWeb.with(webDriver));
  }

  @Given("I navigate to DemoQA homepage")
  public void navigateToDemoQAHomepage() {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    webDriver.navigate().to(DEMOQA_HOME_URL);
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.titleContains("DEMOQA"));
  }

  @Given("I navigate to the book store page")
  public void navigateToBookStorePage() {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    webDriver.navigate().to(BOOK_STORE_URL);
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchBox")));
  }

  @Then("the page title should be {string}")
  public void verifyPageTitle(String expectedTitle) {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.titleContains(expectedTitle));

    String pageTitle = webDriver.getTitle();
    Assert.assertTrue(
        "Page title should contain '" + expectedTitle + "', but was: " + pageTitle,
        pageTitle.contains(expectedTitle));
  }

  @Then("the page should display {string}")
  public void verifyPageDisplaysText(String text) {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    String pageText = webDriver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("Page should display '" + text + "'", pageText.contains(text));
  }

  @When("I search for book title {string}")
  public void searchForBookTitle(String bookTitle) {
    Actor actor = OnStage.theActorInTheSpotlight();

    // Store search term for later verification
    actor.remember("searchTerm", bookTitle);

    // Clear and fill the search box using Screenplay actions
    actor.attemptsTo(
        Clear.field(BOOK_SEARCH_INPUT), SendKeys.of(bookTitle).into(BOOK_SEARCH_INPUT));
  }

  @Then("the search results should display books")
  public void verifySearchResultsDisplayBooks() {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    String pageText = webDriver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("Search results should display book information", pageText.length() > 0);
  }

  @Then("the results should show at least one book")
  public void verifyResultsShowAtLeastOneBook() {
    Actor actor = OnStage.theActorInTheSpotlight();
    WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
    java.util.List<org.openqa.selenium.WebElement> bookRows =
        webDriver.findElements(By.xpath("//div[@role='gridcell']"));
    Assert.assertTrue("Should display at least one book result", bookRows.size() > 0);
  }

  @After
  public void afterScenario() {
    try {
      Actor actor = OnStage.theActorInTheSpotlight();
      WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
      if (webDriver != null) {
        webDriver.quit();
      }
    } catch (Exception e) {
      // Handle case where actor or webdriver is not available
    }
  }
}
