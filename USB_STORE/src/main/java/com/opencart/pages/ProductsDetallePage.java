package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsDetallePage extends BasePage{
    public ProductsDetallePage(WebDriver driver){ super(driver);}

    //Crear los elementos de la pag
    private By product(String product){
        return By.xpath("//a[contains(text(),'"+product+"')]");
    }

    //Metodos o acciones de la pagina
    public String obtenrTituloPag(){
        return driver.getTitle();
    }

    public void selectProduct(String product){
        driver.findElement(product(product)).click();
    }

}
