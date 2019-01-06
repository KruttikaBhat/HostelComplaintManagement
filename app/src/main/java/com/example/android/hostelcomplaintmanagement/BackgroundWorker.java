package com.example.android.hostelcomplaintmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dell on 5/5/18.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String>{
    Context context;
    AlertDialog alertDialog;
    String id;
    public BackgroundWorker(Context ctx){context=ctx;}

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://192.168.43.201/android/v1/login.php";
        String register_url="http://192.168.43.201/android/v1/register.php";
        String electrical_url="http://192.168.43.201/android/v1/electricalComplaint.php";
        String techLogin_url="http://192.168.43.201/android/v1/techLogin.php";
        String adminLogin_url="http://192.168.43.201/android/v1/adminLogin.php";
        if(type.equals("login")){
            try {
                String Roll_no=params[1];
                id=Roll_no;
                String Password=params[2];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("Roll_no","UTF-8")+"="+URLEncoder.encode(Roll_no,"UTF-8")+ "&"
                        + URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(type.equals("register")){
            try {
                String rollno=params[1];
                String name=params[2];
                String phno=params[3];
                String gender=params[4];
                String password=params[5];
                String hostel=params[6];
                String room=params[7];
                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=
                        URLEncoder.encode("Roll_no","UTF-8")+"="+URLEncoder.encode(rollno,"UTF-8")+ "&"
                                + URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+ "&"
                                + URLEncoder.encode("Ph_no","UTF-8")+"="+URLEncoder.encode(phno,"UTF-8")+ "&"
                                + URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+ "&"
                                + URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+ "&"
                                + URLEncoder.encode("HOS_NAME","UTF-8")+"="+URLEncoder.encode(hostel,"UTF-8")+ "&"
                                + URLEncoder.encode("R_NO","UTF-8")+"="+URLEncoder.encode(room,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(type.equals("electrical")){
            try {
                String desc=params[1];
                String idate=params[2];
                String psdate=params[3];
                String room=params[4];
                String rollno=params[5];
                URL url=new URL(electrical_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=
                        URLEncoder.encode("Description","UTF-8")+"="+URLEncoder.encode(desc,"UTF-8")+ "&"
                                + URLEncoder.encode("Issued_date","UTF-8")+"="+URLEncoder.encode(idate,"UTF-8")+ "&"
                                + URLEncoder.encode("ProbStart_date","UTF-8")+"="+URLEncoder.encode(psdate,"UTF-8")+ "&"
                                + URLEncoder.encode("Room_no","UTF-8")+"="+URLEncoder.encode(room,"UTF-8")+ "&"
                                + URLEncoder.encode("Student_Rollno","UTF-8")+"="+URLEncoder.encode(rollno,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(type.equals("loginTech")){
            try {
                String id=params[1];
                String password=params[2];
                URL url=new URL(techLogin_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=
                        URLEncoder.encode("T_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+ "&"
                                + URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(type.equals("loginAdmin")){
            try {
                String id=params[1];
                String password=params[2];
                URL url=new URL(adminLogin_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=
                        URLEncoder.encode("Admin_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+ "&"
                                + URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        if(result.equals("login success")){
            Intent intent = new Intent(context, StudentHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Id", id);
            context.startActivity(intent);
        }
        else if(result.equals("successfully logged in")){
            Intent intent = new Intent(context, ELETechhome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else if(result.equals("logged in successfully")){
            Intent intent = new Intent(context, AdminHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
