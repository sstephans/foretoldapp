package com.twocrows.foretoldapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.twocrows.foretoldapp.R;
import com.twocrows.foretoldapp.entity.Chart;

import java.util.ArrayList;
import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ChartHolder> {
    private List<Chart> charts = new ArrayList<>();

    @NonNull
    @Override
    public ChartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chart_item, parent, false);
        return new ChartHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChartHolder holder, int position) {
        Chart currentChart = charts.get(position);
        holder.textViewName.setText(currentChart.getName());
        holder.textViewLocation.setText(currentChart.getLocation());
        holder.textViewDateTime.setText(currentChart.getMonth() + "/" +
                currentChart.getDay() + "/" +
                currentChart.getYear() + " " +
                currentChart.getHour() + ":" +
                currentChart.getMinute());
    }

    @Override
    public int getItemCount() {
        return charts.size();
    }

    public void setCharts(List<Chart> charts) {
        this.charts = charts;
        notifyDataSetChanged(); // will probably replace with a better one
    }

    class ChartHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLocation;
        private TextView textViewDateTime;

        public ChartHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewDateTime = itemView.findViewById(R.id.text_view_dateTime);
        }
    }
}
