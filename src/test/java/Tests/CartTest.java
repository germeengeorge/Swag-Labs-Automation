package Tests;

import Pages.LoginPage;
import org.testng.annotations.Test;

@Test
public class CartTest extends BaseTest {

    public void IsCartOpened(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .GoToCart()
                .IsCartPageOpened();
    }

    public void CheckProductNumbersMatchBadge(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .ProductsNumbersMatchBadge();
    }

    public void CheckContinueShoppingBtn(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .GoToCart()
                .ContinueShopping();
    }

     public void CheckCheckoutBtn(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .CheckoutBtnExist();
    }

    public void CheckCountAfterRemovingProduct(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(3)
                .GoToCart()
                .RemoveFromCart();
    }

    public void CheckoutIsDisabled(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .GoToCart()
                .CheckoutBtnWhenCartIsEmpty();
    }

    public void ExistanceOfProductsInCart(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .GoToCart()
                .ProductsAreVisible();
    }

    public void AddOneAndRemoveFromCart(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(1)
                .GoToCart()
                .RemoveFromCart();
    }


}
