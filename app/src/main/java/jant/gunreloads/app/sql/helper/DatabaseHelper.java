package jant.gunreloads.app.sql.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import jant.gunreloads.app.sql.model.Bullet;
import jant.gunreloads.app.sql.model.Enums;

/**
 * Created by Adam on 3/29/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AmmoReloads";

    // Table Names
    private static final String TABLE_BULLET = "bullet";
    private static final String TABLE_POWDER = "power";

    // Common Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // Bullet table - column names
    public static final String BULLET_MANUFACTURER = "manufacturer";
    public static final String BULLET_STYLE = "style";
    public static final String BULLET_WEIGHT = "weight";
    public static final String BULLET_CALIBER = "caliber";

    // Power table - column names
    public static final String POWDER_MANUFACTURER = "manufacturer";
    public static final String POWDER_TYPE = "type";

    // *** Table Create Statements ***
    // Bullet table create statement
    private static final String CREATE_TABLE_BULLET = "CREATE TABLE "
            + TABLE_BULLET + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + BULLET_MANUFACTURER + " TEXT,"
            + BULLET_WEIGHT + " TEXT,"
            + BULLET_CALIBER + " TEXT,"
            + BULLET_STYLE + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Power table create statement
    private static final String CREATE_TABLE_POWDER = "CREATE TABLE "
            + TABLE_POWDER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + POWDER_MANUFACTURER + " TEXT,"
            + POWDER_TYPE + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_BULLET);
        db.execSQL(CREATE_TABLE_POWDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BULLET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POWDER);

        // create new tables
        onCreate(db);
    }

    public void closeDb() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    // *** Helpers for Bullet Table ***
    public long createBullet(Bullet bullet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BULLET_CALIBER, bullet.getCaliber().name());
        values.put(BULLET_MANUFACTURER, bullet.getManufacturer());
        values.put(BULLET_STYLE, bullet.getStyle());
        values.put(BULLET_WEIGHT, bullet.getWeight());

        long bulletId = db.insert(TABLE_BULLET, null, values);
        db.close();
        return bulletId;
    }

    public Bullet getBullet(long bulletId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BULLET + " WHERE " + KEY_ID + " = " + bulletId;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null) {
            c.moveToFirst();
        }
        else {
            return null;
        }

        Bullet bullet = new Bullet();
        bullet.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        bullet.setCaliber(Enums.Caliber.valueOf(c.getString(c.getColumnIndex(BULLET_CALIBER))));
        bullet.setManufacturer(c.getString(c.getColumnIndex(BULLET_MANUFACTURER)));
        bullet.setStyle(c.getString(c.getColumnIndex(BULLET_STYLE)));
        bullet.setWeight(c.getString(c.getColumnIndex(BULLET_WEIGHT)));

        db.close();
        return bullet;
    }
}
