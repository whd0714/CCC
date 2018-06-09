package com.example.cxz13.timetable;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private ArrayAdapter univAdapter;
    private Spinner univSpinner;
    private ArrayAdapter gradeAdapter;
    private Spinner gradeSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;

    private String courseMajor = "";
    private String courseUniv = "";
    private String courseGrade = "";
    private String courseArea = "";

    public SQLiteDatabase db;
    public Cursor cursor;
    public ProductDBHelper mHelper;
    Button mButton;
    Button mjuckyoung;
    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;


    public static final String ROOT_DIR = "/data/data/com.example.cxz13.timetable/databases/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setDB(this);

        mHelper = new ProductDBHelper(this);
        db = mHelper.getWritableDatabase();

        mButton = (Button) findViewById(R.id.searchButton);
        univSpinner = (Spinner) findViewById(R.id.univSpinner);
        gradeSpinner = (Spinner) findViewById(R.id.gradeSpinner);
        areaSpinner = (Spinner) findViewById(R.id.areaSpinner);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        mjuckyoung = (Button) findViewById(R.id.juckyoung);
        univAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.univ, android.R.layout.simple_spinner_dropdown_item);
        univSpinner.setAdapter(univAdapter);

        majorAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.major, android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);

        areaAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.universityArea, android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        gradeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.grade, android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeAdapter);

        courseListView = (ListView) findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        Intent it = getIntent();
        adapter = new CourseListAdapter(getApplicationContext(),courseList);
        courseListView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseUniv = univSpinner.getSelectedItem().toString();
                courseMajor = majorSpinner.getSelectedItem().toString();
                courseArea = areaSpinner.getSelectedItem().toString();
                courseGrade = gradeSpinner.getSelectedItem().toString();

                select();
            }
        });
        mjuckyoung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CourseActivity.this, ScheduleActivity.class);
               //intent.putExtra("W", adapter.schedule);
               startActivity(intent);

            }
        });
   }

    public void select(){
        cursor = db.rawQuery("select * from 'course' where 대학 = ? AND 개설학과 = ? AND 학년 = ? AND 이수구분 = ?",new String[]{courseUniv, courseMajor,courseGrade, courseArea});
        //Log.d("Result Query", Integer.toString(cursor.getCount()));
        if(cursor.getCount() <= 0){
            courseList.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"조회된 강의가 없습니다.", Toast.LENGTH_SHORT).show();
            /*AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this.getParent());
            dialog = builder.setMessage("조회된 강의가 없습니다.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();*/
        }
        else {
            int count = 0;
            courseList.clear();
            while (cursor.moveToNext()) {
                Log.d("Result Query", cursor.getString(5));
                Course course = new Course(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
                courseList.add(course);
                count++;
            }
            adapter.notifyDataSetChanged();
        }

    }

    public static void setDB(Context ctx) {
        Log.d("MainActivity", "setDB()");
        File folder = new File(ROOT_DIR);
        if (folder.exists()) {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets();
        File outfile = new File(ROOT_DIR + "testDB.sqlite");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("testDB.sqlite", AssetManager.ACCESS_BUFFER);
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
            super(context, "testDB.sqlite", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
