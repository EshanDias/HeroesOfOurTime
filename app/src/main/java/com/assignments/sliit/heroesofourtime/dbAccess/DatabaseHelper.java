package com.assignments.sliit.heroesofourtime.dbAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignments.sliit.heroesofourtime.core.ImageHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

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
    private Context myContext;

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
    private static final String HERO_IMAGE = "HeroImage";

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
            + HERO_MODIFIED_DATE    + " DATETIME"                                   + ", "
            + HERO_IMAGE            + " TEXT"
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
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HERO);
        Log.e(TAG, CREATE_TABLE_HERO);
        db.execSQL(CREATE_TABLE_LOGIN);
        Log.e(TAG, CREATE_TABLE_LOGIN);

        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_LOGIN));
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_HERO));
        }

        onCreate(db);
    }

    // Closing Database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    private void seedData(SQLiteDatabase db) {
        InitialData initialData = new InitialData(myContext);
        List<Hero> heroList = initialData.getHeroes();

        for(Hero hero: heroList) {
            insertHero(db, hero);
        }
    }

    //endregion Database CRUD

    //region CRUD Operations - Hero Table

    private void insertHero(SQLiteDatabase db, Hero hero) {
        ImageHelper imageHelper = new ImageHelper();

        ContentValues values = new ContentValues();
        values.put(HERO_NAME, hero.getName());
        values.put(HERO_BIRTHDAY, String.valueOf(hero.getBirthday()));
        values.put(HERO_DEATH, String.valueOf(hero.getDeath()));
        values.put(HERO_SUMMARY, hero.getSummary());
        values.put(HERO_DESCRIPTION, hero.getDescription());
        values.put(HERO_COMMENTS, hero.getComments());
        values.put(HERO_MODIFIED_DATE, String.valueOf(Calendar.getInstance().getTime()));
        values.put(HERO_CREATED_DATE, String.valueOf(Calendar.getInstance().getTime()));
        values.put(HERO_IMAGE, imageHelper.insetImage(hero.getHeroImage()));

        //insert row
        try {
            db.insert(TABLE_HERO, HERO_DEATH, values);
        } catch (SQLException e) {
            Log.e(TAG, "Hero Name: " + hero.getName() + "\n" + e.getMessage());
        }
    }

    public Hero getHeroByTag (Object key) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SelectByTagQuery(TABLE_HERO, HERO_ID, key);

        Log.e(TAG, query);

        Cursor cur = db.rawQuery(query, null);

        if (cur != null) {
            cur.moveToFirst();

            Hero hero = new Hero();
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
            return hero;
        } else {
            return null;
        }
    }

    public List<Hero> getHeroes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Hero> heroList = new ArrayList<>();
        String query = SelectAllQuery(TABLE_HERO);

        Log.e(TAG, query);

        Cursor cur = db.rawQuery(query, null);

        if (cur != null && !cur.isClosed()) {
            cur.moveToFirst();
            do {
                Hero hero = new Hero();
                hero.setHeroID(cur.getInt(cur.getColumnIndex(HERO_ID)));
                hero.setName(cur.getString(cur.getColumnIndex(HERO_NAME)));
                hero.setBirthday(new Date(cur.getLong(cur.getColumnIndex(HERO_BIRTHDAY))));
                hero.setDeath(new Date(cur.getLong(cur.getColumnIndex(HERO_DEATH))));
                hero.setSummary(cur.getString(cur.getColumnIndex(HERO_SUMMARY)));
                hero.setDescription(cur.getString(cur.getColumnIndex(HERO_DESCRIPTION)));
                hero.setComments(cur.getString(cur.getColumnIndex(HERO_COMMENTS)));
                hero.setModifiedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_MODIFIED_DATE))));
                hero.setCreatedDate(new Date(cur.getLong(cur.getColumnIndex(HERO_CREATED_DATE))));

                heroList.add(hero);
            } while (cur.moveToNext());
        }
        cur.close();
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
