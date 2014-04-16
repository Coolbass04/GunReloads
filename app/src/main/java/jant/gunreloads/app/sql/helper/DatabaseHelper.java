package jant.gunreloads.app.sql.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jant.gunreloads.app.sql.model.Ammo;
import jant.gunreloads.app.sql.model.Bullet;
import jant.gunreloads.app.sql.model.Firearm;
import jant.gunreloads.app.sql.model.Manufacturer;
import jant.gunreloads.app.sql.model.Powder;
import jant.gunreloads.app.sql.model.Primer;
import jant.gunreloads.app.sql.model.Results;

/**
 * Created by Adam on 3/29/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Consts
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Database Name
    private static final String DATABASE_NAME = "AmmoReloads";

    // Table Names
    public static final String TABLE_AMMO = "ammo";
    public static final String TABLE_BULLET = "bullet";
    public static final String TABLE_FIREARM = "firearm";
    public static final String TABLE_MANUFACTURER = "manufacturer";
    public static final String TABLE_POWDER = "powder";
    public static final String TABLE_PRIMER = "primer";
    public static final String TABLE_RESULTS = "results";

    // Common Column Names
    public static final String KEY_ID = "id";
    public static final String KEY_CREATED_AT = "created_at";

    // Ammo table - column names
    public static final String AMMO_BULLET_ID = "bulletId";
    public static final String AMMO_POWDER_ID = "powderId";
    public static final String AMMO_PRIMER_ID = "primerId";
    public static final String AMMO_RESULTS_ID = "resultsId";
    public static final String AMMO_CARTRIDGE_LENGTH = "cartridgeLength";
    public static final String AMMO_CASE_LENGTH = "caseLength";
    public static final String AMMO_DATE_MANUFACTURED = "dateManufactured";
    public static final String AMMO_QUANTITY_MADE = "quantityMade";

    // Bullet table - column names
    public static final String BULLET_MANUFACTURER_ID = "manufacturerId";
    public static final String BULLET_CALIBER = "caliber";
    public static final String BULLET_STYLE = "style";
    public static final String BULLET_WEIGHT = "weight";

    // Firearm table - column names
    public static final String FIREARM_MANUFACTURER_ID = "manufacturerId";
    public static final String FIREARM_EXTRA_INFO = "extraInfo";
    public static final String FIREARM_MODEL = "model";

    // Manufacturer table - column names
    public static final String MANUFACTURER_NAME = "name";

    // Powder table - column names
    public static final String POWDER_MANUFACTURER_ID = "manufacturerId";
    public static final String POWDER_TYPE = "type";

    // Primer table - column names
    public static final String PRIMER_MANUFACTURER_ID = "manufacturerId";
    public static final String PRIMER_TYPE = "type";

    // Results table - column names
    public static final String RESULTS_FIREARM_ID = "firearmId";
    public static final String RESULTS_DATE_SHOT = "dateShot";
    public static final String RESULTS_EXPLANATION = "explanation";

    // *** Table Create Statements ***

    // Ammo table create statement
    private static final String CREATE_TABLE_AMMO = "CREATE TABLE "
            + TABLE_AMMO + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + AMMO_BULLET_ID + " INTEGER,"
            + AMMO_POWDER_ID + " INTEGER,"
            + AMMO_PRIMER_ID + " INTEGER,"
            + AMMO_RESULTS_ID + " INTEGER,"
            + AMMO_CARTRIDGE_LENGTH + " TEXT,"
            + AMMO_CASE_LENGTH + " TEXT,"
            + AMMO_DATE_MANUFACTURED + " DATETIME,"
            + AMMO_QUANTITY_MADE + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Bullet table create statement
    private static final String CREATE_TABLE_BULLET = "CREATE TABLE "
            + TABLE_BULLET + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + BULLET_MANUFACTURER_ID + " TEXT,"
            + BULLET_CALIBER + " TEXT,"
            + BULLET_STYLE + " TEXT,"
            + BULLET_WEIGHT + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Firearm table create statement
    private static final String CREATE_TABLE_FIREARM = "CREATE TABLE "
            + TABLE_FIREARM + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + FIREARM_MANUFACTURER_ID + " INTEGER,"
            + FIREARM_EXTRA_INFO + " TEXT,"
            + FIREARM_MODEL + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Manufacturer table create statement
    private static final String CREATE_TABLE_MANUFACTURER = "CREATE TABLE "
            + TABLE_MANUFACTURER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + MANUFACTURER_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Powder table create statement
    private static final String CREATE_TABLE_POWDER = "CREATE TABLE "
            + TABLE_POWDER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + POWDER_MANUFACTURER_ID + " TEXT,"
            + POWDER_TYPE + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Powder table create statement
    private static final String CREATE_TABLE_PRIMER = "CREATE TABLE "
            + TABLE_PRIMER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + PRIMER_MANUFACTURER_ID + " TEXT,"
            + PRIMER_TYPE + " DATETIME,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Results table create statement
    private static final String CREATE_TABLE_RESULTS = "CREATE TABLE "
            + TABLE_RESULTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + RESULTS_FIREARM_ID + " INTEGER,"
            + RESULTS_DATE_SHOT + " DATETIME,"
            + RESULTS_EXPLANATION + " TEXT,"
            + PRIMER_TYPE + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(LOG, "Creating Databases");
        // creating required tables
        db.execSQL(CREATE_TABLE_AMMO);
        db.execSQL(CREATE_TABLE_BULLET);
        db.execSQL(CREATE_TABLE_FIREARM);
        db.execSQL(CREATE_TABLE_MANUFACTURER);
        db.execSQL(CREATE_TABLE_POWDER);
        db.execSQL(CREATE_TABLE_PRIMER);
        db.execSQL(CREATE_TABLE_RESULTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AMMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BULLET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIREARM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POWDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);

        // create new tables
        onCreate(db);
    }

    public void closeDb() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    // *** Helpers for Ammo Table ***
    public long createAmmo(Ammo ammo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AMMO_BULLET_ID, ammo.getBulletId());
        values.put(AMMO_POWDER_ID, ammo.getPowderId());
        values.put(AMMO_PRIMER_ID, ammo.getPrimerId());
        //values.put(AMMO_RESULTS_ID, ammo.getResultsId());
        values.put(AMMO_CASE_LENGTH, ammo.getCaseLength());
        values.put(AMMO_CARTRIDGE_LENGTH, ammo.getCartridgeLength());
        values.put(AMMO_DATE_MANUFACTURED, DATE_FORMAT.format(ammo.getDateManufactured()));
        values.put(AMMO_QUANTITY_MADE, ammo.getQuantityMade());

        long ammoId = db.insert(TABLE_AMMO, null, values);
        db.close();
        return ammoId;
    }

    public Ammo getAmmo(long ammoId) {
        Cursor c = selectAllFromTable(TABLE_AMMO, ammoId);
        if(c == null) {
            return null;
        }

        Ammo ammo = new Ammo();
        ammo.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        ammo.setBulletId(c.getLong(c.getColumnIndex(AMMO_BULLET_ID)));
        ammo.setPowderId(c.getLong(c.getColumnIndex(AMMO_POWDER_ID)));
        ammo.setPrimerId(c.getLong(c.getColumnIndex(AMMO_PRIMER_ID)));
        ammo.setResultsId(c.getLong(c.getColumnIndex(AMMO_RESULTS_ID)));
        ammo.setCaseLength(c.getFloat(c.getColumnIndex(AMMO_CASE_LENGTH)));
        ammo.setCartridgeLength(c.getFloat(c.getColumnIndex(AMMO_CARTRIDGE_LENGTH)));
        Date date = null;
        try {
            date = DATE_FORMAT.parse(c.getString(c.getColumnIndex(AMMO_DATE_MANUFACTURED)));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        ammo.setDateManufactured(date);
        ammo.setQuantityMade(c.getInt(c.getColumnIndex(AMMO_QUANTITY_MADE)));

        closeDb();
        return ammo;
    }

    // *** Helpers for Bullet Table ***
    public long createBullet(Bullet bullet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BULLET_CALIBER, bullet.getCaliber());
        values.put(BULLET_MANUFACTURER_ID, bullet.getManufacturerId());
        values.put(BULLET_STYLE, bullet.getStyle());
        values.put(BULLET_WEIGHT, bullet.getWeight());

        long bulletId = db.insert(TABLE_BULLET, null, values);
        db.close();
        return bulletId;
    }

    public Bullet getBullet(long bulletId) {
        Cursor c = selectAllFromTable(TABLE_BULLET, bulletId);
        if(c == null) {
            return null;
        }

        Bullet bullet = new Bullet();
        bullet.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        bullet.setCaliber(c.getString(c.getColumnIndex(BULLET_CALIBER)));
        bullet.setManufacturerId(c.getLong(c.getColumnIndex(BULLET_MANUFACTURER_ID)));
        bullet.setStyle(c.getString(c.getColumnIndex(BULLET_STYLE)));
        bullet.setWeight(c.getString(c.getColumnIndex(BULLET_WEIGHT)));

        closeDb();
        return bullet;
    }

    // *** Helpers for Firearm Table ***
    public long createFirearm(Firearm firearm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIREARM_EXTRA_INFO, firearm.getExtraInfo());
        values.put(FIREARM_MANUFACTURER_ID, firearm.getManufacturerId());
        values.put(FIREARM_MODEL, firearm.getModel());

        long firearmId = db.insert(TABLE_FIREARM, null, values);
        db.close();
        return firearmId;
    }

    public Firearm getFirearm(long firearmId) {
        Cursor c = selectAllFromTable(TABLE_FIREARM, firearmId);
        if(c == null) {
            return null;
        }

        Firearm firearm = new Firearm();
        firearm.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        firearm.setExtraInfo(c.getString(c.getColumnIndex(FIREARM_EXTRA_INFO)));
        firearm.setManufacturerId(c.getLong(c.getColumnIndex(FIREARM_MANUFACTURER_ID)));
        firearm.setModel(c.getString(c.getColumnIndex(FIREARM_MODEL)));
        closeDb();
        return firearm;
    }

    // *** Helpers for Manufacturer Table ***
    public long createManufacturer(Manufacturer manufacturer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MANUFACTURER_NAME, manufacturer.getName());

        long manufacturerId = db.insert(TABLE_MANUFACTURER, null, values);
        db.close();
        return manufacturerId;
    }

    public Manufacturer getManufacturer(long manufacturerId) {
        Cursor c = selectAllFromTable(TABLE_MANUFACTURER, manufacturerId);
        if(c == null) {
            return null;
        }

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        manufacturer.setName(c.getString(c.getColumnIndex(MANUFACTURER_NAME)));
        closeDb();
        return manufacturer;
    }

    // @returns id of the manufacturer name if found, otherwise null
    public long findManufacturerByName(String manufacturerName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_ID + " FROM " + TABLE_MANUFACTURER + " WHERE " + MANUFACTURER_NAME + " = \"" + manufacturerName + "\"";
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null && c.getColumnIndex(KEY_ID) > 0) {
            c.moveToFirst();
            return c.getLong(c.getColumnIndex(KEY_ID));
        }
        else {
            return 0;
        }
    }

    // *** Helpers for Powder Table ***
    public long createPowder(Powder powder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POWDER_MANUFACTURER_ID, powder.getManufacturerId());
        values.put(POWDER_TYPE, powder.getType());

        long powderId = db.insert(TABLE_POWDER, null, values);
        db.close();
        return powderId;
    }

    public Powder getPowder(long powderId) {
        Cursor c = selectAllFromTable(TABLE_POWDER, powderId);
        if(c == null) {
            return null;
        }

        Powder powder = new Powder();
        powder.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        powder.setManufacturerId(c.getLong(c.getColumnIndex(POWDER_MANUFACTURER_ID)));
        powder.setType(c.getString(c.getColumnIndex(POWDER_TYPE)));
        closeDb();
        return powder;
    }

    // *** Helpers for Primer Table ***
    public long createPrimer(Primer primer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRIMER_MANUFACTURER_ID, primer.getManufacturerId());
        values.put(PRIMER_TYPE, primer.getType());

        long primerId = db.insert(TABLE_PRIMER, null, values);
        db.close();
        return primerId;
    }

    public Primer getPrimer(long primerId) {
        Cursor c = selectAllFromTable(TABLE_PRIMER, primerId);
        if(c == null) {
            return null;
        }

        Primer primer = new Primer();
        primer.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        primer.setManufacturerId(c.getLong(c.getColumnIndex(PRIMER_MANUFACTURER_ID)));
        primer.setType(c.getString(c.getColumnIndex(PRIMER_TYPE)));
        closeDb();
        return primer;
    }

    // *** Helpers for Results Table ***
    public long createResults(Results results) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESULTS_DATE_SHOT, DATE_FORMAT.format(results.getDateShot()));
        values.put(RESULTS_EXPLANATION, results.getExplanation());
        values.put(RESULTS_FIREARM_ID, results.getFirearmId());

        long resultsId = db.insert(TABLE_RESULTS, null, values);
        db.close();
        return resultsId;
    }

    public Results getResults(long resultsId) {
        Cursor c = selectAllFromTable(TABLE_RESULTS, resultsId);
        if(c == null) {
            return null;
        }

        Results results = new Results();
        results.setId(c.getLong(c.getColumnIndex(KEY_ID)));
        Date date = null;
        try {
            date = DATE_FORMAT.parse(c.getString(c.getColumnIndex(RESULTS_DATE_SHOT)));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        results.setDateShot(date);
        results.setExplanation(c.getString(c.getColumnIndex(RESULTS_EXPLANATION)));
        results.setFirearmId(c.getLong(c.getColumnIndex(RESULTS_FIREARM_ID)));
        closeDb();
        return results;
    }

    private Cursor selectAllFromTable(String tableName, long keyId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + tableName + " WHERE " + KEY_ID + " = " + keyId;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null) {
            c.moveToFirst();
            return c;
        }
        else {
            return null;
        }
    }
}