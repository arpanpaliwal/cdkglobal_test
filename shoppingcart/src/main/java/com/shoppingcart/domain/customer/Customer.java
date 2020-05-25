package com.shoppingcart.domain.customer;

import com.shoppingcart.domain.DiscountMatrix;
import com.shoppingcart.domain.Slabs;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Customer {

    default List<Slabs> getSlabs() {
        Optional<List<Slabs>> slabs = DiscountMatrix.slabsfor(this);
        return slabs.orElse(Collections.emptyList());
    }

    String customerType();
}


