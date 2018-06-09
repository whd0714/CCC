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


    public CourseListAdapter(Context context, List<Course> courseList){
        db = DBManager.getInstance(context).getScheduleDB();
        this.context = context;
        this.courseList = courseList;
        this.schedule = new Schedule(db);
        courseCodeList = new ArrayList<>();
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
        TextView course_Grade = v.findViewById(R.id.course_Grade);
        TextView courseTitle = v.findViewById(R.id.courseTitle);
        TextView courseCredit = v.findViewById(R.id.courseCredit);
        final TextView courseCode = v.findViewById(R.id.courseCode);
        TextView courseTime = v.findViewById(R.id.courseTime);
        TextView courseProfessor = v.findViewById(R.id.courseProfessor);

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
}
