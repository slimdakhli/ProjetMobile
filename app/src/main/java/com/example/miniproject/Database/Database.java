package com.example.miniproject.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.miniproject.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_Name = "EatDB.db";
    private static final int DB_VER = 1;
    public static SQLiteDatabase db;

    public Database(Context context) {
        super(context, DB_Name, null, DB_VER);
    }

    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qd = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ProductName", "ProductId", "Quantity", "Price", "Discount"};
        String sqlTable = "OrderDetail";
        qd.setTables(sqlTable);
        Cursor c = qd.query(db, sqlSelect, null, null, null, null, null);
        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))

                ));
            } while (c.moveToNext());
        }
        return result;

    }
    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) Values ('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);

    }
    public void cleanCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE From OrderDetail");
        db.execSQL(query);

    }

}
