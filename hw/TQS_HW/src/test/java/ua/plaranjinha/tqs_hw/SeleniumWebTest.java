package ua.plaranjinha.tqs_hw;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SeleniumJupiter.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumWebTest {

  @LocalServerPort
  int serverPort;
  String url;

  @BeforeEach
  public void setUp() {
    url = String.format("http://localhost:%d/", serverPort);
  }

  @Test
  @Order(0)
  public void getDataFromCachedEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    assertPreMatch(driver, new String[] {
            "\"current\" : \\[ \\]",
            "\"history\" : \\[ \\]",
            "\"forecast\" : \\[ \\]"
    });
  }

  @Test
  @Order(1)
  public void getDataFromCached(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    assertDefaultPreMatch(driver);
    assertPreMatch(driver, new String[] {
            "(\"owm_requests\" : 1|\"wb_requests\" : 1)",
            "\"cache_requests\" : 0",
            "\"failed_requests\" : 0",
            "\"history\" : \\[ \\]",
            "\"forecast\" : \\[ \\]"
    });
  }

  @Test
  public void getDataFromLocation(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PT");
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromCoords(HtmlUnitDriver driver) throws InterruptedException {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("40.6443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromLocationHistory(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PT");
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromCoordsHistory(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("40.6443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromLocationForecast(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PT");
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromCoordsForecast(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("40.6443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertDefaultPreMatch(driver);
  }

  @Test
  public void getDataFromLocationEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromLocationHistoryEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsHistoryEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromLocationForecastEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsForecastEmptyInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromLocationInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PTT");
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("406.443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromLocationHistoryInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PTT");
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsHistoryInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("406.443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromLocationForecastInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("locationInput")).click();
    driver.findElement(By.id("locationInput")).sendKeys("Aveiro");
    driver.findElement(By.id("countryCodeInput")).click();
    driver.findElement(By.id("countryCodeInput")).sendKeys("PTT");
    driver.findElement(By.cssSelector("#typeLocationInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(1) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  @Test
  public void getDataFromCoordsForecastInvalidInputs(HtmlUnitDriver driver) {
    driver.get(url);
    driver.findElement(By.id("latInput")).click();
    driver.findElement(By.id("latInput")).sendKeys("406.443");
    driver.findElement(By.id("lonInput")).click();
    driver.findElement(By.id("lonInput")).sendKeys("-8.6455");
    driver.findElement(By.cssSelector("#typeCoordsInput > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector("form:nth-child(2) > button")).click();
    assertPreMatch(driver, new String[] {"404: Not found."});
  }

  private void assertPreMatch(WebDriver driver, String[] strings) {
    for (String s: strings) {
      assertThat(driver.findElement(By.cssSelector("pre")).getText(), matchesPattern("[\\s\\S]*" + s + "[\\s\\S]*"));
    }
  }

  private void assertDefaultPreMatch(WebDriver driver) {
    assertThat(driver.findElement(By.cssSelector("pre")).getText(), matchesPattern(
            "[\\s\\S]*" + "\"location\" : \"Aveiro\"" +
            "[\\s\\S]*" + "\"country_code\" : \"PT\"" +
            "[\\s\\S]*" + "\"lon\" : -8.6455" +
            "[\\s\\S]*" + "\"lat\" : 40.6443" +
            "[\\s\\S]*"
    ));
  }

}
