package com.example.wakemeup.ui.home;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import com.example.wakemeup.R;

public class AlarmStopItemAdapter extends RecyclerView.Adapter<AlarmStopItemAdapter.ViewHolder> {

    List<Model> itemList1;
    private Context context;

    public AlarmStopItemAdapter(List<Model> itemList,Context context) {

        this.itemList1 = itemList;
        this.context = context;

    }

    @NonNull
    @Override
    public AlarmStopItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alarm_stops,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmStopItemAdapter.ViewHolder holder, final int position) {

        holder.lineImage.setColorFilter(itemList1.get(position).getLineColor());
        holder.minutes.setText(itemList1.get(position).getMinutesToTravel());
        holder.stops.setText(itemList1.get(position).getStopInBetween());
        holder.destination.setText(itemList1.get(position).getDestination());
        holder.alarmToggle.setChecked(itemList1.get(position).isSwitchAlarm());
    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView lineImage;
        TextView minutes;
        TextView stops;
        TextView destination;
        SwitchCompat alarmToggle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineImage = itemView.findViewById(R.id.iv_line);
            minutes = itemView.findViewById(R.id.tv_minutes);
            stops = itemView.findViewById(R.id.tv_stops);
            destination = itemView.findViewById(R.id.tv_destination);
            alarmToggle = itemView.findViewById(R.id.switch_alarm);
        }
    }
}