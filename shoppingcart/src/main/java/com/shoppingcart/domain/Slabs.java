package com.shoppingcart.domain;

public class Slabs {
    final int lower;
    final int higher;
    final int discount;

    public Slabs(int lower, int higher, int discount) {
        this.lower = lower;
        this.higher = higher;
        this.discount = discount;
    }

    public int getLower() {
        return lower;
    }

    public int getHigher() {
        return higher;
    }

    public int getDiscount() {
        return discount;
    }
}
