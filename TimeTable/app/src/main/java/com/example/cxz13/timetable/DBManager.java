package com.example.cxz13.timetable;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DBManager {

    private static final String TAG ="DBManager";

    private static DBManager INSTANCE = null;
    private static final String ROOT_DIR = "/data/data/com.example.cxz13.timetable/databases/";

    private Context context;
    private ProductDBHelper courseHelper = null;
    private ProductDBHelper scheduleHelper = null;
    private SQLiteDatabase courseDB = null;
    private SQLiteDatabase scheduleDB = null;

    public SQLiteDatabase getCourseDB() {
        return courseDB;
    }

    public SQLiteDatabase getScheduleDB() {
        return scheduleDB;
    }

    public static DBManager getInstance(Context context) {
        Log.d(TAG, "getInstance()");
        if(INSTANCE == null) {
            INSTANCE = new DBManager(context);
        }
        return INSTANCE;
    }

    private DBManager(Context context) {
        Log.d(TAG, "Init()");
        this.context = context;
        courseHelper = new ProductDBHelper(context, "testDB.sqlite");
        scheduleHelper = new ProductDBHelper(context, "storeDB.sqlite");
        setDB(context, "testDB.sqlite");
        setDB(context, "storeDB.sqlite");
        courseDB = courseHelper.getWritableDatabase();
        scheduleDB = scheduleHelper.getWritableDatabase();
    }

    public static void setDB(Context ctx, String dbName) {
        Log.d(TAG, "setDB()");
        File folder = new File(ROOT_DIR);
        if (folder.exists()) {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets();
        File outfile = new File(ROOT_DIR + dbName);
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open(dbName, AssetManager.ACCESS_BUFFER);
            Log.d(TAG, "open");
            filesize = is.available();
            Log.d(TAG, "file size = " + Integer.toString(is.available()));
            if (outfile.length() >= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        Log.d(TAG, "end");
    }

    class ProductDBHelper extends SQLiteOpenHelper {
        public ProductDBHelper(Context context, String dbName) {
            super(context, dbName, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
