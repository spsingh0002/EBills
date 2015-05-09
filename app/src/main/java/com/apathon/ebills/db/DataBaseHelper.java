package com.apathon.ebills.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.apathon.ebills.models.Bill_Pic;
import com.apathon.ebills.models.Item;
import com.apathon.ebills.models.Seller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/electronics.payu.com.electronics/databases/";

    private static String DB_NAME = "store.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public static final String Table_android_metadata = "android_metadata";

    public static final String Table_bill_pic = "bill_pic";
    public static final String Table_item = "item";
    public static final String Table_seller = "seller";

    public static final String Column__id = "_id";
    public static final String Column_image_path = "image_path";
    public static final String Column_image_name = "image_name";
    public static final String Column_image_date = "image_date";
    public static final String Column_image_seller_id = "image_seller_id";

    public static final String Column_item_name = "item_name";
    public static final String Column_item_desc = "item_desc";
    public static final String Column_item_amount = "item_amount";
    public static final String Column_order_no = "order_no";
    public static final String Column_invoice_no = "invoice_no";
    public static final String Column_item_tag = "item_tag";
    public static final String Column_warranty = "warranty";
    public static final String Column_item_seller_id = "item_seller_id";
    public static final String Column_bill_pic_id = "bill_pic_id";

    public static final String Column_seller = "seller";
    public static final String Column_seller_address = "seller_address";
    public static final String Column_seller_city = "seller_city";
    public static final String Column_seller_state = "seller_state";
    public static final String Column_seller_pin = "seller_pin";
    public static final String Column_seller_tag = "seller_tag";


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 12);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }


    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    /**
     *
     * @return all sellers
     */
    public ArrayList<Seller> getAllSellers() {
        String[] projection = {Column__id, Column_seller, Column_seller_address, Column_seller_city, Column_seller_state, Column_seller_pin, Column_seller_tag};
        Cursor cursor = null;
        ArrayList<Seller> sellers = new ArrayList<>();
        try {
            cursor = getReadableDatabase().query(Table_seller, projection, null, null, null, null, null);
            cursor.moveToFirst();
            do {
                Seller seller = new Seller();
                seller.setColumn__id(cursor.getString(cursor.getColumnIndex(Column__id)));
                seller.setColumn_seller(cursor.getString(cursor.getColumnIndex(Column_seller)));
                seller.setColumn_seller_address(cursor.getString(cursor.getColumnIndex(Column_seller_address)));
                seller.setColumn_seller_city(cursor.getString(cursor.getColumnIndex(Column_seller_city)));
                seller.setColumn_seller_state(cursor.getString(cursor.getColumnIndex(Column_seller_state)));
                seller.setColumn_seller_pin(cursor.getString(cursor.getColumnIndex(Column_seller_pin)));
                seller.setColumn_seller_tag(cursor.getString(cursor.getColumnIndex(Column_seller_tag)));
                sellers.add(seller);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCursor(cursor);
        }
        return sellers;
    }


    /**
     *
     * @return all Items
     */
    public ArrayList<Item> getAllItems() {
        String[] projection = {Column__id, Column_item_name, Column_item_desc,Column_item_amount,Column_order_no,Column_invoice_no,Column_item_tag,Column_warranty,Column_item_seller_id},Column_bill_pic_id;
        Cursor cursor = null;
        ArrayList<Item> items = new ArrayList<>();
        try {
            cursor = getReadableDatabase().query(Table_item, projection, null, null, null, null, null);
            cursor.moveToFirst();
            do {
                Item item = new Item();
                item.setColumn__id(cursor.getString(cursor.getColumnIndex(Column__id)));
                item.setColumn_item_name(cursor.getString(cursor.getColumnIndex(Column_item_name)));
                item.setColumn_item_amount(cursor.getString(cursor.getColumnIndex(Column_item_amount)));
                item.setColumn_order_no(cursor.getString(cursor.getColumnIndex(Column_order_no)));
                item.setColumn_invoice_no(cursor.getString(cursor.getColumnIndex(Column_invoice_no)));
                item.setColumn_item_tag(cursor.getString(cursor.getColumnIndex(Column_item_tag)));
                item.setColumn_warranty(cursor.getString(cursor.getColumnIndex(Column_warranty)));
                item.setColumn_item_seller_id(cursor.getString(cursor.getColumnIndex(Column_item_seller_id)));
                items.add(item);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCursor(cursor);
        }
        return items;
    }

    /**
     *
     * @return all BillsPics
     */
    public ArrayList<Bill_Pic> getAllBill_Pics() {
        String[] projection = {Column__id, Column_image_path, Column_image_name, Column_image_date};
        Cursor cursor = null;
        ArrayList<Bill_Pic> quotes = new ArrayList<>();
        try {
            cursor = getReadableDatabase().query(Table_bill_pic, projection, null, null, null, null, null);
            cursor.moveToFirst();
            do {
                Bill_Pic quote = new Bill_Pic();
                quote.setColumn__id(cursor.getString(cursor.getColumnIndex(Column__id)));
                quote.setColumn_image_path(cursor.getString(cursor.getColumnIndex(Column_image_path)));
                quote.setColumn_image_name(cursor.getString(cursor.getColumnIndex(Column_image_name)));
                quote.setColumn_image_date(cursor.getString(cursor.getColumnIndex(Column_image_date)));
                quote.setColumn_image_seller_id(cursor.getString(cursor.getColumnIndex(Column_image_seller_id)));
                quotes.add(quote);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCursor(cursor);
        }
        return quotes;
    }


    /**
     *
     * @param item is the prepared object of Seller class
     * @return -1 for error , row count on success
     */
    public  long insertItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        long i = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put(item.getColumn_item_name(), Column_item_name);
            cv.put(item.getColumn_item_amount(), Column_item_amount);
            cv.put(item.getColumn_order_no(),Column_order_no);
            cv.put(item.getColumn_order_no(),Column_invoice_no);
            cv.put(item.getColumn_order_no(),Column_item_tag);
            cv.put(item.getColumn_order_no(),Column_warranty);
            cv.put(item.getColumn_order_no(),Column_item_seller_id);
            i = db.insert(Table_item, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return i;
    }


    /**
     *
     * @param seller is the prepared object of Seller class
     * @return -1 for error , row count on success
     */
    public long insertSeller(Seller seller) {
        SQLiteDatabase db = getWritableDatabase();
        long i = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put(seller.getColumn_seller(), Column_seller);
            cv.put(seller.getColumn_seller_address(), Column_seller_address);
            cv.put(seller.getColumn_seller_city(),Column_seller_city);
            cv.put(seller.getColumn_seller_state(),Column_seller_state);
            cv.put(seller.getColumn_seller_pin(),Column_seller_pin);
            cv.put(seller.getColumn_seller_tag(),Column_seller_tag);
            i = db.insert(Table_seller, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return i;
    }


    /**
     *
     * @param column_value is the value to search
     * @param column_KEY is the key of the column in databsae
     * @return List of Sellers depending on params
     */
    public ArrayList<Seller> searchSeller(String column_value, String column_KEY) {
        String[] projection = {Column__id, Column_seller, Column_seller_address, Column_seller_city, Column_seller_state, Column_seller_pin, Column_seller_tag};

        Cursor cursor = null;
        ArrayList<Seller> sellers = new ArrayList<>();
        Seller seller= null;
        try {
            cursor = getReadableDatabase().query(Table_seller, projection, column_KEY + "=?", new String[]{column_value}, null, null, null);
            cursor.moveToFirst();
            do {
                seller = new Seller();
                seller.setColumn__id(cursor.getString(cursor.getColumnIndex(Column__id)));
                seller.setColumn_seller(cursor.getString(cursor.getColumnIndex(Column_seller)));
                seller.setColumn_seller_address(cursor.getString(cursor.getColumnIndex(Column_seller_address)));
                seller.setColumn_seller_city(cursor.getString(cursor.getColumnIndex(Column_seller_city)));
                seller.setColumn_seller_state(cursor.getString(cursor.getColumnIndex(Column_seller_state)));
                seller.setColumn_seller_pin(cursor.getString(cursor.getColumnIndex(Column_seller_pin)));
                seller.setColumn_seller_tag(cursor.getString(cursor.getColumnIndex(Column_seller_tag)));
                sellers.add(seller);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCursor(cursor);
        }
        return sellers;
    }

    /**
     *
     * @param column_value is the value to search
     * @param column_KEY is the key of the column in databsae
     * @return List of Sellers depending on params
     */
    public ArrayList<Item> searchItem(String column_value, String column_KEY) {
        String[] projection = {Column__id, Column_item_name, Column_item_desc,Column_item_amount,Column_order_no,Column_invoice_no,Column_item_tag,Column_warranty,Column_item_seller_id},Column_bill_pic_id;
        Cursor cursor = null;
        ArrayList<Item> items = new ArrayList<>();
        Seller seller= null;
        try {
            cursor = getReadableDatabase().query(Table_item, projection, column_KEY + "=?", new String[]{column_value}, null, null, null);
            cursor.moveToFirst();
            do {
                Item item = new Item();
                item.setColumn__id(cursor.getString(cursor.getColumnIndex(Column__id)));
                item.setColumn_item_name(cursor.getString(cursor.getColumnIndex(Column_item_name)));
                item.setColumn_item_amount(cursor.getString(cursor.getColumnIndex(Column_item_amount)));
                item.setColumn_order_no(cursor.getString(cursor.getColumnIndex(Column_order_no)));
                item.setColumn_invoice_no(cursor.getString(cursor.getColumnIndex(Column_invoice_no)));
                item.setColumn_item_tag(cursor.getString(cursor.getColumnIndex(Column_item_tag)));
                item.setColumn_warranty(cursor.getString(cursor.getColumnIndex(Column_warranty)));
                item.setColumn_item_seller_id(cursor.getString(cursor.getColumnIndex(Column_item_seller_id)));
                items.add(item);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCursor(cursor);
        }
        return items;
    }


    /**
     *
     * @param _id unique id of seller
     * @param seller updated object of seller
     * @return
     */
    public long updateSeller( String _id, Seller seller) {
        SQLiteDatabase db = getWritableDatabase();
        long i = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put(seller.getColumn_seller(), Column_seller);
            cv.put(seller.getColumn_seller_address(), Column_seller_address);
            cv.put(seller.getColumn_seller_city(),Column_seller_city);
            cv.put(seller.getColumn_seller_state(),Column_seller_state);
            cv.put(seller.getColumn_seller_pin(),Column_seller_pin);
            cv.put(seller.getColumn_seller_tag(),Column_seller_tag);
            i = db.update(Table_seller,cv,Column__id + "=?",new String[]{_id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return i;
    }

    /**
     *
     * @param _id unique id of item
     * @param item the updated object of Item class
     * @return
     */
    public long updateItem( String _id, Item item) {
        SQLiteDatabase db = getWritableDatabase();
        long i = -1;
        try {
            ContentValues cv = new ContentValues();
            cv.put(item.getColumn_item_name(), Column_item_name);
            cv.put(item.getColumn_item_amount(), Column_item_amount);
            cv.put(item.getColumn_order_no(),Column_order_no);
            cv.put(item.getColumn_order_no(),Column_invoice_no);
            cv.put(item.getColumn_order_no(),Column_item_tag);
            cv.put(item.getColumn_order_no(),Column_warranty);
            cv.put(item.getColumn_order_no(),Column_item_seller_id);
            i = db.update(Table_seller,cv,Column__id + "=?",new String[]{_id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return i;
    }

    private static final void closeCursor(Cursor cursor) {
        try {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

}