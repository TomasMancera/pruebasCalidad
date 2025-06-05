package com.opencart.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Elementos
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.name("agree");
    private By continueButton = By.cssSelector("input.btn-primary");

    private By successMessage = By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]");

    // Acciones
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterTelephone(String phone) {
        driver.findElement(telephoneInput).sendKeys(phone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(password);
    }

    public void acceptPrivacyPolicy() {
        driver.findElement(privacyPolicyCheckbox).click();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isRegistrationSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.urlContains("route=account/success"));
        } catch (TimeoutException e) {
            return false;
        }
    }

}
