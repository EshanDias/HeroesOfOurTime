package com.assignments.sliit.heroesofourtime.dbAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignments.sliit.heroesofourtime.model.Hero;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by esh_d on 21/03/2018.
 * DB connectivity and functions
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat TAG
    private static final String TAG = "DatabaseHelper";
    private final Context myContext;
    private String pathToSaveDBFile;

    //region Database Info
    private static final String DATABASE_NAME = "HeroesOfOurTime";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_HERO = "Hero";
    private static final String TABLE_LOGIN = "Login";

    //Columns - Hero Table
    private static final String HERO_ID = "HeroID";
    private static final String HERO_NAME = "Name";
    private static final String HERO_BIRTHDAY = "Birthday";
    private static final String HERO_DEATH = "Death";
    private static final String HERO_SUMMARY = "Summary";
    private static final String HERO_DESCRIPTION = "Description";
    private static final String HERO_COMMENTS = "Comments";
    private static final String HERO_CREATED_DATE = "CreatedDate";
    private static final String HERO_MODIFIED_DATE = "ModifiedDate";

    //Columns - Login Table
    private static final String LOGIN_ID = "ID";
    private static final String LOGIN_NAME = "Name";
    private static final String LOGIN_EMAIL = "Email";
    private static final String LOGIN_USERNAME = "Username";
    private static final String LOGIN_PASSWORD = "Password";
    private static final String LOGIN_HINT = "Hint";
    private static final String LOGIN_CREATED_DATE = "CreatedDate";
    private static final String LOGIN_MODIFIED_DATE = "ModifiedDate";

    //Table Create Statements
    private static final String CREATE_TABLE_HERO = "CREATE TABLE " + TABLE_HERO + " ("
            + HERO_ID               + " INTEGER PRIMARY KEY AUTOINCREMENT"          + ", "
            + HERO_NAME             + " TEXT"                                       + ", "
            + HERO_BIRTHDAY         + " DATETIME"                                   + ", "
            + HERO_DEATH            + " DATETIME"                                   + ", "
            + HERO_SUMMARY          + " TEXT"                                       + ", "
            + HERO_DESCRIPTION      + " TEXT"                                       + ", "
            + HERO_COMMENTS         + " TEXT"                                       + ", "
            + HERO_CREATED_DATE     + " DATETIME"                                   + ", "
            + HERO_MODIFIED_DATE    + " DATETIME"
            + ")";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + " ("
            + LOGIN_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT"          + ", "
            + LOGIN_NAME            + " TEXT"                                       + ", "
            + LOGIN_EMAIL           + " TEXT"                                       + ", "
            + LOGIN_USERNAME        + " TEXT"                                       + ", "
            + LOGIN_PASSWORD        + " TEXT"                                       + ", "
            + LOGIN_HINT            + " TEXT"                                       + ", "
            + LOGIN_CREATED_DATE    + " DATETIME"                                   + ", "
            + LOGIN_MODIFIED_DATE   + " DATETIME"
            + ")";

    //endregion

    //region Database CRUD Operation Methods
    public DatabaseHelper(Context context, String filePath) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        pathToSaveDBFile = filePath + "/" + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_HERO);
//        Log.e(TAG, CREATE_TABLE_HERO);
//        db.execSQL(CREATE_TABLE_LOGIN);
//        Log.e(TAG, CREATE_TABLE_LOGIN);

        Log.d(TAG, "OnCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HERO);
//
//        onCreate(db);
    }

    private int getVersionId() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
