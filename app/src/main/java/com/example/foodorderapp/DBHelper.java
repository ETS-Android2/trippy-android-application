package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Database name
    private final static String DB_NAME = "foodorder.db";
    //Database Table name
    private final static String TABLE_NAME = "ORDERS";
    // Database version
    private final static int DB_VERSION = 7;
    // Table columns
    public static final String ID = "id";
    //public static final String NAME = "name";
    //public static final String EMAIL = "email";
    //public static final String PHONE = "phone";
    public static final String PRICE = "price";
    public static final String FOODNAME = "foodname";
    //public static final String QUANTITY = "quantity";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    // Create Table
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            //+ NAME + " TEXT NOT NULL,"
            //+ EMAIL + " TEXT NOT NULL,"
            //+ PHONE + " TEXT NOT NULL,"
            + PRICE + " TEXT NOT NULL,"
            + FOODNAME + " TEXT NOT NULL,"
            //+ QUANTITY + " INTEGER NOT NULL,"
            + DESCRIPTION + " TEXT NOT NULL,"
            + IMAGE + " INTEGER NOT NULL);";


    // Constructor
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Creating Orders Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // Whenever user order new food , the older table will get deleted
    // and new table is created on that with the updated data
     @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // INSERTING data into  Orders table using ContentValues
    public boolean insertOrder(int price, String foodname, String desc, int image) {

        /*
        id = 0
        name =
        email =
        phone =
        price = 1
         foodName = 2
        description = 3
        quantity =
        image = 4
        */
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("NAME", name);
        //values.put("EMAIL", email);
        //values.put("PHONE", phone);
        values.put("PRICE", price);
        values.put("FOODNAME", foodname);
        values.put("DESCRIPTION", desc);
        values.put("IMAGE", image);
        //values.put("QUANTITY", quantity);

        long result = database.insert("ORDERS", null, values);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    // Read data from the table using Cursor and display in orders activity
    public ArrayList<OrdersModel> getOrders() {

        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT id,foodname,image,price FROM ORDERS", null);

        while (cursor.moveToNext()) {
            OrdersModel model = new OrdersModel();
            model.setOrderNumber(cursor.getInt(0) + "");
            model.setSoldItemName(cursor.getString(cursor.getColumnIndex("foodname")));
            model.setOrderImage(cursor.getInt(cursor.getColumnIndex("image")));
            model.setPrice(cursor.getString(Integer.parseInt(cursor.getColumnIndex("price") + "")));
            orders.add(model);
        }
        cursor.close();
        database.close();
        return orders;
    }

    // Cursor will get data from table and pass it to detailActivity for UPDATE
    public Cursor getOrderById(int id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ORDERS WHERE id= " + id, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

     //UPDATING data in orderActivity
    // Orders are updated related to particular ID an order has
    /*public boolean updateOrder(String name, String email, String phone, int price, int image, String desc, String foodName, int quantity, int id) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("NAME", name);
        //values.put("EMAIL", email);
        //values.put("PHONE", phone);
        values.put("PRICE", price);
        values.put("DESCRIPTION", desc);
        values.put("FOODNAME", foodName);
        values.put("QUANTITY", quantity);
        values.put("IMAGE", image);

        long row = database.update("ORDERS", values, "id=" + id, null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }*/

    // DELETING data with particular id
    public int deleteOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("ORDERS", "id=" + id, null);
    }

    // Get Grand Total
    public int addAllValues(){

        int total = 0;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor c = database.rawQuery("SELECT SUM(PRICE) FROM ORDERS", null);
        if(c.moveToFirst()){
            total = c.getInt(0);
        }
        return total;
    }
}
