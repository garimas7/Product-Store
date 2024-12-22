package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {".//features/Checkout.feature"},
    glue = "stepsDefinitions",
    plugin = {"pretty", "html:target/Checkout.html"}, // Report generation
    monochrome = true
)
public class CheckoutRunner {
}

