package com.sipios.refactoring.model;

public enum CustomerType {
    STANDARD_CUSTOMER("standard customer",1,200),
    PREMIUM_CUSTOMER("premium customer",0.9,800),
    PLATINUM_CUSTOMER("platinum customer",0.5,2000);

    private final String text;

    private final double discount;

    private final double maxPrice;

    CustomerType(final String text, final double discount, final double maxPrice) {
        this.text = text;
        this.discount = discount;
        this.maxPrice = maxPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getText() {
        return text;
    }
}
