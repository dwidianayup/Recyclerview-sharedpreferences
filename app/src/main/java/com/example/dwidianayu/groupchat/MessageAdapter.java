package com.example.dwidianayu.groupchat;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDK PEMKOT SMG 6 on 11/3/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MAdapter> {

    List<JSONObject> Jsondata = new ArrayList<>();
    JSONArray jsonArray;

    public MessageAdapter(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        for (int i=0;i<this.jsonArray.length();i++)
            try {
                Jsondata.add(this.jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contac,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position){
        JSONObject jsonObject = Jsondata.get(position);
        try {
            holder.dp.setImageResource(jsonObject.getInt("foto"));
            holder.nama.setText(jsonObject.getString("nama"));
            holder.pesan.setText(jsonObject.getString("pesan"));
            holder.waktu.setText(jsonObject.getString("waktu"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){
        return Jsondata.size();
    }

    public class MAdapter extends RecyclerView.ViewHolder{
        ImageView dp;
        TextView nama, pesan, waktu;

        public MAdapter(View itemView) {
            super(itemView);
            dp = (ImageView)itemView.findViewById(R.id.foto);
            nama = (TextView)itemView.findViewById(R.id.sender);
            pesan = (TextView)itemView.findViewById(R.id.conten);
            waktu = (TextView)itemView.findViewById(R.id.tanggal);
        }
    }
}
