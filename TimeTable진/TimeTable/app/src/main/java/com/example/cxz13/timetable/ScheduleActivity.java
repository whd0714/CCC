package com.example.cxz13.timetable;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ScheduleActivity extends AppCompatActivity {

    private Schedule schedule;
    private AutoResizeTextView monday[] = new AutoResizeTextView[12];
    private AutoResizeTextView tuesday[] = new AutoResizeTextView[12];
    private AutoResizeTextView wednesday[] = new AutoResizeTextView[12];
    private AutoResizeTextView thursday[] = new AutoResizeTextView[12];
    private AutoResizeTextView friday[] = new AutoResizeTextView[12];

    public SQLiteDatabase db;
    public ProductDBHelper nHelper;

    public static final String ROOT_DIR = "/data/data/com.example.cxz13.timetable/databases/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setDB(this);
        nHelper = new ProductDBHelper(this);
        db = nHelper.getWritableDatabase();

        schedule = new Schedule(db);
        db.close();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
             //           .setAction("Action", null).show();
                Intent intent = new Intent(ScheduleActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

        monday[0] = (AutoResizeTextView) findViewById(R.id.monday0);
        monday[1] = (AutoResizeTextView) findViewById(R.id.monday1);
        monday[2] = (AutoResizeTextView) findViewById(R.id.monday2);
        monday[3] = (AutoResizeTextView) findViewById(R.id.monday3);
        monday[4] = (AutoResizeTextView) findViewById(R.id.monday4);
        monday[5] = (AutoResizeTextView) findViewById(R.id.monday5);
        monday[6] = (AutoResizeTextView) findViewById(R.id.monday6);
        monday[7] = (AutoResizeTextView) findViewById(R.id.monday7);
        monday[8] = (AutoResizeTextView) findViewById(R.id.monday8);
        monday[9] = (AutoResizeTextView) findViewById(R.id.monday9);
        monday[10] = (AutoResizeTextView) findViewById(R.id.monday10);
        monday[11] = (AutoResizeTextView) findViewById(R.id.monday11);
        tuesday[0] = (AutoResizeTextView) findViewById(R.id.tuesday0);
        tuesday[1] = (AutoResizeTextView) findViewById(R.id.tuesday1);
        tuesday[2] = (AutoResizeTextView) findViewById(R.id.tuesday2);
        tuesday[3] = (AutoResizeTextView) findViewById(R.id.tuesday3);
        tuesday[4] = (AutoResizeTextView) findViewById(R.id.tuesday4);
        tuesday[5] = (AutoResizeTextView) findViewById(R.id.tuesday5);
        tuesday[6] = (AutoResizeTextView) findViewById(R.id.tuesday6);
        tuesday[7] = (AutoResizeTextView) findViewById(R.id.tuesday7);
        tuesday[8] = (AutoResizeTextView) findViewById(R.id.tuesday8);
        tuesday[9] = (AutoResizeTextView) findViewById(R.id.tuesday9);
        tuesday[10] = (AutoResizeTextView) findViewById(R.id.tuesday10);
        tuesday[11] = (AutoResizeTextView) findViewById(R.id.tuesday11);
        wednesday[0] = (AutoResizeTextView) findViewById(R.id.wednesday0);
        wednesday[1] = (AutoResizeTextView) findViewById(R.id.wednesday1);
        wednesday[2] = (AutoResizeTextView) findViewById(R.id.wednesday2);
        wednesday[3] = (AutoResizeTextView) findViewById(R.id.wednesday3);
        wednesday[4] = (AutoResizeTextView) findViewById(R.id.wednesday4);
        wednesday[5] = (AutoResizeTextView) findViewById(R.id.wednesday5);
        wednesday[6] = (AutoResizeTextView) findViewById(R.id.wednesday6);
        wednesday[7] = (AutoResizeTextView) findViewById(R.id.wednesday7);
        wednesday[8] = (AutoResizeTextView) findViewById(R.id.wednesday8);
        wednesday[9] = (AutoResizeTextView) findViewById(R.id.wednesday9);
        wednesday[10] = (AutoResizeTextView) findViewById(R.id.wednesday10);
        wednesday[11] = (AutoResizeTextView) findViewById(R.id.wednesday11);
        thursday[0] = (AutoResizeTextView) findViewById(R.id.thursday0);
        thursday[1] = (AutoResizeTextView) findViewById(R.id.thursday1);
        thursday[2] = (AutoResizeTextView) findViewById(R.id.thursday2);
        thursday[3] = (AutoResizeTextView) findViewById(R.id.thursday3);
        thursday[4] = (AutoResizeTextView) findViewById(R.id.thursday4);
        thursday[5] = (AutoResizeTextView) findViewById(R.id.thursday5);
        thursday[6] = (AutoResizeTextView) findViewById(R.id.thursday6);
        thursday[7] = (AutoResizeTextView) findViewById(R.id.thursday7);
        thursday[8] = (AutoResizeTextView) findViewById(R.id.thursday8);
        thursday[9] = (AutoResizeTextView) findViewById(R.id.thursday9);
        thursday[10] = (AutoResizeTextView) findViewById(R.id.thursday10);
        thursday[11] = (AutoResizeTextView) findViewById(R.id.thursday11);
        friday[0] = (AutoResizeTextView) findViewById(R.id.friday0);
        friday[1] = (AutoResizeTextView) findViewById(R.id.friday1);
        friday[2] = (AutoResizeTextView) findViewById(R.id.friday2);
        friday[3] = (AutoResizeTextView) findViewById(R.id.friday3);
        friday[4] = (AutoResizeTextView) findViewById(R.id.friday4);
        friday[5] = (AutoResizeTextView) findViewById(R.id.friday5);
        friday[6] = (AutoResizeTextView) findViewById(R.id.friday6);
        friday[7] = (AutoResizeTextView) findViewById(R.id.friday7);
        friday[8] = (AutoResizeTextView) findViewById(R.id.friday8);
        friday[9] = (AutoResizeTextView) findViewById(R.id.friday9);
        friday[10] = (AutoResizeTextView) findViewById(R.id.friday10);
        friday[11] = (AutoResizeTextView) findViewById(R.id.friday11);

        schedule.setting(monday,tuesday,wednesday,thursday,friday);
 //       final Schedule schedule = new Schedule(monday, tuesday, wednesday, thursday, friday);
    }

    public static void setDB(Context ctx) {
        Log.d("MainActivity", "setDB()");
        File folder = new File(ROOT_DIR);
        if (folder.exists()) {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets();
        File outfile = new File(ROOT_DIR + "storeDB.sqlite");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("storeDB.sqlite", AssetManager.ACCESS_BUFFER);
            Log.d("MainActivity", "open");
            filesize = is.available();
            Log.d("MainActivity", "file size = " + Integer.toString(is.available()));
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
            Log.d("MainActivity", e.getMessage());
        }

        Log.d("MainActivity", "end");
    }

    class ProductDBHelper extends SQLiteOpenHelper {
        public ProductDBHelper(Context context) {
            super(context, "storeDB.sqlite", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
