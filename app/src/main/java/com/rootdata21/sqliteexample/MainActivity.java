package com.rootdata21.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_save,btn_read,btn_update,btn_delete;
    EditText edt_name,edt_number,edt_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = findViewById(R.id.btn_Save);
        btn_read = findViewById(R.id.btn_Read);
        btn_update = findViewById(R.id.btn_Update);
        btn_delete = findViewById(R.id.btn_Delete);
        edt_id = findViewById(R.id.edtId);
        edt_name = findViewById(R.id.edtName);
        edt_number = findViewById(R.id.edtNumber);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        Contact_Info contact_info = new Contact_Info("1",edt_name.getText().toString(),edt_number.getText().toString());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addData(new Contact_Info(edt_name.getText().toString(),edt_number.getText().toString()));
                Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_LONG).show();
            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Contact_Info> allDataList = databaseHelper.getAllData();
//                for(int i = 0; i < allDataList.size();i++){
//
//                }

                for(Contact_Info contact_info : allDataList){
                    Toast.makeText(getApplicationContext(),"ID : "+contact_info.getId()+
                            "\nName: "+contact_info.getName()+"\nNumber: "+contact_info.getNumber(),Toast.LENGTH_SHORT).show();
                    Log.d("Data","ID : "+contact_info.getId()+
                            "\nName: "+contact_info.getName()+"\nNumber: "+contact_info.getNumber());
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateContact(new Contact_Info(Integer.parseInt(edt_id.getText().toString()),edt_name.getText().toString(),edt_number.getText().toString()));
                Toast.makeText(getApplicationContext(),"Contact Updated",Toast.LENGTH_LONG).show();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteContact(new Contact_Info(Integer.parseInt(edt_id.getText().toString())));
                Toast.makeText(getApplicationContext(),"Contact Deleted",Toast.LENGTH_LONG).show();
            }
        });

    }
}