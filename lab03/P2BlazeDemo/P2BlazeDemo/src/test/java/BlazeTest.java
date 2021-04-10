// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BlazeTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void blaze() throws InterruptedException {
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