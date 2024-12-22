package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {".//features/LoginPage.feature"},
    glue = "stepsDefinitions",
    plugin = {"pretty", "html:target/LoginPage.html"}, // Report generation
    monochrome = true
)
public class LoginPageRunner {

}
