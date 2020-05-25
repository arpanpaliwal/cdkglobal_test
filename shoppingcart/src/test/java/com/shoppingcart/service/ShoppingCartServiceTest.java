package com.shoppingcart.service;

import com.shoppingcart.domain.customer.Customer;
import com.shoppingcart.domain.customer.PremiumCustomer;
import com.shoppingcart.domain.customer.RegularCustomer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShoppingCartServiceTest {
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void initialize() {
        shoppingCartService = new ShoppingCartServiceImpl();
    }

    @Test
    public void shoppingCartService_shouldBeCreated() {
        Assertions.assertNotNull(shoppingCartService);
    }

    @Test
    public void testCalculateDiscount_when_customerIs_RegularCustomer() {
        Customer customer = new RegularCustomer();
        int amountWithoutDiscount = 5000;
        int disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(5000, disocuntedPrice);

        amountWithoutDiscount = 10000;
        disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(9500, disocuntedPrice);

        amountWithoutDiscount = 15000;
        disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(13500, disocuntedPrice);
    }

    @Test
    public void testCalculateDiscount_when_customerIs_PremiumCustomer() {
        Customer customer = new PremiumCustomer();
        int amountWithoutDiscount = 4000;
        int disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(3600, disocuntedPrice);

        amountWithoutDiscount = 8000;
        disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(7000, disocuntedPrice);

        amountWithoutDiscount = 12000;
        disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(10200, disocuntedPrice);

        amountWithoutDiscount = 20000;
        disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, customer);
        Assertions.assertEquals(15800, disocuntedPrice);
    }

    @ParameterizedTest
    @ValueSource(ints = {4000,8000,12000,20000})
    public void testCalculateDiscount_when_customerIs_NoDiscount_customer(int amountWithoutDiscount) {
        Customer defaultCustomer = () -> "DEFAULT";
        int disocuntedPrice = shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, defaultCustomer);
        Assertions.assertEquals(amountWithoutDiscount, disocuntedPrice);
    }

    @Test
    public void testNegativeAmount() {
        Customer defaultCustomer = () -> "DEFAULT";
        int amountWithoutDiscount = -1;
        Assertions.assertThrows(RuntimeException.class, () -> shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, defaultCustomer));

    }

    @Test
    public void testAmountIs_zero() {
        Customer defaultCustomer = () -> "DEFAULT";
        int amountWithoutDiscount = 0;
        Assertions.assertThrows(RuntimeException.class, () -> shoppingCartService.calculateDiscountedPrice(amountWithoutDiscount, defaultCustomer));

    }

    @AfterEach
    public void distory() {
        shoppingCartService = null;
    }
}
