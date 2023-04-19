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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.LocalDate;

public class LoginMain extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPassword;
    private Button txtButton;
    private final String Username = "nicat";
    private final String Password = "nicat_1993";
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
        txtFooter.setText("©"+String.valueOf(date.getYear()));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        txtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtName.getText().toString();
                String password = txtPassword.getText().toString();
                if(username.trim().isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginMain.this, "Username ve şifrəni daxil edin", Toast.LENGTH_SHORT).show();
                }else {
                    isValid = validateLogin(username, password);

                    if(!isValid){
                        Toast.makeText(LoginMain.this, "Username və ya şifrə yalnışdır.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginMain.this, "Uğurlu giriş edildiiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginMain.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });
    }

    private boolean validateLogin(String username, String password){
        if(username.equals(Username) && password.equals(Password)) return true;

        return false;
    }
}
