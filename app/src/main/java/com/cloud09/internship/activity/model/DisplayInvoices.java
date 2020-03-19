package com.cloud09.internship.activity.model;

public class DisplayInvoices {

    int InvoiceId;

    String InvoiceNumber;
    String CustomerName;
    String InvoiceAmount;

    String DueDate;
    String DueDays;
    String InvoiceStatus;


    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getInvoiceAmount() {
        return InvoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        InvoiceAmount = invoiceAmount;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getDueDays() {
        return DueDays;
    }

    public void setDueDays(String dueDays) {
        DueDays = dueDays;
    }

    public String getInvoiceStatus() {
        return InvoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        InvoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "DisplayInvoices{" +
                "InvoiceId=" + InvoiceId +
                ", InvoiceNumber='" + InvoiceNumber + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", InvoiceAmount='" + InvoiceAmount + '\'' +
                ", DueDate='" + DueDate + '\'' +
                ", DueDays='" + DueDays + '\'' +
                ", InvoiceStatus='" + InvoiceStatus + '\'' +
                '}';
    }
}
