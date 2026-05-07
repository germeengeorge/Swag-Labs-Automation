package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
    WebDriver driver;

    private final By Checkoutbtn=By.id("checkout");
    private final By ContinueShoppingBtn=By.id("continue-shopping");
    private final By cartIcon=By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By products = By.className("cart_item");
    private final By filterIcon = By.tagName("select");
    private final By checkoutInfo= By.className("checkout_info");
    private final By RemoveBtns = By.xpath("//button[.='Remove']");



    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

//    public CartPage OpenCart(){
//        driver.findElement(cartIcon).click();
//        return this;
//    }
    public CheckoutPage GoToCheckout(){
        driver.findElement(Checkoutbtn).click();
        return new CheckoutPage(driver);
    }

    public void IsCartPageOpened(){
        Assert.assertTrue(driver.findElement(Checkoutbtn).isDisplayed());
        //Assert.assertTrue(driver.findElement(ContinueShoppingBtn).isDisplayed());
    }

    public void ProductsNumbersMatchBadge(){
        int badgeNumber = Integer.parseInt(driver.findElement(cartBadge).getText());
        int productsNumber = driver.findElements(products).size();
        Assert.assertEquals(badgeNumber, productsNumber);
    }

    public void ContinueShopping(){
        driver.findElement(ContinueShoppingBtn).click();
        Assert.assertTrue(driver.findElement(filterIcon).isDisplayed());
    }

     public void CheckoutBtnExist(){
        driver.findElement(Checkoutbtn).click();
        Assert.assertTrue(driver.findElement(checkoutInfo).isDisplayed());
     }

    public void RemoveFromCart() {
        int productsNumberBefore = driver.findElements(products).size();
        driver.findElements(RemoveBtns).getFirst().click();
        int productsNumberAfter = driver.findElements(products).size();
        Assert.assertEquals(productsNumberAfter, productsNumberBefore - 1);
        if (productsNumberAfter == 0) {
            Assert.assertTrue(driver.findElements(cartBadge).isEmpty());
        } else {
            int badgeNumber = Integer.parseInt(driver.findElement(cartBadge).getText());
            Assert.assertEquals(badgeNumber, productsNumberAfter);
        }
    }

    public void CheckoutBtnWhenCartIsEmpty(){
        Assert.assertFalse(driver.findElement(Checkoutbtn).isDisplayed());
    }

    public void ProductsAreVisible(){
        int productsNumber = driver.findElements(products).size();
        driver.findElement(ContinueShoppingBtn).click();
        driver.findElement(cartIcon).click();
        int productsNumberAfter = driver.findElements(products).size();
        Assert.assertEquals(productsNumberAfter, productsNumber);
    }







}
