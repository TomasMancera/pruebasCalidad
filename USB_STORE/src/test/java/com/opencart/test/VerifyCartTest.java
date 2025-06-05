package com.opencart.test;

import com.opencart.pages.CartPage;
import com.opencart.pages.ProductPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyCartTest extends BaseTest {

    @Test
    public void verifyProductInCart() {
        ProductPage productPage = new ProductPage(driver);

        // Buscar producto
        productPage.searchForProduct("iMac");

        // Entrar al producto
        productPage.selectFirstProductFromResults();

        // Agregar al carrito
        productPage.addProductToCart();

        // Ir al carrito
        CartPage cart = new CartPage(driver);
        cart.openCart();

        // Verificar que esté el producto
        assertTrue(cart.isProductInCart("iMac"), "Producto no encontrado en el carrito: iMac");
    }
}
