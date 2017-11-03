package com.example.dwidianayu.groupchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    public static String mainPref = "file.main.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences preferences = getSharedPreferences(mainPref,0);
        String dataPesan = preferences.getString("message","");

        try {
            JSONArray jsonArray = new JSONArray(dataPesan);
            messageAdapter = new MessageAdapter(jsonArray);

            recyclerView.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("JSON", dataPesan);
    }

    public void add(View view) {
        Intent it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_clear,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.clear){
            SharedPreferences sharedPreferences = getSharedPreferences(mainPref,0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();

            finish();
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}
