package com.example.cxz13.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends BaseAdapter{
    private Context context;
    private List<Course> courseList;
    public Schedule schedule;
    public List<String> courseCodeList;
    public SQLiteDatabase db;
    public ProductDBHelper nHelper;

    public static final String ROOT_DIR = "/data/data/com.example.cxz13.timetable/databases/";
    //private Activity parent;

    public CourseListAdapter(Context context, List<Course> courseList){
        setDB(context);
        nHelper = new ProductDBHelper(context);
        db=nHelper.getWritableDatabase();
        this.context = context;
        this.courseList = courseList;
        this.schedule = new Schedule(db);
        courseCodeList = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       View v = View.inflate(context, R.layout.course,null);
        TextView course_Grade = (TextView) v.findViewById(R.id.course_Grade);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);
        final TextView courseCode = (TextView) v.findViewById(R.id.courseCode);
        TextView courseTime = (TextView) v.findViewById(R.id.courseTime);
        TextView courseProfessor = (TextView) v.findViewById(R.id.courseProfessor);

        if(courseList.get(position).getCourseGrade().equals("전체") || courseList.get(position).getCourseGrade().equals("")){
            course_Grade.setText("모든 학년");
        }
        else{
            course_Grade.setText(courseList.get(position).getCourseGrade() + "학년");
        }
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText(courseList.get(position).getCourseCredit());
        courseProfessor.setText(courseList.get(position).getCourseProfessor() +"교수님");
        courseTime.setText(courseList.get(position).getCourseTime());
        courseCode.setText(courseList.get(position).getCourseCode());

        v.setTag(courseList.get(position).getCourseCode());

       Button addButton = (Button) v.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validate = false;
                validate = schedule.validate(courseList.get(position).getCourseTime());
                courseCodeList.add(courseList.get(position).getCourseCode());
                Log.d("maybeValid","maybeValid");
                if(validate && alreadyin(courseCodeList,courseCode.toString())) {
                    schedule.addSchedule(courseList.get(position).getCourseTime(), courseList.get(position).getCourseTitle(),db);
                    Toast.makeText(context,"추가되었습니다", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"중복된 시간입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
    public boolean alreadyin(List<String> courseCodeList, String item){
        for(int i=0;i<courseCodeList.size();i++){
            if(courseCodeList.get(i) == item){
                return false;
            }

        }
        return true;
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
