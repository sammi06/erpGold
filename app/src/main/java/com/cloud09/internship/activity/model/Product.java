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

    public String getProductName() {
        return ProductName;
    }

    public Double getProductRate() {
        return ProductRate;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public String getCurrentCost() {
        return CurrentCost;
    }

    public String getDiscountedPrice() {
        return DiscountedPrice;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public String getPItemCode() {
        return PItemCode;
    }


    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setProductRate(Double productRate) {
        ProductRate = productRate;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public void setCurrentCost(String currentCost) {
        CurrentCost = currentCost;
    }

    public void setDiscountedPrice(String discountedPrice) {
        DiscountedPrice = discountedPrice;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public void setPItemCode(String PItemCode) {
        this.PItemCode = PItemCode;
    }
}
