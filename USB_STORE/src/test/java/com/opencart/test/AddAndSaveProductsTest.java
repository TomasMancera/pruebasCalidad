package com.opencart.test;

import com.opencart.pages.ProductPage;
import com.opencart.utils.ExcelReader;
import com.opencart.utils.ExcelWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddAndSaveProductsTest extends BaseTest {

    @Test
    public void searchAddAndSaveProducts() {
        String inputPath = "src/main/resources/inputData.xlsx";
        String outputPath = "src/main/resources/outputData.xlsx";

        ExcelReader reader = new ExcelReader(inputPath, "Hoja1");
        int rows = reader.getRowCount();

        ProductPage productPage = new ProductPage(driver);
        List<String> successfullyAdded = new ArrayList<>();

        for (int i = 1; i < rows; i++) {
            String product = reader.getCellData(i, 2); // Columna "producto"

            productPage.searchForProduct(product);
            if (productPage.isProductFound()) {
                productPage.addToCart();

                if (productPage.isProductAddedSuccessfully() && product != null && !product.isBlank()) {
                    successfullyAdded.add(product.trim());
                }
            }
        }

        // Escribir productos agregados en nuevo Excel
        ExcelWriter.writeProductsToExcel(successfullyAdded, outputPath);

        assertTrue(successfullyAdded.size() > 0, "No se agregó ningún producto exitosamente");
    }
}
