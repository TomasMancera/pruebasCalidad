package com.opencart.test;

import com.opencart.pages.ProductPage;
import com.opencart.utils.ExcelReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectProductTest extends BaseTest {

    @Test
    public void searchAndAddProductsFromExcel() {
        String path = "src/main/resources/inputData.xlsx";
        ExcelReader reader = new ExcelReader(path, "Hoja1");

        int rows = reader.getRowCount();

        ProductPage productPage = new ProductPage(driver);

        for (int i = 1; i < rows; i++) {
            String product = reader.getCellData(i, 2); // columna C: "producto"

            productPage.searchForProduct(product);
            assertTrue(productPage.isProductFound(), "Producto no encontrado: " + product);

            productPage.addToCart();
            assertTrue(productPage.isProductAddedSuccessfully(), "No se agregó al carrito: " + product);
        }
    }
}
