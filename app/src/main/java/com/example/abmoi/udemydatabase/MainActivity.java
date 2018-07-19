package com.example.abmoi.udemydatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    private static  final  String Tag="MainActivity";
    private TextView mDisplay_SUB_DATE;
    private TextView mDisplayDATE_OPEN_BC;
    private Button BTNdate_SUB;
    private Button BTNdate_OPEN_BC;
    private Button BTNshow_members;


    private DatePickerDialog.OnDateSetListener mDateSetListener_SUB;
    private DatePickerDialog.OnDateSetListener mDateSetListener_OPEN;

 //buttons for add

    private Button BTNaddMember;
    private EditText ED_Name;
    private EditText ED_BcAmount;
    private EditText ED_BcNumber;
    private ListActivity list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dbManager= new DBManager(this);
         list= new ListActivity();







//ADD MEMBERS SIDE

        BTNaddMember=(Button) findViewById(R.id.ADDmember);
        ED_Name=(EditText) findViewById(R.id.EDname);
        ED_BcAmount=(EditText)findViewById(R.id.EDbcAmount);
        ED_BcNumber=(EditText)findViewById(R.id.EDbcNumber);
        BTNshow_members=(Button)findViewById(R.id.Show_Members);






        BTNdate_SUB=(Button) findViewById(R.id.SUB_DATE);
        mDisplay_SUB_DATE=(TextView) findViewById(R.id.SUB_TEXT);
        mDisplayDATE_OPEN_BC=(TextView) findViewById(R.id.OPENbc_TEXT);
        BTNdate_OPEN_BC=(Button)findViewById(R.id.BTN_OPENbc);

        //submission date
        BTNdate_SUB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int Year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog Dialog= new DatePickerDialog(MainActivity.this ,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,mDateSetListener_SUB,Year,Month,Day);
                 Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Dialog.show();



            }
        });
        //submission date

         //submission date
        mDateSetListener_SUB =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month+1;
                String date= month+"/"+ day+"/"+year;

              mDisplay_SUB_DATE.setText(date);
            }
        };

        //submission DATEPICKER


        //OPEN BC DATEPICKER

        //submission date
        BTNdate_OPEN_BC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();

                int Year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog Dialog= new DatePickerDialog(MainActivity.this ,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,mDateSetListener_OPEN,Year,Month,Day);
                Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Dialog.show();



            }
        });
        //submission date

        //submission date
        mDateSetListener_OPEN =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month+1;
                String date= month+"/"+ day+"/"+year;

                mDisplayDATE_OPEN_BC.setText(date);
            }
        };

      //show All members

        BTNshow_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(intent1);

                }});

        BTNshow_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.ShowAllRECords(view);
            }
        });



    }//END ON CREATE METHOD

    public void REG_CUSTOMER(View v){
        String name= ED_Name.getText().toString();
        String BC_Number= ED_BcNumber.getText().toString();
        String BC_Amount= ED_BcAmount.getText().toString();
        String Sub_DATE=mDisplay_SUB_DATE.getText().toString();
        String OPen_DATE=mDisplayDATE_OPEN_BC.getText().toString();
        Log.d(">>>", "variables set");
      try{
          dbManager.open();
          Log.d(">>>", "db open");
          Log.d(">>>", "name: " + name);
          Log.d(">>>", "BC Number " + BC_Number);
          Log.d(">>>", "BC Amount " + BC_Amount);
          Log.d(">>>", "Sub Date " + Sub_DATE);
          Log.d(">>>", "Open : " + OPen_DATE);


          dbManager.NewCustomer(name,BC_Number,BC_Amount,Sub_DATE,OPen_DATE);
          Toast.makeText(getApplicationContext(),"ok hogya",Toast.LENGTH_SHORT).show();
          ED_Name.setText(" ");
          ED_BcAmount.setText("");
          ED_BcNumber.setText("");
          mDisplay_SUB_DATE.setText("00-00-00");
          mDisplayDATE_OPEN_BC.setText("00-00-00");
      }
      catch (Exception e){
          e.printStackTrace();
      }
        finally {
          dbManager.close();
      }






    }








}//END CLASS METHOD
