package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    private final By Checkoutbtn=By.id("checkout");
    private final By firstName=By.id("first-name");
    private final By lastName=By.id("last-name");
    private final By postalCode=By.id("postal-code");
    private final By cancelBtn=By.id("cancel");
    private final By errorMsg=By.cssSelector("h3[data-test='error']");
    private final By continueBtn=By.id("continue");
    private final By cartIcon=By.className("shopping_cart_link");
    private final By products = By.className("cart_item");
    private final By prices= By.className("inventory_item_price");
    private final By finishBtn=By.id("finish");
    private final By priceBeforeTax=By.className("summary_subtotal_label");
    private final By tax=By.className("summary_tax_label");
    private final By priceAfterTax =By.className("summary_total_label");
    private final By imgOrderConfirmed = By.className("pony_express");
    private final By txtOrderConfirmed=By.className("complete-text");
    private final By BackHomeBtn = By.id("back-to-products");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By RemoveBtns = By.xpath("//button[.='Remove']");


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage FillCheckoutInfo(String fName, String lName, String postal){
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(postal);
        driver.findElement(continueBtn).click();
        return this;
    }
    public CheckoutPage enterTxtInPostalCode(String postal){
        driver.findElement(postalCode).sendKeys(postal);
        String actualValue = driver.findElement(postalCode).getAttribute("value");
        Assert.assertTrue(actualValue.matches("\\d*"));
        return this;
    }

    public void IsErrorMessageDisplayed(String error){
        Assert.assertTrue(driver.findElement(errorMsg).getText().contains(error));
    }

    public void CancelCheckout(){
        driver.findElement(cancelBtn).click();
        Assert.assertTrue(driver.findElement(Checkoutbtn).isDisplayed());
    }


    public CheckoutPage VerifyPrices(){
        double sum=0;
        for (int i=0; i<driver.findElements(prices).size(); i++){
            String priceText = driver.findElements(prices).get(i).getText().replace("$", "");
            sum += Double.parseDouble(priceText);
        }
        String priceBeforeTaxText = driver.findElement(priceBeforeTax).getText().replace("Item total: $", "");
        double priceBeforeTaxValue = Double.parseDouble(priceBeforeTaxText);
        Assert.assertEquals(sum, priceBeforeTaxValue);

        String taxText = driver.findElement(tax).getText().replace("Tax: $", "");
        double taxValue = Double.parseDouble(taxText);

        String priceAfterTaxText = driver.findElement(priceAfterTax).getText().replace("Total: $", "");
        double priceAfterTaxValue = Double.parseDouble(priceAfterTaxText);
        double expectedPriceAfterTax = sum + taxValue;
        Assert.assertEquals(priceAfterTaxValue, expectedPriceAfterTax);
            return this;
    }

    public CheckoutPage FinishCheckout(){
        driver.findElement(finishBtn).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(imgOrderConfirmed));
        Assert.assertTrue(driver.findElement(imgOrderConfirmed).isDisplayed());
        Assert.assertTrue(driver.findElement(txtOrderConfirmed).getText().contains("Your order has been dispatched"));
        return this;
    }

    public void BackToHome(){
        driver.findElement(BackHomeBtn).click();
        Assert.assertTrue(driver.findElement(cartIcon).isDisplayed());
        Assert.assertTrue(driver.findElements(cartBadge).isEmpty());
    }

    public CheckoutPage compareProductCount(){
      int productsNumber=driver.findElements(products).size();
      String countOnBadge=driver.findElement(cartBadge).getText();
      int badgeNum=Integer.parseInt(countOnBadge);
        Assert.assertEquals(productsNumber,badgeNum);
        return this;
    }

    public CheckoutPage RemoveAllProducts() {
        List<WebElement> removeButtons = driver.findElements(RemoveBtns);
        for (WebElement btn : removeButtons) {
            btn.click();
        }
        Assert.assertTrue(driver.findElements(cartBadge).isEmpty());
        return this;
    }

    public void IsCheckoutBynDisabled(){
        Assert.assertFalse(driver.findElement(Checkoutbtn).isEnabled());
    }


}
