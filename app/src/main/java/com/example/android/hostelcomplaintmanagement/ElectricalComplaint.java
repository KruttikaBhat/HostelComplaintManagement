package com.example.android.hostelcomplaintmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ElectricalComplaint extends AppCompatActivity {

    EditText desc,Idate,PSdate,room;
    String Rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical_complaint);
        desc=(EditText)findViewById(R.id.desc);
        Idate=(EditText)findViewById(R.id.issuedDate);
        PSdate=(EditText)findViewById(R.id.probStart);
        room=(EditText)findViewById(R.id.ELEroom);
        Rollno= getIntent().getStringExtra("RollNo");
        //Toast.makeText(getApplicationContext(),Rollno,Toast.LENGTH_LONG).show();
    }

    public void EnterComp(View view){
        String str_desc=desc.getText().toString();
        String str_Idate=Idate.getText().toString();
        String str_PSdate=PSdate.getText().toString();
        String str_room=room.getText().toString();
        String str_rollno=Rollno.trim();
        String type="electrical";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,str_desc,str_Idate,str_PSdate,str_room,str_rollno);

    }
}
