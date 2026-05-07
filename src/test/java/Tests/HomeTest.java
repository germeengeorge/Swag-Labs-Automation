package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class HomeTest extends BaseTest {
HomePage homePage ;
    public void AddToCart() {
       new LoginPage(driver)
               .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(1)
                .CompareCartCount();
    }

    public void CheckIemsCount(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .CheckCount(6);
    }

    public void CheckImagesVisibility(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .CheckImagesAreVisible(6);
    }

    public void CheckFilterIcon() {
       new LoginPage(driver)
               .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .IsFilterIconDisplayed();

    }

    public void ProductDetailsPage() {
        new LoginPage(driver)
                .Login("standard_user", "secret_sauce")
                .IsLoginSuccessful()
                .goToProductDetailsPage();
    }

    public void AddAllProducts() {
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddAllProducts()
                .CompareCartCount();
    }

    public void RemoveAllProducts() {
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddAllProducts()
                .CompareCartCount()
                .RemoveAllProducts();
    }

    public void addOneProductandRemoveThis() {
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddNToCart(1)
                .CompareCartCount()
                .RemoveAllProducts();

    }

    public void filterByNameAsc() {
        new LoginPage(driver)
                .Login("standard_user", "secret_sauce")
                .IsLoginSuccessful()
                .FilterByNameAsc();
    }

    public void filterByNameDesc() {
        new LoginPage(driver)
                .Login("standard_user", "secret_sauce")
                .IsLoginSuccessful()
                .FilterByNameDesc();
    }

    public void filterByPriceLow() {
        new LoginPage(driver)
                .Login("standard_user", "secret_sauce")
                .IsLoginSuccessful()
                .FilterBypriceLow();
    }

    public void filterByPriceHigh() {
        new LoginPage(driver)
                .Login("standard_user", "secret_sauce")
                .IsLoginSuccessful()
                .FilterBypriceLHigh();
    }

    public void CheckCusrorPointer(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .IsCartCursorPointer();
    }

     public void AddToCartFromPDP(){
        new LoginPage(driver)
                .Login("standard_user","secret_sauce")
                .IsLoginSuccessful()
                .AddToCartFromPDP();
     }

}