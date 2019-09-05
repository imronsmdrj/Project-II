package com.ron.barcodepos.getData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ron.barcodepos.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Contacts> contacts;
    Context context;

    public Adapter(Context context, List<Contacts> contacts){
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.bid.setText(contacts.get(position).getBid());
        holder.bnama.setText(contacts.get(position).getBnama());
        holder.bsatuan.setText(contacts.get(position).getBsatuan());
        holder.bharga_pokok.setText(contacts.get(position).getBharga_pokok());
        holder.bharga_jual.setText(contacts.get(position).getBharga_jual());
        holder.bharga_jual_grosir.setText(contacts.get(position).getBharga_jual_grosir());
        holder.bstok.setText(contacts.get(position).getBstok());
        holder.bkategori_id.setText(contacts.get(position).getBkategori_id());
    }

    @Override
    public int getItemCount(){
        return contacts == null ? 0 : contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bid, bnama, bsatuan, bharga_pokok, bharga_jual, bharga_jual_grosir, bstok, bkategori_id;
        public MyViewHolder(View view){
            super(view);
            bid = view.findViewById(R.id.bid);
            bnama = view.findViewById(R.id.bnama);
            bsatuan = view.findViewById(R.id.bsatuan);
            bharga_pokok = view.findViewById(R.id.bharga_pokok);
            bharga_jual = view.findViewById(R.id.bharga_jual);
            bharga_jual_grosir = view.findViewById(R.id.bharga_jual_grosir);
            bstok = view.findViewById(R.id.bstok);
            bkategori_id = view.findViewById(R.id.bkategori_id);
        }
    }
}
