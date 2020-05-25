package com.shoppingcart.service;

import com.shoppingcart.domain.customer.Customer;
import com.shoppingcart.domain.Slabs;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    public int calculateDiscountedPrice(final int amountBeforeDiscount, Customer customer) {
        if (amountBeforeDiscount <= 0) {
            throw new RuntimeException("amount must be +ve number");
        }
        int discountedPrice = 0;
        List<Slabs> slabs = customer.getSlabs();
        for (Slabs slab : slabs) {
            int diff = 0;
            if (amountBeforeDiscount >= slab.getHigher()) {
                diff = Math.subtractExact(slab.getHigher(), slab.getLower());
            }
            if (amountInSlabRange(amountBeforeDiscount, slab)) {
                diff = Math.subtractExact(amountBeforeDiscount, slab.getLower());
            }
            discountedPrice += calculateDeltaDiscountAmount(diff, slab.getDiscount());
        }
        return amountBeforeDiscount - discountedPrice;
    }

    private boolean amountInSlabRange(int amountBeforeDiscount, Slabs slab) {
        return amountBeforeDiscount > slab.getLower() && amountBeforeDiscount <= slab.getHigher();
    }

    private int calculateDeltaDiscountAmount(int obtained, int percentage) {
        return (obtained * percentage) / 100;
    }
}
