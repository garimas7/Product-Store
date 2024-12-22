package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {".//features/Signup.feature"},
    glue = "stepsDefinitions",
    plugin = {"pretty", "html:target/SignUpPage.html"}, // Report generation
    monochrome = true
)
public class SignupRunner {

}
