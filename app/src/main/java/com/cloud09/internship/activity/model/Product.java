package com.cloud09.internship.activity.model;

public class Product {
    public int ProductId;

    public String ProductName;
    public Double ProductRate;
    public String ProductCategory;
    public String CurrentCost;
    public String DiscountedPrice;
    public String ProductDesc;

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductRate=" + ProductRate +
                ", ProductCategory='" + ProductCategory + '\'' +
                ", CurrentCost='" + CurrentCost + '\'' +
                ", DiscountedPrice='" + DiscountedPrice + '\'' +
                ", ProductDesc='" + ProductDesc + '\'' +
                '}';
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





}
