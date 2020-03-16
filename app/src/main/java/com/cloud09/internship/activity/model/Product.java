package com.cloud09.internship.activity.model;

public class Product {
    public int ProductId;

    public String ProductName;
    public Double ProductRate;
    public String ProductCategory;
    public String CurrentCost;
    public String DiscountedPrice;
    public String ProductDesc;
    public String PItemCode;

    public String getPItemCode() {
        return PItemCode;
    }

    public void setPItemCode(String PItemCode) {
        this.PItemCode = PItemCode;
    }

    //Getters
    public String getProductName() {
        return ProductName;
    }

    //Setters
    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Double getProductRate() {
        return ProductRate;
    }

    public void setProductRate(Double productRate) {
        ProductRate = productRate;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getCurrentCost() {
        return CurrentCost;
    }

    public void setCurrentCost(String currentCost) {
        CurrentCost = currentCost;
    }

    public String getDiscountedPrice() {
        return DiscountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        DiscountedPrice = discountedPrice;
    }
}
