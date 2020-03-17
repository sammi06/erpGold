package com.cloud09.internship.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cloud09.internship.activity.model.InvoiceProduct_Class;
import com.cloud09.internship.activity.model.Product;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class products_sqlite extends SQLiteOpenHelper {

    public products_sqlite(@Nullable Context context) {
        super(context, "Invoice", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = "CREATE TABLE products(id INTEGER primary key AUTOINCREMENT, p_id, p_name TEXT, p_rate Double, discounted_price TEXT, current_cost TEXT, p_category TEXT, p_description TEXT)";
            String CREATE_TABLE2 = "CREATE TABLE invoiceproducts (id INTEGER primary key AUTOINCREMENT, p_name TEXT, p_quantity TEXT, p_description TEXT, p_rate TEXT, p_amount TEXT, sales_tax TEXT)";
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE2);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.i("cvv", "Unable to create DB" + ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS invoiceproducts");
        onCreate(db);
    }

    public void addProducts(Product product) {
        Product obj_product = new Product();
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("p_id", obj_product.ProductId);
        cv.put("p_name", obj_product.ProductName);
        cv.put("p_rate", obj_product.ProductName);
        cv.put("discounted_price", obj_product.ProductName);
        cv.put("current_cost", obj_product.ProductName);

        db.insert("products", null, cv);
        Log.i("cvv", "adoup1: " + (cv));
        db.close();
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> product_db_List = new ArrayList<>();
        String query = "SELECT * FROM products";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            Product p = new Product();

            p.ProductName = c.getString(c.getColumnIndex("p_name"));
            p.ProductDesc = c.getString(c.getColumnIndex("p_description"));
            p.ProductCategory = c.getString(c.getColumnIndex("p_category"));
            p.ProductRate = c.getDouble(c.getColumnIndex("p_rate"));


            product_db_List.add(p);
            Log.i("cvv", product_db_List.toString());
        }
        c.close();
        db.close();
        return product_db_List;
    }


    public void addInvoiceProducts(String name, String quantity, String description, String rate, String amount, String SalesTax) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("p_name", name);
        cv.put("p_quantity", quantity);
        cv.put("p_description", description);
        cv.put("p_rate", rate);
        cv.put("p_amount", amount);
        cv.put("sales_tax", SalesTax);

        db.insert("invoiceproducts", null, cv);
        db.close();
    }

    public void updateInvoiceProducts(int id, String name, String quantity, String description, String rate, String amount, String SalesTax) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "Update invoiceproducts set p_name='" + name + "', p_quantity='" + quantity + "'  ,p_description='" + description + "'  ,p_rate='" + rate + "'  ,p_amount='" + amount + "'  ,sales_tax='" + SalesTax + "'  WHERE id=" + id;
        db.execSQL(query);
        Log.i("cvv", "adoup2: " + (query));
        db.close();
    }

    public ArrayList<InvoiceProduct_Class> getAllInvoiceProducts() {
        ArrayList<InvoiceProduct_Class> product_db_List = new ArrayList<>();
        String query = "SELECT * FROM invoiceproducts";

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            InvoiceProduct_Class p = new InvoiceProduct_Class();

            p.id = c.getInt(c.getColumnIndex("id"));
            p.ProductInvoiceName = c.getString(c.getColumnIndex("p_name"));
            p.ProductInvoiceQuantity = c.getString(c.getColumnIndex("p_quantity"));
            p.ProductInvoiceDesc = c.getString(c.getColumnIndex("p_description"));
            p.ProductInvoiceRate = c.getString(c.getColumnIndex("p_rate"));
            p.ProductInvoiceAmount = c.getString(c.getColumnIndex("p_amount"));
            p.ProductInvoiceTax = c.getString(c.getColumnIndex("sales_tax"));

            product_db_List.add(p);
        }
        c.close();
        db.close();
        return product_db_List;
    }

    public void deleteInvoiceProducts(int Id) {
        try {
            String query = "DELETE FROM invoiceproducts WHERE id=" + Id;
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getInvoiceProductsAmount() {
        String query = "SELECT SUM (p_amount) as total_amount FROM invoiceproducts";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.getCount() == 0) {
            c.close();
            return 0;
        } else {
            c.moveToFirst();
            int totalAmount = c.getInt(c.getColumnIndex("total_amount"));
            c.close();
            db.close();
            return totalAmount;
        }
    }
}
