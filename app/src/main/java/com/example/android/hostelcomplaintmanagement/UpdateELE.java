package com.example.android.hostelcomplaintmanagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateELE extends AppCompatActivity {
    String HttpURL = "http://192.168.43.201/android/v1/updateELE.php";
    ProgressDialog progressDialog;
    String finalResult;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText cid,date;
    Button UpdateStudent;
    String cidHolder,dateHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ele);
        cid = (EditText)findViewById(R.id.cid);
        date=(EditText)findViewById(R.id.resDate);
        UpdateStudent = (Button)findViewById(R.id.updateButton);

        // Adding click listener to update button .
        UpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
                StudentRecordUpdate(cidHolder,dateHolder);

            }
        });

    }

    public void GetDataFromEditText(){
        cidHolder = cid.getText().toString();
        dateHolder=date.getText().toString();
    }
    // Method to Update Student Record.
    public void StudentRecordUpdate(final String ID,final String resDate){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateELE.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateELE.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("C_id",params[0]);
                hashMap.put("Resolved_date",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,resDate);
    }

}
