package com.example.na.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.na.LoginMain;
import com.example.na.MainActivity;
import com.example.na.R;
import com.example.na.databinding.FragmentHomeBinding;
import com.example.na.databinding.FragmentLoginBinding;
import com.example.na.ui.home.HomeFragment;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.time.Year;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText txtName;
    private EditText txtPassword;
    private TextView txtFooter;
    private CheckBox checkBox;
    private final String Username = "nicat";
    private final String Password = "nicat_1993";
    boolean isValid = false;
    FragmentLoginBinding loginBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = loginBinding.getRoot();

        Button btnLogin = loginBinding.loginButton;
        checkBox = loginBinding.checkBox;
        txtName = loginBinding.username;
        txtPassword = loginBinding.password;
        txtFooter = loginBinding.signupText;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtName.getText().toString();
                String password = txtPassword.getText().toString();
                if(username.trim().isEmpty() || password.isEmpty()){
                    Toast.makeText(getActivity(), "Username ve şifrəni daxil edin", Toast.LENGTH_SHORT).show();
                }else {
                    isValid = validateLogin(username, password);

                    if(!isValid){
                        Toast.makeText(getActivity(), "Username və ya şifrə yalnışdır.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Uğurlu giriş edildi fragment", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });



        return root;
    }

    private boolean validateLogin(String username, String password){
        if(username.equals(Username) && password.equals(Password)) return true;

        return false;
    }
}