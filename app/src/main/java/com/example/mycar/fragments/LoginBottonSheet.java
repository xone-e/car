package com.example.mycar.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycar.R;
import com.example.mycar.activities.MainActivity;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.model.User;
import com.example.mycar.storage.SharedPrefrencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginBottonSheet extends BottomSheetDialogFragment {

    private static final String TAG = "LoginBottonSheet";
    public static final String PRFS_FILE = "com.example.mycar";
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";

    private EditText userNameText;
    private EditText passwordText;
    private Button login;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private int userId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_bottom_sheet, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userNameText = view.findViewById(R.id.input_userName);
        passwordText = view.findViewById(R.id.input_password);

        login = view.findViewById(R.id.login_bottom_sheet);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Button cancel = view.findViewById(R.id.cancel_bottom_sheet);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private  void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(getContext(), R.style.AppThemeMyLightProgressBar);
        dialog.setMessage("لطفا صبر کنید...");
        dialog.setIndeterminate(true);
        dialog.show();

        String userName = userNameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();


        // new MyProgressDialog(this, userName, password).execute();

        //ApiInterface mApiService = this.getInterfaceService();
        Call<User> call = RetrofitClient.getInstance().getApi().postLogin(userName, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                userId = user.getId();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (userId > 0) {
                            SharedPrefrencesManager.getInstance(getContext()).setUserId(userId);
                            SharedPrefrencesManager.getInstance(getContext()).setIsLoggedIn();
                            startActivity(new Intent(getActivity() , MainActivity.class));
                            dialog.dismiss();
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), "لطفا نام کاربری یا رمز خود را بررسی کنید", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                }, 1500);
    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
        login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String userName = userNameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (userName.isEmpty() || userName.length() < 3) {
            userNameText.setError("نام کاربری معتبر نمی باشد");
            valid = false;
        } else {
            userNameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 5) {
            passwordText.setError("حداقل 5 کارکتر وارد کنید");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
