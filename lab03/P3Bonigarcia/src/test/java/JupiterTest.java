import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SeleniumJupiter.class)
class JupiterTest {

    @Test
    void testWithOneFirefox(FirefoxDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testWithTwoFirefoxs(FirefoxDriver driver1,
                             FirefoxDriver driver2) {
        driver1.get("http://www.seleniumhq.org/");
        driver2.get("http://junit.org/junit5/");
        assertThat(driver1.getTitle(), startsWith("Selenium"));
        assertThat(driver2.getTitle(), equalTo("JUnit 5"));
    }

    @Test
    void testWithOneHtmlUnit(HtmlUnitDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    public void blaze(HtmlUnitDriver driver) throws InterruptedException {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(716, 727));
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(1)")).click();
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Flights from Paris to London:"));
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Pedro Laranjinha");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("3819273");
        driver.findElement(By.id("creditCardNumber")).sendKeys(Keys.ENTER);
        // If I don't make it wait, the assert can't find the h1 element
        TimeUnit.SECONDS.sleep(1);
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }
}