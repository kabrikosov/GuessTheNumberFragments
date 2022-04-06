package com.bignerdanch.android.guessthenumberfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.bignerdanch.android.guessthenumberfragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NumberGuess {

    private ActivityMainBinding binding;
    private FragmentManager fm;
    Fragment f1, f2;
    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fm = getSupportFragmentManager();

        f1 = fm.findFragmentById(binding.fragmentContainer.getId());
        if (f1 == null) {
            f1 = new RangeInput();
            fm.beginTransaction().add(binding.fragmentContainer.getId(), f1).commit();
        }

        binding.startButton.setOnClickListener(view -> {
            if (!state) {
                var a = ((RangeInput) f1).getStart();
                var b = ((RangeInput) f1).getEnd();
                if (b - a > 0) {
                    f2 = GuessFragment.newInstance(a, b);
                    fm.beginTransaction().replace(binding.fragmentContainer.getId(), f2).commit();
                    binding.startButton.setText("Заново");
                }
            } else {
                fm.beginTransaction().replace(binding.fragmentContainer.getId(), f1).commit();
                binding.startButton.setText(R.string.startButton);
            }
            state = !state;
        });
    }

    @Override
    public void onNumberGuess () {
        fm.beginTransaction().replace(binding.fragmentContainer.getId(), f1).commit();
        binding.startButton.setText(R.string.startButton);
    }
}