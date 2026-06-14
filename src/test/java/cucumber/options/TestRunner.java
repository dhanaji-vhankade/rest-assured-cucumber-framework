package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinations" }, plugin = {
		"pretty" , "json:target/jsonReports/cucumber-reports.json"})
//tags = "@AddPlace"

public class TestRunner {

}
