package com.example.dwidianayu.groupchat;

import android.content.SharedPreferences;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editt;
    EditText input_nama, input_pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        input_nama = (EditText)findViewById(R.id.input_nama);
        input_pesan = (EditText)findViewById(R.id.input_pesan);
        preferences = getSharedPreferences(MainActivity.mainPref,0);
        editt = preferences.edit();
    }

    public void kirim(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama",input_nama.getText().toString());
            jsonObject.put("pesan",input_pesan.getText().toString());
            jsonObject.put("waktu",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("foto",R.drawable.mario);
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (preferences.contains("message")){
            String dataPesan = preferences.getString("message","");
            try {
                JSONArray jsonArray = new JSONArray(dataPesan);
                jsonArray.put(jsonObject);
                editt.putString("message",jsonArray.toString());
                editt.apply();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editt.putString("message", jsonArray.toString());
            editt.apply();
        }
        finish();
    }
}
