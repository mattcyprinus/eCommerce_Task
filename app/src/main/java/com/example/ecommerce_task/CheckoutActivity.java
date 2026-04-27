package com.example.ecommerce_task;

import static androidx.core.content.ContentProviderCompat.requireContext;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName    = findViewById(R.id.et_name);
        etEmail   = findViewById(R.id.et_email);
        etPhone   = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);

        ImageButton back = findViewById(R.id.btn_back);
        back.setOnClickListener(view -> finish());
        getUserDetail();
    }

    private void getUserDetail(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY5ZWM2MTJmZWUzZmJkNmY1ZjVkZTBjZCIsImlhdCI6MTc3NzA5OTA3NywiZXhwIjoxNzc3MTg1NDc3fQ.yCDB1dufcJKyghrQMaUjjfa0gjvD7sjWn44E8khje6E";

        RetrofitClient.getApi()
                .getProfile(token)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call,
                                           @NonNull Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            User user = response.body();

                            etName.setText(user.getFullName());
                            etEmail.setText(user.getEmail());
                            etPhone.setText(user.getPhone());
                            etAddress.setText(user.getAddress());
                        }else {
                            // Xem response trả về lỗi gì
                            try {
                                String errorBody = response.errorBody().string();
                                Toast.makeText(CheckoutActivity.this,
                                        "Code: " + response.code() + " - " + errorBody,
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(CheckoutActivity.this,
                                        "Code: " + response.code(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Toast.makeText(CheckoutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}