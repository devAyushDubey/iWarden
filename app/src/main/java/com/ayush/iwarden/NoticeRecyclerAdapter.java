package com.ayush.iwarden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.ViewHolder> {

    ArrayList<NoticeClass> notices;

    public NoticeRecyclerAdapter(ArrayList<NoticeClass> notice){
        notices = notice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.author.setText(notices.get(position).getmAuthor());
        holder.author.setTextColor(notices.get(position).getmColor());
        holder.author.setCompoundDrawablesRelativeWithIntrinsicBounds(notices.get(position).getmIcon(),0,0,0);
        holder.notice.setText(notices.get(position).getmNotice());
        holder.dates.setText(notices.get(position).getmDate());
        holder.back.setBackground(notices.get(position).getmBack());
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView author;
        TextView dates;
        RelativeLayout back;
        TextView notice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author_placeholder);
            dates = itemView.findViewById(R.id.date_placeholder);
            back = itemView.findViewById(R.id.Card_back);
            notice = itemView.findViewById(R.id.Notice_placeholder);
        }
    }
}
