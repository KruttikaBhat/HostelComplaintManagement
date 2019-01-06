package com.example.android.hostelcomplaintmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentLogin extends AppCompatActivity {

    EditText rn,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        rn=(EditText)findViewById(R.id.studentRollno);
        pw=(EditText)findViewById(R.id.studentPassword);
    }

    public void OnLogin(View view){
        String rollno=rn.getText().toString();
        String password=pw.getText().toString();
        String type="login";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,rollno,password);
    }

    public void OpenReg(View view){
        startActivity(new Intent(this,StudentRegister.class));
    }
}
