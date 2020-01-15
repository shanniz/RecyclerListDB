package com.example.shan.recyclerlistDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String KeyID = "id";
    private static final String DBName = "Manufacturers";
    private  static  final String TableName = "Manufacturer";
    private  static  final String Name = "Manufacturer_name";
    private  static  final String Phone = "phone_number";
    private  static  final String Address = "address";
    private Context context;
    //
    private static DBHelper singleton;

    public static DBHelper getDBHelper(Context context) {
        if (singleton == null) {
            singleton = new DBHelper(context);
        }
        singleton.context = context;
        return singleton;
    }

    private DBHelper(Context context) {
        super(context, DBName, null, 1);
        this.context = context;
    }


    // Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+ TableName +"("
                + KeyID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Name + " TEXT NOT NULL,"
                + Phone + "  TEXT,"
                + Address + "  TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        // Create tables again
        onCreate(db);
    }

    public void insertManufacturer(String name, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, name);
        contentValues.put(Address, address);
        db.insert(TableName, null, contentValues);
    }

    public Manufacturer getManufacturer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery("SELECT * FROM " + TableName + " WHERE " + KeyID + "=?",
                new String[] {Integer.toString(id)} );

        if (cursor != null)
            cursor.moveToFirst();

        Manufacturer oem = new Manufacturer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return oem;
    }

    // Get all Manufacturers
    public List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> mList = new ArrayList<Manufacturer>();
        String selectQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Manufacturer oem = new Manufacturer(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                mList.add(oem);
            } while (cursor.moveToNext());
        }
        return mList;
    }

    // Update manufacturer
    public int updateManufacturer(Manufacturer oem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Name, oem.getName());
        values.put(Phone, oem.getPhone());
        values.put(Address, oem.getAddress());

        // updating row
        return db.update(TableName, values, KeyID + " = ?",
                new String[] { String.valueOf(oem.getId()) });
    }

    // Delete a manufacturer
    public void deleteManufacturer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName, KeyID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*// Getting Manufacturers Count
    public int getManufacturersCount() {
        String countQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }*/
}
