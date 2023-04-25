package com.example.na;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.LocalDate;

public class LoginMain extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPassword;
    private Button txtButton;
    @NonNull
    private final String Username = "nicat";
    ;
    @NonNull
    private final String Password = "nicat_1993";;
    private CheckBox checkBox;
    private TextView txtFooter;
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        txtName = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        txtButton = findViewById(R.id.loginButton);
        checkBox = findViewById(R.id.checkBox);
        txtFooter = findViewById(R.id.signupText);

        //set current year to footer
        LocalDate date = LocalDate.now();
        txtFooter.setText("©" + String.valueOf(date.getYear()));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        txtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtName.getText().toString();
                String password = txtPassword.getText().toString();
                doLogin(username, password);
            }
        });
    }


    private void doLogin(@NonNull String username, String password) {
        txtName = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        if (username.trim().isEmpty() && password.isEmpty()) {
            txtName.setError("İstifadəçi adını daxil edin");
            txtPassword.setError("Şifrəni daxil edin");
            txtName.setBackgroundResource(R.drawable.edit_text_border_color_red);
            txtPassword.setBackgroundResource(R.drawable.edit_text_border_color_red);
        } else if (!username.trim().isEmpty() && username.trim().length() < 3) {
            txtName.setError("İstifadəçi adını daxil edin(Min 3 simvol)");
            txtName.setBackgroundResource(R.drawable.edit_text_border_color_red);
        } else if (password.isEmpty()) {
            txtPassword.setError("Şifrəni daxil edin");
            txtPassword.setBackgroundResource(R.drawable.edit_text_border_color_red);
            txtName.setBackgroundResource(R.drawable.custom_edittext);
        } else if (!password.isEmpty() && (username.trim().isEmpty() || username.trim().length() < 3)) {
            txtName.setError("İstifadəçi adını daxil edin(Min 3 simvol)");
            txtName.setBackgroundResource(R.drawable.edit_text_border_color_red);
            txtPassword.setBackgroundResource(R.drawable.custom_edittext);
        } else {
            txtName.setBackgroundResource(R.drawable.custom_edittext);
            txtPassword.setBackgroundResource(R.drawable.custom_edittext);
            isValid = validateLogin(username, password);

            if (!isValid) {
                Toast.makeText(LoginMain.this, "İstifadəçi adı və ya şifrə yalnışdır.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginMain.this, "Uğurlu giriş", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginMain.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private boolean validateLogin(@NonNull String username, String password) {
        if (username.equals(Username) && password.equals(Password)) return true;
        return false;
    }
}
