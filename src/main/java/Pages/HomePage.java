package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    WebDriver driver;

    private final By addToCartBtns = By.xpath("//div[.='Sauce Labs Backpack'] //following::button");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By filterIcon = By.tagName("select");
    private final By inventoryItems = By.className("inventory_item");
    private final By images = By.cssSelector("a > img");
    private final By products = By.xpath("//div[@class='inventory_item_name ']");
    private final By backToProductsBtn = By.id("back-to-products");
    private final By RemoveBtns = By.xpath("//button[.='Remove']");
    private final By prices = By.xpath("//div[@class='inventory_item_price']");
    private final By cartIcon= By.className("shopping_cart_link");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

//    public Pages.HomePage AddToCart() {
//        driver.findElements(addToCartBtns).getFirst().click();
//        return this;
//    }
    public HomePage AddAllProducts() {
        List<WebElement> buttons = driver.findElements(addToCartBtns);
        for (WebElement btn : buttons) {
            btn.click();
        }
        return this;
    }
    public HomePage AddNToCart(int count) {
        List<WebElement> buttons = driver.findElements(addToCartBtns);
        for (int i = 0; i < count; i++)
            buttons.get(i).click();
        return this;
    }

    public void IsAddToCart() {
        Assert.assertEquals(driver.findElement((addToCartBtns)).getText(), "Remove");
        Assert.assertTrue(driver.findElement(cartBadge).isDisplayed());
    }

    public CartPage GoToCart() {
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }

    public void IsFilterIconDisplayed() {
        Assert.assertTrue(driver.findElement(filterIcon).isDisplayed());
    }

    public void CheckCount(int num) {
        Assert.assertEquals(driver.findElements(inventoryItems).size(), num);
    }


    public void CheckImagesAreVisible(int num) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(images));
        List<WebElement> imgs = driver.findElements(images);
        Assert.assertEquals(imgs.size(), num);
        for (WebElement img : imgs) {
            Assert.assertTrue(img.isDisplayed(),"img");
        }
    }

    public void goToProductDetailsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int count = driver.findElements(products).size();
        for (int i = 0; i < count; i++) {
            List<WebElement> items = driver.findElements(products);
            items.get(i).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsBtn));
            Assert.assertTrue(driver.findElement(backToProductsBtn).isDisplayed());
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(products));
        }
    }

    public void AddToCartFromPDP() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElements(products).getFirst().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsBtn));
        driver.findElement(addToCartBtns).click();
        Assert.assertEquals(driver.findElement((addToCartBtns)).getText(), "Remove");
        Assert.assertTrue(driver.findElement(cartBadge).isDisplayed());
    }

    public HomePage CompareCartCount() {
        int selectedItems = driver.findElements(RemoveBtns).size();
        String cartCountText = driver.findElement(cartBadge).getText();
        int cartCount = Integer.parseInt(cartCountText);
        Assert.assertEquals(cartCount, selectedItems);
        return this;
    }


    public void RemoveAllProducts() {
        List<WebElement> removeButtons = driver.findElements(RemoveBtns);
        for (WebElement btn : removeButtons) {
            btn.click();
        }
        Assert.assertTrue(driver.findElements(cartBadge).isEmpty());
    }

    public HomePage FilterByNameAsc(){
        new Select(driver.findElement(filterIcon)).selectByVisibleText("Name (A to Z)");
        List<String> ActualList = new ArrayList<>();
        for (WebElement product : driver.findElements(products)) {
            ActualList.add(product.getText());
        }
        List<String> ExpectedList = new ArrayList<>(ActualList);
        Collections.sort(ExpectedList);
        Assert.assertEquals(ActualList,ExpectedList);
            return this;
    }

    public HomePage FilterByNameDesc(){
        new Select(driver.findElement(filterIcon)).selectByVisibleText("Name (Z to A)");
        List<String> ActualList = new ArrayList<>();
        for (WebElement product : driver.findElements(products)) {
            ActualList.add(product.getText());
        }
        List<String> ExpectedList = new ArrayList<>(ActualList);
        ExpectedList.sort(Collections.reverseOrder());
        Assert.assertEquals(ActualList,ExpectedList);
        return this;
    }

    public HomePage FilterBypriceLow(){
        new Select(driver.findElement(filterIcon)).selectByVisibleText("Price (low to high)");
        List<Double> ActualList = new ArrayList<>();
        for (WebElement product : driver.findElements(products)) {
            ActualList.add(Double.parseDouble(product.findElement(prices).getText().replace("$", "")));
        }
        List<Double> ExpectedList = new ArrayList<>(ActualList);
        Collections.sort(ExpectedList);
        Assert.assertEquals(ActualList,ExpectedList);
        return this;
    }

    public HomePage FilterBypriceLHigh(){
        new Select(driver.findElement(filterIcon)).selectByVisibleText("Price (high to low)");
        List<Double> ActualList = new ArrayList<>();
        for (WebElement product : driver.findElements(products)) {
            ActualList.add(Double.parseDouble(product.findElement(prices).getText().replace("$", "")));
        }
        List<Double> ExpectedList = new ArrayList<>(ActualList);
        ExpectedList.sort(Collections.reverseOrder());
        Assert.assertEquals(ActualList,ExpectedList);
        return this;
    }

    public void IsCartCursorPointer(){
        String cursorStyle = driver.findElement(cartIcon).getCssValue("cursor");
        Assert.assertEquals(cursorStyle, "pointer");
    }





}