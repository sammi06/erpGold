package com.cloud09.internship.activity.serverApi;

public class ApiConfiguration {
    //http://test.erp.gold/api/advanceinventory/items/GetList
    public static final String BASE_URL = "http://test.erp.gold/api/";

    public static final String PRODUCTS_URL = BASE_URL + "advanceinventory/items/GetList";
    public static final String GetContacts_URL = BASE_URL + "CRM/leadcontact/GetleadContactList";
    public static final String PostContacts_URL = BASE_URL + "CRM/leadcontact/Post";
}
