package com.ayush.iwarden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    Context mcontext;
    ArrayList<StudentResult> results;

    public ResultAdapter(){

    }

    public ResultAdapter(ArrayList<StudentResult> results){
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout,parent,false);
        if(getItemCount()==0)
            return null;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getItemCount()!=0) {
            holder.name.setText(results.get(position).getMname());
            holder.room.setText(String.valueOf(results.get(position).getMroom()));
            holder.id.setText(String.valueOf(results.get(position).getMid()));
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView room;
        TextView id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(getItemCount()!=0) {
                name = itemView.findViewById(R.id.stu_name_placeholder);
                room = itemView.findViewById(R.id.stu_room_placeholder);
                id = itemView.findViewById(R.id.stu_id_placeholder);
            }
        }
    }
}
