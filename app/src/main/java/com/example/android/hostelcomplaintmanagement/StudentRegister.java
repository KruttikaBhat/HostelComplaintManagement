package com.example.android.hostelcomplaintmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentRegister extends AppCompatActivity {


    EditText rollno,name,phno,password,gender,hostel,room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        rollno=(EditText)findViewById(R.id.regRollno);
        name=(EditText)findViewById(R.id.regName);
        phno=(EditText)findViewById(R.id.regPhno);
        password=(EditText)findViewById(R.id.regPassword);
        gender=(EditText)findViewById(R.id.regGen);
        hostel=(EditText)findViewById(R.id.regHostel);
        room=(EditText)findViewById(R.id.regRoomNo);

    }

    public void OnReg(View view){
        String str_rollno=rollno.getText().toString();
        String str_name=name.getText().toString();
        String str_phno=phno.getText().toString();
        String str_password=password.getText().toString();
        String str_gender=gender.getText().toString();
        String str_hostel=hostel.getText().toString();
        String str_room=room.getText().toString();
        String type="register";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,str_rollno,str_name,str_phno,str_password,str_gender,str_hostel,str_room);

    }
}
