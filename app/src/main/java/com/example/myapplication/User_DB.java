package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 歐歐 on 2016/5/31.
 */
public class User_DB extends SQLiteOpenHelper {
    private final static int _DBVersion = 1; //<-- 版本
    private final static String _DBName = "Find_And_Lost";  //<-- db name
    private final static String _UserTable = "User"; //<-- table name
    private final static String _LostThingTable = "LostThing"; //<-- table name
    private final static String _PickedThingTable = "PickedThing"; //<-- table name

    SQLiteDatabase db;

    String UserTable = "CREATE TABLE IF NOT EXISTS " + _UserTable +
            " (" +
            " _id " + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            " UserName " + "TEXT NOT NULL ," +
            " PassWord " + "TEXT NOT NULL" +
            " )";

    String LostThingTable = "CREATE TABLE IF NOT EXISTS " + _LostThingTable +
            " (" +
            " _id " + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            " UserName " + "TEXT NOT NULL ," +
            " WhatLost " + "TEXT NOT NULL ," +
            " WhereLost " + "TEXT NOT NULL ," +
            " WhenLost " + "TEXT NOT NULL ," +
            " Phone " + "TEXT NOT NULL" +
            " )";

    String PickedThingTable = "CREATE TABLE IF NOT EXISTS " + _PickedThingTable +
            " (" +
            " _id " + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            " UserName " + "TEXT NOT NULL ," +
            " WhatPicked " + "TEXT NOT NULL ," +
            " WherePicked " + "TEXT NOT NULL ," +
            " WhenPicked " + "TEXT NOT NULL ," +
            " Phone " + "TEXT NOT NULL" +
            " )";

