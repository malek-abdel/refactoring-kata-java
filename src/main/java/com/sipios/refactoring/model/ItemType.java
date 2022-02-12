package com.sipios.refactoring.model;

public enum ItemType {
    TSHIRT(30,1),
    DRESS(50,0.8),
    JACKET(100,0.9);

    private final double price;

    private final double discountPeriodRate;

    ItemType(final double price, final double discountPeriodRate) {
        this.price = price;
        this.discountPeriodRate = discountPeriodRate;
    }

    public double getDiscountPeriodRate() {
        return discountPeriodRate;
    }

    public double getPrice() {
        return price;
    }
}
