package com.shoppingcart.domain.customer;

import com.shoppingcart.domain.customer.Customer;

public class RegularCustomer implements Customer {
    @Override
    public String customerType() {
        return "REGULAR";
    }
}
