package com.bignerdanch.android.guessthenumberfragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdanch.android.guessthenumberfragments.databinding.FragmentGuessBinding;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuessFragment extends Fragment {

    private FragmentGuessBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "start";
    private static final String ARG_PARAM2 = "end";

    // TODO: Rename and change types of parameters
    private int start;
    private int end;

    public GuessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuessFragment newInstance(int param1, int param2) {
        GuessFragment fragment = new GuessFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            start = getArguments().getInt(ARG_PARAM1);
            end = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGuessBinding.inflate(inflater, container, false);
        TextView counter = binding.counter;
        AtomicInteger ct = new AtomicInteger();
        AtomicInteger a = new AtomicInteger(start);
        AtomicInteger b = new AtomicInteger(end + 1);
        AtomicInteger ch = new AtomicInteger((a.get() + b.get()) / 2);
        binding.chosenValue.setText(Integer.toString(ch.get()));

        binding.biggerButton.setOnClickListener(view -> {
            a.set(ch.get());
            ch.set((a.get() + b.get()) / 2);
            if (b.get() - a.get() > 1) {
                binding.chosenValue.setText(Integer.toString(ch.get()));
                ct.getAndIncrement();
                counter.setText(Integer.toString(ct.get()));
            }
        });

        binding.smallerButton.setOnClickListener(view -> {
            b.set(ch.get());
            ch.set((a.get() + b.get()) / 2);
            if (b.get() - a.get() > 1) {
                binding.chosenValue.setText(Integer.toString(ch.get()));
                ct.getAndIncrement();
                counter.setText(Integer.toString(ct.get()));
            }
        });

        binding.equalsButton.setOnClickListener(view ->
                ((MainActivity) requireActivity()).onNumberGuess()
        );

        return binding.getRoot();
    }
}