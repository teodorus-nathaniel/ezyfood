package com.example.ezyfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ezyfood.R;
import com.example.ezyfood.models.Item;

import java.util.ArrayList;

public class Products extends SQLiteOpenHelper {
    private static String CREATE_STORES_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS Store(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "address TEXT," +
            "lat REAL," +
            "lng REAL)";

    private static String CREATE_STORE_DETAILS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS StoreDetail(" +
            "store_id INTEGER," +
            "product_id INTEGER," +
            "stock INTEGER" +
            ")";

    private static String DROP_STORES_TABLE_QUERY = "DROP TABLE IF EXISTS Store";
    private static String DROP_STORE_DETAILS_TABLE_QUERY = "DROP TABLE IF EXISTS StoreDetail";

    private static String CREATE_PRODUCT_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS Product(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "image INTEGER," +
            "category TEXT," +
            "price TEXT)";

    private static String DROP_PRODUCT_TABLE_QUERY = "DROP TABLE IF EXISTS Product";

    public Products(Context ctx){
        super(ctx, "ezyfood", null, DatabaseVersion.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCT_TABLE_QUERY);
        db.execSQL(CREATE_STORES_TABLE_QUERY);
        db.execSQL(CREATE_STORE_DETAILS_TABLE_QUERY);
        initializeMenu(db);
        initializeStores(db);
        initializeStoreDetails(db);
    }

    public void initializeStores(SQLiteDatabase db) {
        this.insertStore(db, "Puri Indah", -6.181281, 106.729620);
        this.insertStore(db, "Binus University",  -6.224179, 106.750522);
        this.insertStore(db, "Binus1 University",  -6.154179, 106.680522);
        this.insertStore(db, "Binus2 University",  -6.204179, 106.690522);
        this.insertStore(db, "Binus3 University",  -6.254179, 106.800522);
        this.insertStore(db, "Binus4 University",  -6.104179, 106.900522);
        this.insertStore(db, "Binus5 University",  -6.304179, 106.300522);
        this.insertStore(db, "Binus6 University",  -6.354179, 106.500522);
    }

    public void initializeStoreDetails(SQLiteDatabase db) {
        this.insertStoreDetail(db, 1, 1, 10);
        this.insertStoreDetail(db, 1, 2, 10);
        this.insertStoreDetail(db, 1, 3, 10);
        this.insertStoreDetail(db, 1, 4, 10);
        this.insertStoreDetail(db, 1, 5, 10);
        this.insertStoreDetail(db, 1, 6, 20);
        this.insertStoreDetail(db, 1, 7, 15);
        this.insertStoreDetail(db, 1, 8, 5);
        this.insertStoreDetail(db, 1, 9, 0);

        this.insertStoreDetail(db, 2, 1, 10);
        this.insertStoreDetail(db, 2, 2, 10);
        this.insertStoreDetail(db, 2, 3, 10);
        this.insertStoreDetail(db, 2, 4, 10);
        this.insertStoreDetail(db, 2, 5, 10);
        this.insertStoreDetail(db, 2, 6, 20);
        this.insertStoreDetail(db, 2, 7, 15);
        this.insertStoreDetail(db, 2, 8, 5);
        this.insertStoreDetail(db, 2, 9, 0);

        for(int i=3; i<=8; i++) {
            this.insertStoreDetail(db, i, 1, 10);
            this.insertStoreDetail(db, i, 2, 10);
            this.insertStoreDetail(db, i, 3, 10);
            this.insertStoreDetail(db, i, 4, 10);
            this.insertStoreDetail(db, i, 5, 10);
            this.insertStoreDetail(db, i, 6, 20);
            this.insertStoreDetail(db, i, 7, 15);
            this.insertStoreDetail(db, i, 8, 5);
            this.insertStoreDetail(db, i, 9, 0);
        }
    }
    public void initializeMenu(SQLiteDatabase db) {
        this.insert(db, "drink", R.drawable.ic_water, "Mineral Water", 6000);
        this.insert(db, "drink", R.drawable.ic_orange_juice, "Orange Juice", 15000);
        this.insert(db, "drink", R.drawable.ic_thai_tea, "Thai Tea", 25000);

        this.insert(db, "food", R.drawable.ic_fried_rice, "Fried Rice", 30000);
        this.insert(db, "food", R.drawable.ic_fried_chicken, "Fried Chicken", 25000);
        this.insert(db, "food", R.drawable.ic_chicken_soup, "Chicken Soup", 20000);

        this.insert(db, "snack", R.drawable.ic_nachos, "Nachos", 20000);
        this.insert(db, "snack", R.drawable.ic_fried_wedges, "Fried Potato Wedges", 25000);
        this.insert(db, "snack", R.drawable.ic_salad, "Salad", 12500);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_PRODUCT_TABLE_QUERY);
        db.execSQL(DROP_STORES_TABLE_QUERY);
        db.execSQL(DROP_STORE_DETAILS_TABLE_QUERY);

        db.execSQL(CREATE_PRODUCT_TABLE_QUERY);
        db.execSQL(CREATE_STORES_TABLE_QUERY);
        db.execSQL(CREATE_STORE_DETAILS_TABLE_QUERY);
        initializeMenu(db);
        initializeStores(db);
        initializeStoreDetails(db);
    }

    public void insertStore(SQLiteDatabase db, String address, double lat, double lng){
        boolean isNull = db == null;
        if(isNull) {
            db = this.getWritableDatabase();
        }
        ContentValues cv = new ContentValues();
        cv.put("address", address);
        cv.put("lat", lat);
        cv.put("lng", lng);
        db.insert("Store", null, cv);
        if(isNull) {
            db.close();
        }
    }

    public void insertStoreDetail(SQLiteDatabase db, int storeId, int productId, int stock){
        boolean isNull = db == null;
        if(isNull) {
            db = this.getWritableDatabase();
        }
        ContentValues cv = new ContentValues();
        cv.put("store_id", storeId);
        cv.put("product_id", productId);
        cv.put("stock", stock);
        db.insert("StoreDetail", null, cv);
        if(isNull) {
            db.close();
        }
    }


    public void insert(SQLiteDatabase db, String category, int image, String name, int price){
        boolean isNull = db == null;
        if(isNull) {
            db = this.getWritableDatabase();
        }
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("image", image);
        cv.put("name", name);
        cv.put("price", price);
        db.insert("Product", null, cv);
        if(isNull) {
            db.close();
        }
    }

    public ArrayList<Item> getItem(String query) throws Exception{
        ArrayList<Item> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToNext()){
            c.moveToPrevious();
            while(c.moveToNext()){
                int id = c.getInt(0);
                String name = c.getString(1);
                int image = c.getInt(2);
                String category = c.getString(3);
                int price = c.getInt(4);
                Item p = new Item(id, category, image, name, price);
                list.add(p);
            }
        }
        c.close();
        return list;
    }

    public ArrayList<Item> getDrinks() throws Exception {
        return getItem("SELECT * FROM PRODUCT WHERE category = 'drink'");
    }

    public ArrayList<Item> getFoods() throws Exception {
        return getItem("SELECT * FROM PRODUCT WHERE category = 'food'");
    }

    public ArrayList<Item> getSnacks() throws Exception {
        return getItem("SELECT * FROM PRODUCT WHERE category = 'snack'");
    }
}
