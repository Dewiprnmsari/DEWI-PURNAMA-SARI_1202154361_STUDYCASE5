package com.example.android.dewipurnamasari_1202154361_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 3/25/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    private Context mContext;
    private List<AddData> list;
    int color;

    //sebagai konstruktor
    public Adapter(Context cntx, List<AddData> list, int color){
        this.mContext = cntx;
        this.list = list;
        this.color = color;
    }

    //menentukan viewholder untuk recycleview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false);
        holder hldr = new   holder(view);
        return hldr;
    }

    //mengatur nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Desc.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.card_View.setCardBackgroundColor(mContext.getResources().getColor(this.color));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan nilai list dari adapter
    public AddData getData (int position){
        return list.get(position);
    }

    //menghapus list pada todolist
    public void deleteData (int i){
        list.remove(i); //menghapus itemt yang dipilih
        //memberi notifikasi item yang dihapus
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());;

    }

    class holder extends RecyclerView.ViewHolder{

        public TextView ToDo, Desc, Priority;
        public CardView card_View;
        public holder(View itemView){
            super(itemView);


            ToDo = itemView.findViewById(R.id.todo);
            Desc = itemView.findViewById(R.id.description);
            Priority = itemView.findViewById(R.id.number);
            card_View = itemView.findViewById(R.id.cardview);
        }
    }
}
