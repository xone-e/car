package com.example.mycar.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycar.R;
import com.example.mycar.adapters.UnfinishedTaskAdapter;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.model.Service;
import com.example.mycar.pojo.Services;
import com.example.mycar.storage.SharedPrefrencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishTaskFrafment extends Fragment {

    private RecyclerView recyclerView;
    private List<Service> services;
    private int userId;

    public FinishTaskFrafment() {
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /*Call<Services> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllServices(userId, false);

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Services result = response.body();
                services = result.getServices();
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });

        UnfinishedTaskAdapter adapter = new UnfinishedTaskAdapter(services, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);*/
    }
}
