import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webpages.DeveloperApplyPage;
import webpages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplyAsDeveloperTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void TearDown(){
        driver.close();
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);
        assertTrue(home.isPageOpened());
        home.clickOnDeveloperApplyButton();

        //Create object of DeveloperApplyPage
        DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

        //Check if page is opened
        assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setDeveloper_email("dejan@toptal.com");
        applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
        applyPage.setDeveloper_password("password123");
        applyPage.setDeveloper_password_confirmation("password123");

        //Click on join
        //applyPage.clickOnJoin();
    }

}