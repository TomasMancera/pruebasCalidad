package com.opencart.test;

import com.opencart.pages.HomePage;
import com.opencart.pages.RegisterPage;
import com.opencart.utils.ExcelReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUserFromExcel() {
        // Ruta al archivo y hoja
        String excelPath = "src/main/resources/inputData.xlsx";
        ExcelReader reader = new ExcelReader(excelPath, "UsuariosRegistro");

        int rowCount = reader.getRowCount();

        for (int i = 1; i < rowCount; i++) { // Empieza desde 1 para omitir encabezado
            String firstName = reader.getCellData(i, 0);
            String lastName = reader.getCellData(i, 1);
            String email = reader.getCellData(i, 2);
            String telephone = reader.getCellData(i, 3);
            String password = reader.getCellData(i, 4);

            // Navegar y registrar
            HomePage home = new HomePage(driver);
            home.goToRegisterPage();

            RegisterPage register = new RegisterPage(driver);
            register.enterFirstName(firstName);
            register.enterLastName(lastName);
            register.enterEmail(email);
            register.enterTelephone(telephone);
            register.enterPassword(password);
            register.acceptPrivacyPolicy();
            register.clickContinue();

            // Validación
            assertTrue(register.isRegistrationSuccessful(), "Registro fallido para: " + email);
        }
    }
}
