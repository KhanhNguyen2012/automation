package starter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = {"starter.stepdefinitions"},
    plugin = {
      "io.cucumber.core.plugin.SerenityReporterParallel",
      "pretty",
      "timeline:build/test-results/timeline"
    },
    features = {"src/test/resources/features"})
public class CucumberTestCase {}
