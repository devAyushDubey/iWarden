package com.ayush.iwarden;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DDRecyclerAdapter extends RecyclerView.Adapter<DDRecyclerAdapter.ViewHolder> {

    ArrayList<DDClass> mdds;
    Context mcontext;
    public DDRecyclerAdapter(Context context, ArrayList<DDClass> dds){
        mcontext = context;
        mdds = dds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dd_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mdds.get(position).getMname());
        holder.icon.setImageResource(mdds.get(position).getMicon_id());
        if(mdds.get(position).isDone()){
            holder.back.setBackground(mcontext.getResources().getDrawable(R.drawable.gradient2));
        }
        else {
            holder.back.setBackground(mcontext.getResources().getDrawable(R.drawable.gradient3));
        }
    }

    @Override
    public int getItemCount() {
        return mdds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView name;
        RelativeLayout back;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.ico_placeholder);
            name = itemView.findViewById(R.id.name_placeholder);
            back = itemView.findViewById(R.id.back);
        }
    }
}
