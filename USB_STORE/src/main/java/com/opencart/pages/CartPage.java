package com.opencart.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Selector genérico para productos en el carrito
    private By cartTable = By.cssSelector(".table-responsive");

    public boolean isProductInCart(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            String xpath = "//div[@class='table-responsive']//a[contains(text(),'" + productName + "')]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return driver.findElements(By.xpath(xpath)).size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void openCart() {
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/cart");
    }
}
