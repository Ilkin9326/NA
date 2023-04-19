package com.example.na.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.na.LoginMain;
import com.example.na.R;
import com.example.na.databinding.ActivityMainBinding;
import com.example.na.databinding.FragmentHomeBinding;
import com.example.na.databinding.FragmentLoginBinding;
import com.example.na.ui.login.LoginFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FragmentLoginBinding loginBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnHesabla = binding.btnHesabla;

        btnHesabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = binding.startDate.getText().toString();
                String endDate = binding.endDate.getText().toString();
                Toast.makeText(getActivity(), "Username ve şifrəni daxil edin"+startDate+" - "+endDate, Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}