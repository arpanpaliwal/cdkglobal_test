package com.shoppingcart.service;

import com.shoppingcart.domain.customer.Customer;

public interface ShoppingCartService {

    int calculateDiscountedPrice(int amountBeforeDiscount, Customer customer);
}
