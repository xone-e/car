package com.example.mycar.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycar.R;
import com.example.mycar.api.RetrofitClient;
import com.example.mycar.pojo.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();
    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText machineNameEditText;
    private EditText numberplateEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signupButton;

    private Button cancleButtuon;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.first_name_signup);
        lastNameEditText = findViewById(R.id.last_name_signup);
        machineNameEditText = findViewById(R.id.machine_name_signup);
        numberplateEditText = findViewById(R.id.numberplate_signup);
        usernameEditText = findViewById(R.id.username_signup);
        passwordEditText = findViewById(R.id.password_signup);
        signupButton = findViewById(R.id.save_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        cancleButtuon = findViewById(R.id.cancel_signup);
        cancleButtuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }


        final ProgressDialog dialog = new ProgressDialog(this, R.style.AppThemeMyLightProgressBar);
        dialog.setMessage("Creating...");
        dialog.setIndeterminate(true);
        dialog.show();


        String name = nameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String machineName = machineNameEditText.getText().toString().trim();
        String numberplate = numberplateEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        Call<Result> call = RetrofitClient.getInstance().getApi().postSignup(name, lastName, machineName, numberplate, username, password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result myResponse = response.body();
                result = myResponse.getResult();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "there is problem in server." +
                        "\n try again.", Toast.LENGTH_LONG).show();
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        switch (result) {
                            case "registered":
                                Toast.makeText(SignupActivity.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                dialog.dismiss();
                                finish();
                                break;
                            case "failed registered":
                                Toast.makeText(SignupActivity.this, "ثبت نام ناموفق" +
                                        "\n try another time!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;
                            case "exist":
                                Toast.makeText(SignupActivity.this, "کاربر دیگری با این نام کاربری ثبت نام کرده است لطفا دوباره امتحان کنید.", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                break;
                        }
                    }
                }, 1500);

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "signUp failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = nameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String machineName = machineNameEditText.getText().toString().trim();
        String numberplate = numberplateEditText.getText().toString().trim();
        String userName = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            nameEditText.setError("At least 3 characters");
            valid = false;
        } else {
            nameEditText.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 3) {
            lastNameEditText.setError("At least 3 characters");
            valid = false;
        } else {
            lastNameEditText.setError(null);
        }

        if (machineName.isEmpty() || machineName.length() < 3) {
            machineNameEditText.setError("At least 3 characters");
            valid = false;
        } else {
            machineNameEditText.setError(null);
        }

        if (numberplate.isEmpty() || numberplate.length() < 3) {
            numberplateEditText.setError("At least 3 characters");
            valid = false;
        } else {
            numberplateEditText.setError(null);
        }

        if (userName.isEmpty() || userName.length() < 3) {
            usernameEditText.setError("At least 3 characters");
            valid = false;
        } else {
            usernameEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 5) {
            passwordEditText.setError("More than 5 alphanumeric characters");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }
}
