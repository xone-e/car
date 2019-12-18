package com.example.mycar.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycar.R;
import com.example.mycar.model.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnfinishedTaskAdapter extends RecyclerView.Adapter<UnfinishedTaskAdapter.ListViewHolder> {

    private List<Service> mServices = new ArrayList<>();
    private Context mContext;

    public UnfinishedTaskAdapter(List<Service> mServices, Context mContext) {
        this.mServices = mServices;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_unfinished_list_item, viewGroup, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Service service = mServices.get(position);
        holder.serviceName.setText(service.getName());
        holder.serviceEstimateKilometer.setText(service.getEstimateKilometer());
        holder.serviceNotificationDate.setText(service.getNotificationDate());
        holder.done.setChecked(service.getIsDone());

        holder.done.setTag(position);

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.done.getTag();

                if (!mServices.get(pos).getIsDone()) {
                    //Toast.makeText(mContext, "checked!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private String getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mdate = null;
        try {
            mdate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long  md = (mdate.getTime());

        return sdf.format(md);
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView serviceName;
        public TextView serviceEstimateKilometer;
        public TextView serviceNotificationDate;
        public CheckBox done;

        public ListViewHolder(View itemView) {
            super(itemView);

            serviceName = itemView.findViewById(R.id.service_name);
            serviceEstimateKilometer = itemView.findViewById(R.id.service_estimate_kilometer);
            serviceNotificationDate = itemView.findViewById(R.id.service_notification_date);
            done = itemView.findViewById(R.id.done_checkbox);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
