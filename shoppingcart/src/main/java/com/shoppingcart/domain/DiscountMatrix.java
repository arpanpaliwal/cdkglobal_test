package com.shoppingcart.domain;

import com.shoppingcart.domain.customer.Customer;

import java.util.*;

public class DiscountMatrix {

    private DiscountMatrix() {

    }
    static Map<String, List<Slabs>> filledMatrix = new HashMap<String, List<Slabs>>();
    static {

        //Should come from data source
        //slab for regular customer
        List<Slabs> slabsRegular = new ArrayList<>();
        slabsRegular.add(new Slabs(0, 5000, 0));
        slabsRegular.add(new Slabs(5000, 10000, 10));
        slabsRegular.add(new Slabs(10000, Integer.MAX_VALUE, 20));
        filledMatrix.put("REGULAR", slabsRegular);

        //slabs for premium customer
        List<Slabs> slabsPremium = new ArrayList<>();
        slabsPremium.add(new Slabs(0, 4000, 10));
        slabsPremium.add(new Slabs(4000, 8000, 15));
        slabsPremium.add(new Slabs(8000, 12000, 20));
        slabsPremium.add(new Slabs(12000, Integer.MAX_VALUE, 30));
        filledMatrix.put("PREMIUM", slabsPremium);

    }

    public static Optional<List<Slabs>> slabsfor(Customer customer) {
        return Optional.ofNullable(filledMatrix.get(customer.customerType()));

    }
}
