package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Crear elementos de la pagina
    private By category(String category) {
        return By.xpath("//a[text()='" + category + "']");
    }

    private By subCategory(String subCategory) {
        return By.xpath("//div[@class='dropdown-inner']//a[contains(text(),'" + subCategory + "')]");
    }

    //Metodos o acciones de la pagina
    public void selectCategory(String category) {
        driver.findElement(category(category)).click();
    }

    public void selectSubCategory(String subCategory) {
        //Fluent Wait
        Wait<WebDriver> waitF = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15)) //Tiempo maximo de espera
                .pollingEvery(Duration.ofMillis(300)) //Frecuencia de verificacion
                .ignoring(NoSuchFieldError.class); //Excepciones que Ignora

        WebElement subCat = waitF.until(driver -> driver.findElement(subCategory(subCategory)));
        subCat.click();
    }

    // En HomePage.java
    public void goToRegisterPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
        myAccount.click();

        try {
            WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li/a[text()='Logout']")
            ));
            logoutLink.click();

            wait.until(ExpectedConditions.urlContains("route=account/logout"));

            myAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
            myAccount.click();

        } catch (TimeoutException e) {
            System.out.println("Usuario no estaba logueado o enlace 'Logout' no encontrado. Procediendo a buscar 'Register'.");
        }

        // Ahora, busca el enlace "Register"
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li/a[text()='Register']")
        ));
        registerLink.click();
    }

}
