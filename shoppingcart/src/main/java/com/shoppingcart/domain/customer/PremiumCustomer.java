package com.shoppingcart.domain.customer;

import com.shoppingcart.domain.customer.Customer;

public class PremiumCustomer implements Customer {

    public String customerType() {
        return "PREMIUM";
    }
}
