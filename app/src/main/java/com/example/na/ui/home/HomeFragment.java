package com.example.na.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.na.R;
import com.example.na.databinding.FragmentHomeBinding;
import com.example.na.databinding.FragmentLoginBinding;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FragmentLoginBinding loginBinding;
    private TextView txtResRemDay, txtResDurationDay, txtResPercentage, txtResCurrentDate;
    private EditText startDate;
    private EditText endDate;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnHesabla = binding.btnHesabla;
        txtResRemDay = binding.txtresRemainDay;
        txtResDurationDay = binding.txtResDurationDay;
        txtResPercentage = binding.txtResPercentage;
        txtResCurrentDate=binding.txtResCurrentDate;

        LocalDate ll = LocalDate.now();
        txtResCurrentDate.setText(ll.toString("dd.MM.yyyy"));

        btnHesabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                String inputDate = binding.txtStartDate.getText().toString();
                String inputDate1 = binding.txtEndDate.getText().toString();

                if(inputDate.isEmpty() && inputDate1.isEmpty()){
                    binding.txtStartDate.setError("Məlumatları tam doldurun");
                    binding.txtEndDate.setError("Məlumatları tam doldurun");
                    binding.txtStartDate.setBackgroundResource(R.drawable.edit_text_border_color_red);
                    binding.txtEndDate.setBackgroundResource(R.drawable.edit_text_border_color_red);
                } else if (inputDate.isEmpty() || inputDate == "" || inputDate.length() != 10) {
                    binding.txtStartDate.setError("Məlumatları tam doldurun");
                    binding.txtStartDate.setBackgroundResource(R.drawable.edit_text_border_color_red);
                    binding.txtEndDate.setBackgroundResource(R.drawable.custom_edittext);
                } else if (inputDate1.isEmpty() || inputDate1 == ""  || inputDate.length() != 10) {
                    binding.txtEndDate.setError("Məlumatları tam doldurun");
                    binding.txtEndDate.setBackgroundResource(R.drawable.edit_text_border_color_red);
                    binding.txtStartDate.setBackgroundResource(R.drawable.custom_edittext);
                } else {
                    binding.txtStartDate.setBackgroundResource(R.drawable.custom_edittext);
                    binding.txtEndDate.setBackgroundResource(R.drawable.custom_edittext);
                    try {
                        LocalDate ll = LocalDate.now();
                        LocalDate ll1 = LocalDate.parse(inputDate, formatter);
                        LocalDate ll3 = LocalDate.parse(inputDate1, formatter);

                        //Istifade muddeti
                        double dayDuration = Days.daysBetween(ll1, ll3).getDays();
                        txtResDurationDay.setText(String.valueOf(dayDuration));

                        //Qalan gun
                        double remDay = Days.daysBetween(ll, ll3).getDays();
                        txtResRemDay.setText(String.valueOf(remDay));

                        //Faiz
                        double faiz = remDay*100/dayDuration;
                        DecimalFormat df = new DecimalFormat("#.#####");
                        df.format(faiz);
                        txtResPercentage.setText(df.format(faiz));

                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

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