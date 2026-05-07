package Tests;

import Pages.LoginPage;
import org.testng.annotations.Test;

@Test
public class CheckoutTest extends BaseTest {

    public void CheckCheckoutProcess(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("xyz","xyz","12334")
                .compareProductCount()
                .VerifyPrices()
                .FinishCheckout()
                .BackToHome();
    }
    public void EmptyFirstNameCheckout(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("","xyz","123")
                .IsErrorMessageDisplayed("Error: First Name is required");
    }

    public void EmptyLastNameCheckout(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("xyz","","123")
                .IsErrorMessageDisplayed("Error: Last Name is required");
    }
    public void EmptyPostalCodeCheckout(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("xyz","xyz","")
                .IsErrorMessageDisplayed("Error: Postal Code is required");
    }

    public void PostalCodeValue(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("xyz","xyz","")
                .enterTxtInPostalCode("abc");
    }

    public void CancelCheckout(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .GoToCheckout()
                .CancelCheckout();

    }


    public void FullProccessWithFilter(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .FilterBypriceLHigh()
                .AddNToCart(2)
                .GoToCart()
                .GoToCheckout()
                .FillCheckoutInfo("xyz","xyz","12334")
                .VerifyPrices()
                .FinishCheckout()
                .BackToHome();

    }


}
