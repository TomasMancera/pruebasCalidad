package com.opencart.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");
    private By productResult = By.cssSelector(".product-thumb");
    private By addToCartButton = By.xpath("//button[@onclick[contains(.,'cart.add')]]");
    private By successMessage = By.cssSelector(".alert-success");

    public void searchForProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void selectFirstProductFromResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-thumb")));

        driver.findElement(By.cssSelector(".product-thumb h4 a")).click();
    }

    public void addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By addToCartButton = By.id("button-cart");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".alert-success")
        ));
    }

    public boolean isProductFound() {
        return driver.findElements(productResult).size() > 0;
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public boolean isProductAddedSuccessfully() {
        return driver.findElements(successMessage).size() > 0;
    }
}
