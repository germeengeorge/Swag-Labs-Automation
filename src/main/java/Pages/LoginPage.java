package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    private final By username=By.id("user-name");
    private final By password=By.id("password");
    private final By loginBtn=By.id("login-button");
    private final By cartIcon=By.className("shopping_cart_link");
    private final By errorMsg=By.cssSelector("h3[data-test='error']");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage Login(String userName, String pass) {
        driver.findElement(username).sendKeys(userName);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
        return this;
    }

    public HomePage IsLoginSuccessful(){
        Assert.assertTrue(driver.findElement((cartIcon)).isDisplayed());
        return new HomePage(driver);
    }

    public void IsErrorMessageDisplayed(String errormsg){
         Assert.assertTrue(driver.findElement(errorMsg).getText().contains(errormsg));
    }
}
