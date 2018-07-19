package com.example.abmoi.udemydatabase;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Abmoi on 10/3/2017.
 */

public class DBManager {
    DatabaseHelperUser DD;
    private Context c;
    private SQLiteDatabase sqlDB;
    private static final String DBName="BCSYSTEM";
    private static final int DBVersion =1;



    public DBManager(Context context){
        this.c=context;

    }






    private    static final String TableName="CUSTOMER";
    private    static final String ColUserName ="UserName";
    private    static final String ColBCnumber ="BCnumber";
    private    static final String ColBCanount ="BCAmount";
    private    static final String ColsubmissionDate ="SUB_DATE";
    private    static final String ColOpenBcDate ="OPEN_DATE";













private static  final String CreateTable_CUSTOMER="CREATE TABLE IF NOT EXISTS "
        +TableName+ " " +
        "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        ColUserName+" TEXT,"+
        ColBCnumber+" INTEGER,"+
        ColBCanount+" INTEGER,"+
        ColsubmissionDate+" DATE,"+
        ColOpenBcDate+" DATE)";

//***********************INSERT DATA

    public Long NewCustomer(String Name,String BcNumber,String BcAmount,String SubmissionDate,String OPenBcDate){
        ContentValues CV= new ContentValues();


        DateFormat format = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
        Date thisDate = null;
        try {
            thisDate = format.parse(SubmissionDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        CV.put(ColUserName,Name);
        CV.put(ColBCnumber,BcNumber);
        CV.put(ColBCanount,BcAmount);

         CV.put(ColsubmissionDate,SubmissionDate);
        CV.put(ColOpenBcDate,OPenBcDate);


        Log.d(">>>", "db open");
        Log.d(">>>", "name: " + Name);
        Log.d(">>>", "BC Number " + BcNumber);
        Log.d(">>>", "BC Amount " + BcAmount);
        Log.d(">>>", "Sub Date " + thisDate);
        Log.d(">>>", "Open : " + thisDate);

return sqlDB.insert(TableName,null,CV);
      //  return Long.parseLong("1");

    }



    /// SHOW ALL RECORDS IN LIST


    public  String GetALLrecords(){
        String Result="";

        String[]  Columns={"id",ColUserName,ColBCnumber,ColBCanount,ColOpenBcDate,ColsubmissionDate};
        Cursor C=sqlDB.rawQuery("SELECT * FROM "+TableName+" order by id ",null);
        int iID=C.getColumnIndex("id");
        int iColUserName=C.getColumnIndex(ColUserName);
        int iColBCnumber=C.getColumnIndex(ColBCnumber);
        int iColBCamount=C.getColumnIndex(ColBCanount);
        int iColOpenBCdate=C.getColumnIndex(ColOpenBcDate);
        int iColSubmissionDate=C.getColumnIndex(ColsubmissionDate);


        for (C.moveToFirst(); !C.isAfterLast(); C.moveToNext()){
            Result +="ID:" + C.getString(iID)+" "+C.getString(iColUserName)+" "+C.getString(iColBCnumber)+" "+C.getString(iColBCnumber);
            Result +=" "+C.getString(iColOpenBCdate)+" "+C.getString(iColSubmissionDate)+" \n";

        }


    return  Result;
    }



private static  class  DatabaseHelperUser extends SQLiteOpenHelper{
Context context;

public DatabaseHelperUser(Context context){
    super(context,DBName,null,DBVersion);
    this.context=context;
}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CreateTable_CUSTOMER);
        Toast.makeText(context,"CUSTOMER TABLE IS CREATED SUCESSFULLLY",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("Drop table IF EXISTS "+ TableName);
        onCreate(sqLiteDatabase);
    }
}


//DATABASE OPEN AND CLOSE




    public DBManager open() throws SQLException{
        DD=new DatabaseHelperUser(c);
        sqlDB=DD.getWritableDatabase();
                return this;
    }

public void close(){
    DD.close();
}

}
