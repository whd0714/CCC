package com.example.cxz13.timetable;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Schedule {

    private String monday[] = new String[12];
    private String tuesday[] = new String[12];
    private String wednesday[] = new String[12];
    private String thursday[] = new String[12];
    private String friday[] = new String[12];

    private static ArrayList<String> courseList = new ArrayList<>();
    public Cursor cursor;

    public Schedule(SQLiteDatabase db){
        for(int i=0;i<12;i++){
            String num = String.valueOf(i);
            cursor = db.rawQuery("select * from 'schedule' where 강의시간 = ?",new String[]{num});
            cursor.moveToNext();
            monday[i] = cursor.getString(0);
            Log.d("monday",monday[i]);
            cursor.moveToNext();
            tuesday[i] = cursor.getString(0);
            cursor.moveToNext();
            wednesday[i] = cursor.getString(0);
            cursor.moveToNext();
            thursday[i] = cursor.getString(0);
            cursor.moveToNext();
            friday[i] = cursor.getString(0);
        }
    }

    public void addSchedule(String scheduleText, String courseTitle, SQLiteDatabase db) {
        int temp;
        courseList.add(courseTitle);
        if ((temp = scheduleText.indexOf("월")) > -1) {
            ContentValues values = new ContentValues();
            int count = 0;
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = (int) scheduleText.charAt(temp) - 48;
            for (int i = temp; i < scheduleText.length(); i++) {
                if (scheduleText.charAt(i) == '/') {
                    endPoint = (int) scheduleText.charAt(i - 2) - 48;
                    count++;
                }
            }
            if (count==0) {
                endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;
            }
            if ((endPoint - startPoint) == 1) {
                monday[startPoint] = courseTitle;
                monday[endPoint] = courseTitle;
                values.put("강의명",courseTitle);
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(endPoint)});
                cursor = db.rawQuery("select 강의명 from schedule where 강의시간 = ? AND 요일 = 'Monday'", new String[]{String.valueOf(startPoint)});
                cursor.moveToNext();
            } else if (startPoint == endPoint) {
                monday[startPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(startPoint)});
            } else {
                monday[startPoint] = courseTitle;
                monday[endPoint - 1] = courseTitle;
                monday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(endPoint-1)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Monday'",new String[]{courseTitle,String.valueOf(endPoint)});
            }



        }

        if ((temp = scheduleText.indexOf("화")) > -1) {
            int count = 0;
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length(); i++) {
                if (scheduleText.charAt(i) == '/') {
                    endPoint = (int) scheduleText.charAt(i - 2) - 48;
                    count++;
                }
            }
            if (count == 0) {
                endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;
            }
            if ((endPoint - startPoint) == 1) {
                tuesday[startPoint] = courseTitle;
                tuesday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(endPoint)});
            } else if (startPoint == endPoint) {
                tuesday[startPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(startPoint)});

            } else {
                tuesday[startPoint] = courseTitle;
                tuesday[endPoint - 1] = courseTitle;
                tuesday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(endPoint-1)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Tuesday'",new String[]{courseTitle,String.valueOf(endPoint)});
            }
        }

        if ((temp = scheduleText.indexOf("수")) > -1) {
            int count = 0;
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length(); i++) {
                if (scheduleText.charAt(i) == '/') {
                    endPoint = (int) scheduleText.charAt(i - 2) - 48;
                    count++;
                }
            }
            if (count == 0) {
                endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;
            }
            if ((endPoint - startPoint) == 1) {
                wednesday[startPoint] = courseTitle;
                wednesday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(endPoint)});

            } else if (startPoint == endPoint) {
                wednesday[startPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(startPoint)});
            } else {
                wednesday[startPoint] = courseTitle;
                wednesday[endPoint - 1] = courseTitle;
                wednesday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(endPoint-1)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Wednesday'",new String[]{courseTitle,String.valueOf(endPoint)});
            }

        }


        if ((temp = scheduleText.indexOf("목")) > -1) {
            int count = 0;
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length(); i++) {
                if (scheduleText.charAt(i) == '/') {
                    endPoint = (int) scheduleText.charAt(i - 2) - 48;
                    count++;
                }
            }
            if (count == 0) {
                endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;
            }
            if ((endPoint - startPoint) == 1) {
                thursday[startPoint] = courseTitle;
                thursday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(endPoint)});
            } else if (startPoint == endPoint) {
                thursday[startPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(startPoint)});
            } else {
                thursday[startPoint] = courseTitle;
                thursday[endPoint - 1] = courseTitle;
                thursday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(endPoint-1)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Thursday'",new String[]{courseTitle,String.valueOf(endPoint)});
            }
        }

        if ((temp = scheduleText.indexOf("금")) > -1) {
            int count = 0;
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length(); i++) {
                if (scheduleText.charAt(i) == '/') {
                    endPoint = (int) scheduleText.charAt(i - 2) - 48;
                    count++;
                }
            }
            if (count == 0) {
                endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;
            }
            if ((endPoint - startPoint) == 1) {
                friday[startPoint] = courseTitle;
                friday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(endPoint)});
            } else if (startPoint == endPoint) {
                friday[startPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(startPoint)});
            } else {
                friday[startPoint] = courseTitle;
                friday[endPoint - 1] = courseTitle;
                friday[endPoint] = courseTitle;
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(startPoint)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(endPoint-1)});
                db.execSQL("update 'schedule' set 강의명 = ? where 강의시간 = ? AND 요일 = 'Friday'",new String[]{courseTitle,String.valueOf(endPoint)});
            }
        }
    }


    public boolean validate(String scheduleText){
        int temp;
        if((temp = scheduleText.indexOf("월")) > -1){
            temp ++;
            int startPoint = (int)scheduleText.charAt(temp)-48;
            int endPoint = (int)scheduleText.charAt(scheduleText.length()-1)-48;

            if(monday[startPoint].equals("")&&monday[endPoint].equals("")){
                return true;
            }
            return false;
        }

        if((temp = scheduleText.indexOf("화")) > -1){
            temp ++;
            int startPoint = (int)scheduleText.charAt(temp)-48;
            int endPoint = (int)scheduleText.charAt(scheduleText.length()-1)-48;

            if(tuesday[startPoint].equals("")&&tuesday[endPoint].equals("")){
                return true;
            }
            return false;
        }

        if((temp = scheduleText.indexOf("수")) > -1){
            temp ++;
            int startPoint = (int)scheduleText.charAt(temp)-48;
            int endPoint = (int)scheduleText.charAt(scheduleText.length()-1)-48;

            if(wednesday[startPoint].equals("")&&wednesday[endPoint].equals("")){
                return true;
            }
            return false;
        }


        if((temp = scheduleText.indexOf("목")) > -1){
            temp ++;
            int startPoint = (int)scheduleText.charAt(temp)-48;
            int endPoint = (int)scheduleText.charAt(scheduleText.length()-1)-48;

            if(thursday[startPoint].equals("")&&thursday[endPoint].equals("")){
                return true;
            }
            return false;
        }

        if((temp = scheduleText.indexOf("금")) > -1) {
            temp++;
            int startPoint = (int) scheduleText.charAt(temp) - 48;
            int endPoint = (int) scheduleText.charAt(scheduleText.length() - 1) - 48;

            if (friday[startPoint].equals("") && friday[endPoint].equals("")) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void setting (AutoResizeTextView[] monday,AutoResizeTextView[] tuesday,AutoResizeTextView[] wednesday,AutoResizeTextView[] thursday,AutoResizeTextView[] friday){
        for(int i=0;i<12;i++){
            if(!this.monday[i].equals("")){
                monday[i].setText(this.monday[i]);
                monday[i].setBackgroundResource(getColor(getIndex(this.monday[i])));
            }
            if (this.monday[i].equals("")){
                monday[i].setText("가나다라마바사아자차");
            }
            if(!this.tuesday[i].equals("")){
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setBackgroundResource(getColor(getIndex(this.tuesday[i])));
            }
            if (this.tuesday[i].equals("")){
                tuesday[i].setText("가나다라마바사아자차");
            }
            if(!this.wednesday[i].equals("")){
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setBackgroundResource(getColor(getIndex(this.wednesday[i])));
            }
            if (this.wednesday[i].equals("")){
                wednesday[i].setText("가나다라마바사아자차");
            }
            if(!this.thursday[i].equals("")){
                thursday[i].setText(this.thursday[i]);
                Log.d("thurs", this.thursday[i]);
                thursday[i].setBackgroundResource(getColor(getIndex(this.thursday[i])));
            }
            if (this.thursday[i].equals("")){
                thursday[i].setText("가나다라마바사아자차");
            }
            if(!this.friday[i].equals("")){
                friday[i].setText(this.friday[i]);
                friday[i].setBackgroundResource(getColor(getIndex(this.friday[i])));
            }
            if (this.friday[i].equals("")){
                friday[i].setText("가나다라마바사아자차");
            }
            monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();
        }


    }

    public int getIndex(String courseName) {
        int ret = 0;
        for(int i=0;i<courseList.size();i++) {
            if(courseList.get(i).equals(courseName)) {
                Log.d("find same subject", courseList.get(i) + " / " + courseName);
                ret = i;
            }
        }

        Log.d("getIndex : ", Integer.toString(ret) + " / " + Integer.toString(courseList.size()));

        return ret;
    }


    public int getColor(int index) {
        index = index % 6;
        Log.d("where",Integer.toString(index));
        if (index == 0) {
            return R.color.color1;
        } else if (index == 1) {
            return R.color.color2;
        } else if (index == 2) {
            return R.color.color3;
        } else if (index == 3) {
            return R.color.color4;
        } else if (index == 4) {
            return R.color.color5;
        } else {
            return R.color.color6;
        }
    }

    public void setting_init (SQLiteDatabase db) {
        //String emptystr = "";
        db.execSQL("update 'schedule' set 강의명 = ''");
    }

}
