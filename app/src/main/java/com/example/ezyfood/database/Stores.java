package com.example.ezyfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ezyfood.data.Cart;
import com.example.ezyfood.models.CartItem;
import com.example.ezyfood.models.Item;
import com.example.ezyfood.models.Store;
import com.example.ezyfood.models.StoreDetail;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

public class Stores extends SQLiteOpenHelper {
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

    SQLiteDatabase db;

    private static String DROP_STORES_TABLE_QUERY = "DROP TABLE IF EXISTS Store";
    private static String DROP_STORE_DETAILS_TABLE_QUERY = "DROP TABLE IF EXISTS StoreDetail";

    public Stores(Context ctx){
        super(ctx, "ezyfood", null, DatabaseVersion.VERSION);
//        createDatabase(this.getWritableDatabase());
        db = getWritableDatabase();
    }

    public void createDatabase(SQLiteDatabase db) {
        db.execSQL(CREATE_STORES_TABLE_QUERY);
        db.execSQL(CREATE_STORE_DETAILS_TABLE_QUERY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATA_TEST", "onCreate: " + "HEY HEY");
        createDatabase(db);
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STORES_TABLE_QUERY);
        db.execSQL(DROP_STORE_DETAILS_TABLE_QUERY);

        db.execSQL(CREATE_STORES_TABLE_QUERY);
        db.execSQL(CREATE_STORE_DETAILS_TABLE_QUERY);
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

    public String cursorToString(Cursor cursor){
        String cursorString = "";
        if (cursor.moveToFirst() ){
            String[] columnNames = cursor.getColumnNames();
            for (String name: columnNames)
                cursorString += String.format("%s ][ ", name);
            cursorString += "\n";
            do {
                for (String name: columnNames) {
                    cursorString += String.format("%s ][ ",
                            cursor.getString(cursor.getColumnIndex(name)));
                }
                cursorString += "\n";
            } while (cursor.moveToNext());
        }
        return cursorString;
    }

    public ArrayList<Store> getStores(Integer id) throws Exception{
        ArrayList<Store> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * " +
                "FROM STOREDETAIL JOIN STORE ON Store.id = STOREDETAIL.store_id JOIN PRODUCT ON Product.id = STOREDETAIL.product_id";
        if(id != null) {
            query += " WHERE STORE.id = " + id;
        }
        Cursor c = db.rawQuery(query,
                null);

        Log.d("DATA_TEST", cursorToString(c));
        HashMap<Integer, Store> storeHashMap = new HashMap<>();

        if(c.moveToFirst()){
            do {
                int storeId = c.getInt(0);
                Store store = storeHashMap.get(storeId);
                if(store == null) {
                    String address = c.getString(4);
                    double lat = c.getDouble(5);
                    double lng = c.getDouble(6);
                    Store add = new Store(storeId, address, new LatLng(lat, lng));
                    storeHashMap.put(storeId, add);
                    store = add;
                }

                int productId = c.getInt(1);
                int image = c.getInt(8);
                String category = c.getString(9);
                String name = c.getString(7);
                int price = c.getInt(10);

                int stock = c.getInt(2);
                Item item = new Item(productId, category, image, name, price);
                StoreDetail detail = new StoreDetail(item, stock);

                store.addDetail(detail);
            }while(c.moveToNext());
        }
        c.close();

        for(Store store : storeHashMap.values()) {
            list.add(store);
        }
        Log.d("DATA_TEST", "getStores: " + list.size() + "");

        return list;
    }

    public ArrayList<String> checkout(int storeId) {
        ArrayList<CartItem> carts = Cart.getCartItems();
        try {
            Store store = getStores(storeId).get(0);
            ArrayList<String> notEnoughStock = new ArrayList<>();
            for (CartItem cart : carts) {
                int prodId = cart.getItem().getId();
                boolean found = false;
                for (StoreDetail detail : store.details) {
                    if(prodId == detail.item.getId()) {
                        found = true;

                        if(cart.getQty() > detail.stock) {
                            notEnoughStock.add(cart.getItem().getName() + " Stock is not enough. Current Stock: " + detail.stock);
                        }
                        break;
                    }
                }

                if(!found) {
                    notEnoughStock.add(cart.getItem().getName() + " Stock is not enough. Current Stock: " + 0);
                }
            }

            return notEnoughStock;
        } catch(Exception e) {
            ArrayList<String> message = new ArrayList<>();
            message.add("Error!");
            return message;
        }
    }

    public void finalCheckout(int storeId) {
        ArrayList<CartItem> carts = Cart.getCartItems();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Store store = getStores(storeId).get(0);
            for (CartItem cart : carts) {
                int prodId = cart.getItem().getId();
                for (StoreDetail detail : store.details) {
                    if(prodId == detail.item.getId()) {
                        if(cart.getQty() < detail.stock) {
                            int finalStock = detail.stock - cart.getQty();
                            Log.d("FINAL_CHECKOUT", "finalCheckout: " + "UPDATE StoreDetail SET stock=" + finalStock + " WHERE store_id=" + storeId + " AND product_id=" + detail.item.getId());
                            db.execSQL("UPDATE StoreDetail SET stock=" + finalStock + " WHERE store_id=" + storeId + " AND product_id=" + detail.item.getId());
                        }
                        break;
                    }
                }
            }
        } catch(Exception e) {
        }
    }
}
