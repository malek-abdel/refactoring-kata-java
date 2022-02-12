package com.sipios.refactoring.model;

public class Body {

    private Item[] items;
    private CustomerType type;

    public Body(Item[] is, CustomerType t) {
        this.items = is;
        this.type = t;
    }

    public Body(Item[] is, String t) {
        this.items = is;
        this.type = CustomerType.valueOf(t);
    }

    public Body() {}

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

}
