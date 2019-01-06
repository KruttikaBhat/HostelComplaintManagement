package com.example.android.hostelcomplaintmanagement;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class NetComplaints extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_complaints);
        listView=(ListView)findViewById(R.id.NETlistview);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new NetComplaints.Connection().execute();
    }

    class Connection extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result="";
            String host="http://192.168.43.201/android/v1/netComplaints.php";

            try {
                HttpClient client=new DefaultHttpClient();
                HttpGet request=new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response=client.execute(request);
                BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer stringBuffer=new StringBuffer("");
                String line="";
                while((line=reader.readLine())!=null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result=stringBuffer.toString();
            } catch (Exception e) {
                return new String("There exception: "+e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResult=new JSONObject(result);
                int success=jsonResult.getInt("success");
                if(success==1){
                    JSONArray complaints=jsonResult.getJSONArray("complaint");
                    for (int i=0;i<complaints.length();i++){
                        JSONObject complaint=complaints.getJSONObject(i);
                        String rollno=complaint.getString("Student_Rollno");
                        String hostel=complaint.getString("HOS_NAME");
                        String desc=complaint.getString("Description");
                        String idate=complaint.getString("Issued_date");
                        String room=complaint.getString("Room_no");
                        String tid=complaint.getString("TECH_ID");
                        String status=complaint.getString("Status");
                        String line=rollno+" "+hostel+" "+desc+" "+idate+" "+room+" "+tid+" "+status;
                        adapter.add(line);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"no network complaints",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