//        String query = "SELECT version_id FROM dbVersion";
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//        int v =  cursor.getInt(0);
//        cursor.close();
        int v = db.getVersion();
        db.close();
        return v;
    }

    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist) {
            Log.d(TAG, "Database exists.");
            int currentDBVersion = getVersionId();
            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.");
                deleteDb();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch(SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }

    // Closing Database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    //endregion

    //region CRUD Operations - Hero Table

    public long insertHero(Hero hero) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null,
                SQLiteDatabase.OPEN_READWRITE);

        ContentValues values = new ContentValues();
        values.put(HERO_NAME, hero.getName());
        values.put(HERO_BIRTHDAY, String.valueOf(hero.getBirthday()));
        values.put(HERO_DEATH, String.valueOf(hero.getDeath()));
        values.put(HERO_SUMMARY, hero.getSummary());
        values.put(HERO_DESCRIPTION, hero.getDescription());
        values.put(HERO_COMMENTS, hero.getComments());
        values.put(HERO_MODIFIED_DATE, String.valueOf(Calendar.getInstance().getTime()));
        values.put(HERO_CREATED_DATE, String.valueOf(Calendar.getInstance().getTime()));

        //insert row
        long id = db.insert(TABLE_HERO, null, values);

        db.close();
        return id;
    }

    public Hero getHeroByTag (Object key) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null,
                SQLiteDatabase.OPEN_READONLY);
        Hero hero = null;
        String query = SelectByTagQuery(TABLE_HERO, HERO_ID, key);

        Log.e(TAG, query);

        Cursor cur = db.rawQuery(query, null);

        if (cur != null) {
            cur.moveToFirst();

            hero = new Hero();
            hero.setHeroID(cur.getInt(cur.getColumnIndex(HERO_ID)));
            hero.setName(cur.getString(cur.getColumnIndex(HERO_NAME)));
            hero.setBirthday(new Date(cur.getLong(cur.getColumnIndex(HERO_BIRTHDAY))));
            hero.setDeath(new Date(cur.getLong(cur.getColumnIndex(HERO_DEATH))));
            hero.setSummary(cur.getString(cur.getColumnIndex(HERO_SUMMARY)));
            hero.setDescription(cur.getString(cur.getColumnIndex(HERO_DESCRIPTION)));
            hero.setComments(cur.getString(cur.getColumnIndex(HERO_COMMENTS)));
            hero.setModifiedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_MODIFIED_DATE))));
            hero.setCreatedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_CREATED_DATE))));

            cur.close();
        }

        db.close();
        return hero;
    }

    public List<Hero> getHeroes() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null,
                SQLiteDatabase.OPEN_READONLY);
        List<Hero> heroList = new ArrayList<>();
        String query = SelectAllQuery(TABLE_HERO);
query = "SELECT name FROM sqlite_master WHERE type='table'";
        Log.e(TAG, query);

        Cursor cur = null;
        try {
            cur = db.rawQuery(query, null);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }

        if (cur != null && !cur.isClosed()) {
            cur.moveToFirst();
                while (!cur.isAfterLast()) {
//                    Hero hero = new Hero();
//                    hero.setHeroID(cur.getInt(cur.getColumnIndex(HERO_ID)));
//                    hero.setName(cur.getString(cur.getColumnIndex(HERO_NAME)));
//                    hero.setBirthday(new Date(cur.getLong(cur.getColumnIndex(HERO_BIRTHDAY))));
//                    hero.setDeath(new Date(cur.getLong(cur.getColumnIndex(HERO_DEATH))));
//                    hero.setSummary(cur.getString(cur.getColumnIndex(HERO_SUMMARY)));
//                    hero.setDescription(cur.getString(cur.getColumnIndex(HERO_DESCRIPTION)));
//                    hero.setComments(cur.getString(cur.getColumnIndex(HERO_COMMENTS)));
//                    hero.setModifiedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_MODIFIED_DATE))));
//                    hero.setCreatedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_CREATED_DATE))));
//
//                    heroList.add(hero);
                    String a = cur.getString(0);
                    Log.e(TAG,cur.getString(0));
                }
            cur.close();
        }
        else {
            heroList = null;
        }

        db.close();
        return heroList;
    }

    //endregion

    //region Generate Queries
    private String SelectByTagQuery (String tableName, String ColumnName, Object key) {

        String query = "";

        if (key instanceof String){
            query = "SELECT * FROM " + tableName + " WHERE " + ColumnName + " LIKE '%" + key + "%'";
        } else if (key instanceof Integer) {
            query = "SELECT * FROM " + tableName + " WHERE " + ColumnName + " = " + key;
        }

        return query;
    }

    private String SelectAllQuery (String tableName) {
        return "SELECT * FROM " + tableName;
    }

    //endregion
}
