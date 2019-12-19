package com.example.mycar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycar.R;
import com.example.mycar.adapters.UnfinishedTaskAdapter;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.model.Service;
import com.example.mycar.pojo.Services;
import com.example.mycar.receiver.MyBroadcastReceiver;
import com.example.mycar.storage.SharedPrefrencesManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnfinishedTaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Service> services;
    private int userId;

    public UnfinishedTaskFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);

        userId = SharedPrefrencesManager.getInstance(getContext()).getUserId();

        Log.d("tarikhe",Calendar.getInstance() + "");

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE MMM dd HH:mm:ss OOOO yyyy", Locale.ROOT);
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        long timeInMilliseconds = OffsetDateTime.parse(givenDateString, formatter)
                .toInstant()
                .toEpochMilli();
        System.out.println("Date in milli :: USING ThreeTenABP >>> " + timeInMilliseconds);*/

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "18-12-2019";
        try {
            Date date = sdf.parse(dateInString);
            long  m = (date.getTime())  + 1579042800;

            System.out.println("Milliseconds to Date: " + sdf.format(m));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getAllServices();



    }

    private void getAllServices() {
        Call<Services> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllServices(userId, 0);

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Services result = response.body();
                services = result.getServices();

                UnfinishedTaskAdapter adapter = new UnfinishedTaskAdapter(services, getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }
}
