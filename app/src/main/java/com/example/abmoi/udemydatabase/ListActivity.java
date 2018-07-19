package com.example.abmoi.udemydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
  private ListView List_DB;

  DBManager DBmanger;

    ArrayAdapter ADAPTER_LIST;
    ArrayList<String> List_DB_ARRAYLIST =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       List_DB =(ListView) findViewById(R.id.LIST_DB);
       DBmanger= new DBManager(this);

       ADAPTER_LIST= new ArrayAdapter(this,android.R.layout.simple_list_item_1,List_DB_ARRAYLIST);
       List_DB.setAdapter(ADAPTER_LIST);



    }



    public  void  ShowAllRECords(View v){
      ADAPTER_LIST.clear();
      try{
          DBmanger.open();
          ADAPTER_LIST.add(DBmanger.GetALLrecords());

      }catch (Exception ex){
         ex.printStackTrace();
      }finally {
          DBmanger.close();
      }


            }





}
