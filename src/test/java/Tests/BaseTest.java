package Tests;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        driver = new EdgeDriver(options);
        driver.get("https://www.saucedemo.com/");
//        new LoginPage(driver)
//                .Login("standard_user", "secret_sauce")
//                .IsLoginSuccessful();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
