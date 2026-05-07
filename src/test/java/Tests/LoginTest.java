package Tests;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void ValidLogin(){
      new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful();
    }


    @Test
    public void InvalidLogin(){
        new LoginPage(driver)
                .Login("germeen","secret_sauce")
                .IsErrorMessageDisplayed("Username and password do not match");
    }
    @Test
    public void EmptyLogin(){
        new LoginPage(driver)
                .Login("","")
                .IsErrorMessageDisplayed("Username is required");
    }

}
