package com.example.mycar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycar.JalaliCalendar;
import com.example.mycar.R;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.model.Service;
import com.example.mycar.pojo.Result;
import com.example.mycar.pojo.Services;
import com.example.mycar.receiver.MyBroadcastReceiver;
import com.example.mycar.storage.SharedPrefrencesManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddService extends AppCompatActivity implements View.OnClickListener {

    private EditText nameServiceEditText;
    private EditText serviceKilometerEditText;
    private EditText estimateKilometerEditText;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        nameServiceEditText = findViewById(R.id.add_service_name);
        serviceKilometerEditText = findViewById(R.id.add_service_kilometer);
        estimateKilometerEditText = findViewById(R.id.add_estimate_kilometer);

        Button cancel = findViewById(R.id.cancel_service);
        cancel.setOnClickListener(this);

        Button saveService = findViewById(R.id.save_service);
        saveService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_service:
                finish();
                break;
            case R.id.save_service:
                addService();
                break;
        }
    }

    private void addService() {
        String nameService = nameServiceEditText.getText().toString().trim();
        String serviceKilometer = serviceKilometerEditText.getText().toString().trim();
        String estimateKilometer = estimateKilometerEditText.getText().toString().trim();

        JalaliCalendar jalaliCalendar = new JalaliCalendar(Calendar.getInstance());
        String date = jalaliCalendar.get(Calendar.YEAR)
                + "/" + (jalaliCalendar.get(Calendar.MONTH) + 1)
                + "/" + jalaliCalendar.get(Calendar.DAY_OF_MONTH);

        final int userId = SharedPrefrencesManager.getInstance(AddService.this).getUserId();

        Call<Result> call = RetrofitClient.getInstance().getApi().addService(nameService, userId, serviceKilometer, estimateKilometer);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result myResponse = response.body();
                result = myResponse.getResult();
                switch (result) {
                    case "inserted":

                        launchAlarm(userId);
                        Toast.makeText(AddService.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case "failed insert":
                        Toast.makeText(AddService.this, "اشکال در ثبت", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(AddService.this, "مشکل از سمت سرور می باشد." +
                        "\n .دوباره امتحان کنید", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void launchAlarm(int userId) {
        Call<Services> call2 = RetrofitClient
                .getInstance()
                .getApi()
                .getLastService(userId, 0);

        call2.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Services result = response.body();
                Service service = result.getServices().get(0);

                /*Intent intent = new Intent(AddService.this, MyBroadcastReceiver.class);
                intent.putExtra("serviceName", service.getName());
                intent.putExtra("notificationDate", service.getNotificationDate());
                sendBroadcast(intent);*/

                new MyBroadcastReceiver().setAlarm(AddService.this, result.getServices().get(0).getNotificationMilisecond());
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }
}
