package com.cloud09.internship.activity.model;

public class InvoiceProduct_Class {
    public int id;
    public String ProductInvoiceName;
    public String ProductInvoiceQuantity;
    public String ProductInvoiceDesc;

    public String ProductInvoiceRate;
    public String ProductInvoiceAmount;
    public String ProductInvoiceTax;

    @Override
    public String toString() {
        return "InvoiceProduct_Class{" +
                "id=" + id +
                ", ProductInvoiceName='" + ProductInvoiceName + '\'' +
                ", ProductInvoiceQuantity='" + ProductInvoiceQuantity + '\'' +
                ", ProductInvoiceDesc='" + ProductInvoiceDesc + '\'' +
                ", ProductInvoiceRate='" + ProductInvoiceRate + '\'' +
                ", ProductInvoiceAmount='" + ProductInvoiceAmount + '\'' +
                ", ProductInvoiceTax='" + ProductInvoiceTax + '\'' +
                '}';
    }
}
