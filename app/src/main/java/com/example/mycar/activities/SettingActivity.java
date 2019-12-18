package com.example.mycar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycar.R;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.model.User;
import com.example.mycar.pojo.Users;
import com.example.mycar.storage.SharedPrefrencesManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {

    private TextView logOut;
    private Button editProfile;
    private ImageView imageProfile;
    private EditText nameProfle;
    private EditText lastNameProfle;
    private EditText machineNameProfile;
    private EditText numberplateProfile;
    private TextView userNameProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        logOut = findViewById(R.id.text_view_logout);
        imageProfile = findViewById(R.id.image_profile);
        nameProfle = findViewById(R.id.name_profile_setting);
        lastNameProfle = findViewById(R.id.last_name_profile_setting);
        userNameProfile = findViewById(R.id.username_profile_setting);
        machineNameProfile = findViewById(R.id.machine_name_setting);
        numberplateProfile = findViewById(R.id.numberplate_setting);
        //editProfile = findViewById(R.id.btn_change_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save){

        }

        return super.onOptionsItemSelected(item);
    }

    /*private void updateProfile(final int userId, HashMap<String, String> map) {
        if (!validate()) {
            Toast.makeText(SettingActivity.this, "دوباره بررسی کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Result> call = RetrofitClient
                .getInstance()
                .getApi()
                .updateProfile(userId, map);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result.getResult().equals("updated")) {
                    Toast.makeText(getContext(), "profile updated", Toast.LENGTH_SHORT).show();

                    Fragment frg = getActivity().getSupportFragmentManager().findFragmentByTag("editProfile");
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();
                } else {
                    Toast.makeText(getContext(), "failed update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        });
    }*/

    public void getUser() {
        int userId = SharedPrefrencesManager.getInstance(SettingActivity.this).getUserId();
        Call<Users> call = RetrofitClient.getInstance().getApi().getUser(userId);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users result = response.body();
                List<User> users = result.getUsers();
                nameProfle.setText(users.get(0).getName());
                lastNameProfle.setText(users.get(0).getLastName());
                userNameProfile.setText(users.get(0).getUserName());
                machineNameProfile.setText(users.get(0).getMachineName());
                numberplateProfile.setText(users.get(0).getNumberplate());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(SettingActivity.this, "there is problem in server." +
                        "\n try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameProfle.getText().toString().trim();
        String lastName = lastNameProfle.getText().toString().trim();
        String machineName = machineNameProfile.getText().toString().trim();
        String numberplate = numberplateProfile.getText().toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            nameProfle.setError("enter a valid username");
            valid = false;
        } else {
            nameProfle.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 5) {
            lastNameProfle.setError("More than 5 alphanumeric characters");
            valid = false;
        } else {
            lastNameProfle.setError(null);
        }

        if (machineName.isEmpty() || machineName.length() < 3) {
            machineNameProfile.setError("enter a valid username");
            valid = false;
        } else {
            machineNameProfile.setError(null);
        }

        if (numberplate.isEmpty() || numberplate.length() < 3) {
            numberplateProfile.setError("enter a valid username");
            valid = false;
        } else {
            numberplateProfile.setError(null);
        }

        return valid;
    }
}
