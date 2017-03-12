package com.jzarsuelo.android.inventory.pojo;

public class Item {
    private String mName;
    private Double mPrice;
    private Integer mQuantity;

    public Item() {}

    public Item(String name, Double price, Integer quantity) {
        mName = name;
        mPrice = price;
        mQuantity = quantity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public Integer getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Integer quantity) {
        mQuantity = quantity;
    }
}