    public User_DB(Context context) {
        super(context, _DBName, null, _DBVersion);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable);
        db.execSQL(LostThingTable);
        db.execSQL(PickedThingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // 使用者
    public int UserCheck(String INPUT,String Password)
    {
        String where = " UserName  = ?  AND  PassWord = ?  ";
        // 執行查詢
        Cursor result = db.query(_UserTable, null, where, new String[]{INPUT, Password}, null, null, null, null);

        if(result.moveToFirst())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int SearchUser(String INPUT)
    {
        String where = " UserName  = ?  ";
        // 執行查詢
        Cursor result = db.query(_UserTable, null, where, new String[]{INPUT}, null, null, null, null);

        if(result.moveToFirst())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void AddUser(String INPUT,String Password)
    {
        ContentValues values = new ContentValues();
        values.put("UserName", INPUT);
        values.put("PassWord", Password);
        db.insert(_UserTable, null, values);
    }

    //遺失物
    public LostThingDataModel SearchUserLostThingDataItem(int ID)
    {
        String where = "select * from " + _LostThingTable + " where _id = " + ID;

        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        result.moveToFirst();
        int K = result.getInt(result.getColumnIndex("_id"));
        String A = result.getString(result.getColumnIndex("UserName"));
        String B = result.getString(result.getColumnIndex("WhatLost"));
        String C = result.getString(result.getColumnIndex("WhereLost"));
        String D = result.getString(result.getColumnIndex("WhenLost"));
        String E = result.getString(result.getColumnIndex("Phone"));
        LostThingDataModel LostItem = new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E);

        return LostItem;
    }

    public ArrayList<LostThingDataModel> SearchUserLostThingData(String USERNAME)
    {
        String where = "select * from " + _LostThingTable + " where UserName = " + USERNAME;

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);

        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatLost"));
            String C = result.getString(result.getColumnIndex("WhereLost"));
            String D = result.getString(result.getColumnIndex("WhenLost"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetLostThingData()
    {
        String where = "select * from " + _LostThingTable;

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);

        int row = result.getCount();
        result.moveToLast();

        // 只顯示最新的十筆資料
        if(row < 10)
        {
            for(int i = 0; i < row; i++)
            {
                int K = result.getInt(result.getColumnIndex("_id"));
                String A = result.getString(result.getColumnIndex("UserName"));
                String B = result.getString(result.getColumnIndex("WhatLost"));
                String C = result.getString(result.getColumnIndex("WhereLost"));
                String D = result.getString(result.getColumnIndex("WhenLost"));
                String E = result.getString(result.getColumnIndex("Phone"));
                LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
                result.moveToPrevious();
            }
        }
        else
        {
            for(int i = 0; i < 10; i++)
            {
                int K = result.getInt(result.getColumnIndex("_id"));
                String A = result.getString(result.getColumnIndex("UserName"));
                String B = result.getString(result.getColumnIndex("WhatLost"));
                String C = result.getString(result.getColumnIndex("WhereLost"));
                String D = result.getString(result.getColumnIndex("WhenLost"));
                String E = result.getString(result.getColumnIndex("Phone"));
                LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
                result.moveToPrevious();
            }
        }

        return LostList;
    }

    public void AddLostThing(String USER,String ITEM,String ADDRESS,String DATE,String PHONE)
    {
        ContentValues values = new ContentValues();
        values.put("UserName", USER);
        values.put("WhatLost", ITEM);
        values.put("WhereLost", ADDRESS);
        values.put("WhenLost", DATE);
        values.put("Phone", PHONE);
        db.insert(_LostThingTable, null, values);
    }

    public void EditLostThing(int id,String USER,String ITEM,String ADDRESS,String DATE,String PHONE)
    {
        ContentValues values = new ContentValues();
        values.put("UserName", USER);
        values.put("WhatLost", ITEM);
        values.put("WhereLost", ADDRESS);
        values.put("WhenLost", DATE);
        values.put("Phone", PHONE);
        db.update(_LostThingTable, values, "_id = " + id, null);
    }

    public void DeleteLostThing(int id)
    {
        db.delete(_LostThingTable, "_id = " + id, null);
    }

    public ArrayList<LostThingDataModel> GetSelectResultForUserName(String SELECTDATA)
    {
        String where = "select * from " + _LostThingTable + " where UserName LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatLost"));
            String C = result.getString(result.getColumnIndex("WhereLost"));
            String D = result.getString(result.getColumnIndex("WhenLost"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetSelectResultForLostThingName(String SELECTDATA)
    {
        String where = "select * from " + _LostThingTable + " where WhatLost LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatLost"));
            String C = result.getString(result.getColumnIndex("WhereLost"));
            String D = result.getString(result.getColumnIndex("WhenLost"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetSelectResultForLostAddress(String SELECTDATA)
    {
        String where = "select * from " + _LostThingTable + " where WhereLost LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatLost"));
            String C = result.getString(result.getColumnIndex("WhereLost"));
            String D = result.getString(result.getColumnIndex("WhenLost"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    // 拾獲物資料
    public LostThingDataModel SearchUserPickedThingDataItem(int ID)
    {
        String where = "select * from " + _PickedThingTable + " where _id = " + ID;

        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        result.moveToFirst();
        int K = result.getInt(result.getColumnIndex("_id"));
        String A = result.getString(result.getColumnIndex("UserName"));
        String B = result.getString(result.getColumnIndex("WhatPicked"));
        String C = result.getString(result.getColumnIndex("WherePicked"));
        String D = result.getString(result.getColumnIndex("WhenPicked"));
        String E = result.getString(result.getColumnIndex("Phone"));
        LostThingDataModel PickedItem = new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E);

        return PickedItem;
    }

    public ArrayList<LostThingDataModel> SearchUserPickedThingData(String USERNAME)
    {
        String where = "select * from " + _PickedThingTable + " where UserName = " + USERNAME;

        ArrayList<LostThingDataModel> PickedList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);

        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatPicked"));
            String C = result.getString(result.getColumnIndex("WherePicked"));
            String D = result.getString(result.getColumnIndex("WhenPicked"));
            String E = result.getString(result.getColumnIndex("Phone"));
            PickedList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return PickedList;
    }

    public ArrayList<LostThingDataModel> GetPickedThingData()
    {
        String where = "select * from " + _PickedThingTable;

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);

        int row = result.getCount();
        result.moveToLast();

        // 只顯示最新的十筆資料
        if(row < 10)
        {
            for(int i = 0; i < row; i++)
            {
                int K = result.getInt(result.getColumnIndex("_id"));
                String A = result.getString(result.getColumnIndex("UserName"));
                String B = result.getString(result.getColumnIndex("WhatPicked"));
                String C = result.getString(result.getColumnIndex("WherePicked"));
                String D = result.getString(result.getColumnIndex("WhenPicked"));
                String E = result.getString(result.getColumnIndex("Phone"));
                LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
                result.moveToPrevious();
            }
        }
        else
        {
            for(int i = 0; i < 10; i++)
            {
                int K = result.getInt(result.getColumnIndex("_id"));
                String A = result.getString(result.getColumnIndex("UserName"));
                String B = result.getString(result.getColumnIndex("WhatPicked"));
                String C = result.getString(result.getColumnIndex("WherePicked"));
                String D = result.getString(result.getColumnIndex("WhenPicked"));
                String E = result.getString(result.getColumnIndex("Phone"));
                LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
                result.moveToPrevious();
            }
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetSelectResultForUserNameInPicked(String SELECTDATA)
    {
        String where = "select * from " + _PickedThingTable + " where UserName LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatPicked"));
            String C = result.getString(result.getColumnIndex("WherePicked"));
            String D = result.getString(result.getColumnIndex("WhenPicked"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetSelectResultForPickedThingName(String SELECTDATA)
    {
        String where = "select * from " + _PickedThingTable + " where WhatPicked LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatPicked"));
            String C = result.getString(result.getColumnIndex("WherePicked"));
            String D = result.getString(result.getColumnIndex("WhenPicked"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public ArrayList<LostThingDataModel> GetSelectResultForPickedAddress(String SELECTDATA)
    {
        String where = "select * from " + _PickedThingTable + " where WherePicked LIKE '%" + SELECTDATA + "%'";

        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        // 執行查詢
        Cursor result = db.rawQuery(where, null);
        int row = result.getCount();
        result.moveToFirst();
        for(int i = 0; i < row; i++)
        {
            int K = result.getInt(result.getColumnIndex("_id"));
            String A = result.getString(result.getColumnIndex("UserName"));
            String B = result.getString(result.getColumnIndex("WhatPicked"));
            String C = result.getString(result.getColumnIndex("WherePicked"));
            String D = result.getString(result.getColumnIndex("WhenPicked"));
            String E = result.getString(result.getColumnIndex("Phone"));
            LostList.add(new LostThingDataModel(K, B, R.drawable.new_image, D, A, C, E));
            result.moveToNext();
        }

        return LostList;
    }

    public void AddPickedThing(String USER,String ITEM,String ADDRESS,String DATE,String PHONE)
    {
        ContentValues values = new ContentValues();
        values.put("UserName", USER);
        values.put("WhatPicked", ITEM);
        values.put("WherePicked", ADDRESS);
        values.put("WhenPicked", DATE);
        values.put("Phone", PHONE);
        db.insert(_PickedThingTable, null, values);
    }

    public void EditPickedThing(int id,String USER,String ITEM,String ADDRESS,String DATE,String PHONE)
    {
        ContentValues values = new ContentValues();
        values.put("UserName", USER);
        values.put("WhatPicked", ITEM);
        values.put("WherePicked", ADDRESS);
        values.put("WhenPicked", DATE);
        values.put("Phone", PHONE);
        db.update(_PickedThingTable, values, "_id = " + id, null);
    }

    public void DeletePickedThing(int id)
    {
        db.delete(_PickedThingTable, "_id = " + id, null);
    }
}
