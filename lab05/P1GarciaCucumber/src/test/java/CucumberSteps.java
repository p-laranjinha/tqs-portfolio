import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CucumberSteps {
    Calculator calculator;

    @Given("a calculator I just turned on")
    public void aCalculatorIJustTurnedOn() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calculator.push(arg0);
        calculator.push(arg1);
        calculator.push("+");
    }

    @Then("the result is {int}")
    public void theResultIs(int arg0) {
        assertEquals(arg0, calculator.value().doubleValue());
    }

    @When("I subtract {int} to {int}")
    public void iSubtractTo(int arg0, int arg1) {
        calculator.push(arg0);
        calculator.push(arg1);
        calculator.push("-");
    }

    @When("I divide {int} by {int}")
    public void iDivideBy(int arg0, int arg1) {
        calculator.push(arg0);
        calculator.push(arg1);
        calculator.push("/");
    }
}
