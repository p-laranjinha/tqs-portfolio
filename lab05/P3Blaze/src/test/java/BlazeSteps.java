import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlazeSteps {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Given("the website {string}")
    public void theWebsite(String arg0) {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.get(String.format("https://%s/", arg0));
        driver.manage().window().setSize(new Dimension(716, 727));
    }

    @When("I choose the departure {int}")
    public void iChooseTheDeparture(int arg0) {
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector(String.format(".form-inline:nth-child(%d) > option:nth-child(%d)", arg0 == 1 ? arg0 : arg0 + 1, arg0))).click();
    }

    @And("I choose the destination {int}")
    public void iChooseTheDestination(int arg0) {
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.cssSelector(String.format(".form-inline:nth-child(%d) > option:nth-child(%d)", arg0 == 1 ? arg0 : arg0 + 1, arg0))).click();
    }

    @And("I click the submit button")
    public void iClickTheSubmitButton() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the flight {int}")
    public void iChooseTheFlight(int arg0) {
        driver.findElement(By.cssSelector(String.format("tr:nth-child(%d) .btn", arg0))).click();
    }

    @And("I insert the name {string}")
    public void iInsertTheName(String arg0) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(arg0);
    }

    @And("I insert the credit card {int}")
    public void iInsertTheCreditCard(int arg0) {
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(Integer.toString(arg0));
    }

    @Then("I make a successful travel choice")
    public void iMakeASuccessfulTravelChoice() throws InterruptedException{
        TimeUnit.SECONDS.sleep(1);
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
        driver.quit();
    }
}
