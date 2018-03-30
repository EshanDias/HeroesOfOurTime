package com.assignments.sliit.heroesofourtime.dbAccess;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignments.sliit.heroesofourtime.core.ImageHelper;
import com.assignments.sliit.heroesofourtime.model.Hero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            + HERO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
            + HERO_NAME + " TEXT" + ", "
            + HERO_BIRTHDAY + " INTEGER" + ", "
            + HERO_DEATH + " INTEGER" + ", "
            + HERO_SUMMARY + " TEXT" + ", "
            + HERO_DESCRIPTION + " TEXT" + ", "
            + HERO_COMMENTS + " TEXT" + ", "
            + HERO_CREATED_DATE + " INTEGER" + ", "
            + HERO_MODIFIED_DATE + " INTEGER" + ", "
            + HERO_IMAGE + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + " ("
            + LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
            + LOGIN_NAME + " TEXT" + ", "
            + LOGIN_EMAIL + " TEXT" + ", "
            + LOGIN_USERNAME + " TEXT" + ", "
            + LOGIN_PASSWORD + " TEXT" + ", "
            + LOGIN_HINT + " TEXT" + ", "
            + LOGIN_CREATED_DATE + " INTEGER" + ", "
            + LOGIN_MODIFIED_DATE + " INTEGER"
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
        db.execSQL(CREATE_TABLE_LOGIN);

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

        for (Hero hero : heroList) {
            insertHero(db, hero);
        }
    }

    //endregion Database CRUD

    //region CRUD Operations - Hero Table

    @SuppressLint("SimpleDateFormat")
    private void insertHero(SQLiteDatabase db, Hero hero) {
        ContentValues values = new ContentValues();
        values.put(HERO_NAME, hero.getName());
        values.put(HERO_BIRTHDAY, new SimpleDateFormat("yyyy-MM-dd").format(hero.getBirthday()));
        if (hero.getDeath() != null) {
            values.put(HERO_DEATH, new SimpleDateFormat("yyyy-MM-dd").format(hero.getDeath()));
        } else {
            values.put(HERO_DEATH, "");
        }
        values.put(HERO_SUMMARY, hero.getSummary());
        values.put(HERO_DESCRIPTION, hero.getDescription());
        values.put(HERO_COMMENTS, hero.getComments());
        values.put(HERO_MODIFIED_DATE, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        values.put(HERO_CREATED_DATE, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        values.put(HERO_IMAGE, hero.getHeroImage());

        //insert row
        try {
            db.insert(TABLE_HERO, null, values);
        } catch (SQLException e) {
            Log.e(TAG, "Hero Name: " + hero.getName() + "\n" + e.getMessage());
        }
    }

    public Hero getHeroByTag(Object key) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query = SelectByTagQuery(TABLE_HERO, HERO_ID, key);

        Log.d(TAG, query);

        Cursor cur = db.rawQuery(query, null);

        if (cur != null) {
            cur.moveToFirst();

            Hero hero = new Hero();
            hero.setHeroID(cur.getInt(cur.getColumnIndex(HERO_ID)));
            hero.setName(cur.getString(cur.getColumnIndex(HERO_NAME)));
            hero.setBirthday(sdf.parse(cur.getString(cur.getColumnIndex(HERO_BIRTHDAY))));
            if (cur.getString(cur.getColumnIndex(HERO_DEATH)).isEmpty()){
                hero.setDeath(null);
            }else {
                hero.setDeath(sdf.parse(cur.getString(cur.getColumnIndex(HERO_DEATH))));
            }
            hero.setSummary(cur.getString(cur.getColumnIndex(HERO_SUMMARY)));
            hero.setDescription(cur.getString(cur.getColumnIndex(HERO_DESCRIPTION)));
            hero.setComments(cur.getString(cur.getColumnIndex(HERO_COMMENTS)));
            hero.setModifiedDate(sdf.parse(cur.getString(cur.getColumnIndex(HERO_MODIFIED_DATE))));
            hero.setCreatedDate(sdf.parse(cur.getString(cur.getColumnIndex(HERO_CREATED_DATE))));
            hero.setHeroImage(cur.getInt(cur.getColumnIndex(HERO_IMAGE)));

            cur.close();
            return hero;
        } else {
            return null;
        }
    }

    public List<Hero> getHeroes() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Hero> heroList = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query = SelectAllQuery(TABLE_HERO, null, HERO_ID);

        Log.d(TAG, query);

        Cursor cur = db.rawQuery(query, null);

        if (cur != null && !cur.isClosed()) {
            cur.moveToFirst();
            do {
                Hero hero = new Hero();
                hero.setHeroID(cur.getInt(cur.getColumnIndex(HERO_ID)));
                hero.setName(cur.getString(cur.getColumnIndex(HERO_NAME)));
                hero.setBirthday(sdf.parse(cur.getString(cur.getColumnIndex(HERO_BIRTHDAY))));
                if (cur.getString(cur.getColumnIndex(HERO_DEATH)).isEmpty()){
                    hero.setDeath(null);
                }else {
                    hero.setDeath(sdf.parse(cur.getString(cur.getColumnIndex(HERO_DEATH))));
                }
                hero.setSummary(cur.getString(cur.getColumnIndex(HERO_SUMMARY)));
                hero.setDescription(cur.getString(cur.getColumnIndex(HERO_DESCRIPTION)));
                hero.setComments(cur.getString(cur.getColumnIndex(HERO_COMMENTS)));
                hero.setModifiedDate(sdf.parse(cur.getString(cur.getColumnIndex(HERO_MODIFIED_DATE))));
                hero.setCreatedDate(sdf.parse(cur.getString(cur.getColumnIndex(HERO_CREATED_DATE))));
                hero.setHeroImage(cur.getInt(cur.getColumnIndex(HERO_IMAGE)));

                heroList.add(hero);
            } while (cur.moveToNext());
        }
        if (cur != null) {
            cur.close();
        }
        return heroList;
    }

    //endregion

    //region Generate Queries
    private String SelectByTagQuery(String tableName, String ColumnName, Object key) {

        String query = "";

        if (key instanceof String) {
            query = "SELECT * FROM " + tableName + " WHERE " + ColumnName + " LIKE '%" + key + "%'";
        } else if (key instanceof Integer) {
            query = "SELECT * FROM " + tableName + " WHERE " + ColumnName + " = " + key;
        }

        return query;
    }

    private String SelectAllQuery(String tableName, ContentValues whereConditions, String orderBy) {
        String query;
        StringBuilder where = new StringBuilder();
        query = "SELECT * FROM " + tableName;

        if (whereConditions != null) {
            int count = 0;
            where.append(" WHERE ");
            for (String columnName : whereConditions.keySet()) {
                count += 1;
                where.append(columnName).append(" = ");
                where.append(whereConditions.get(columnName));
                if (whereConditions.size() > count) {
                    where.append(" AND ");
                }
            }
        } else if (!orderBy.isEmpty()) {
            orderBy = " ORDER BY " + orderBy;
        }

        return query + where.toString() + orderBy;
    }

    //endregion
}
