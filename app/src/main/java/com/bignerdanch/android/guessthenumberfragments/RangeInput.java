package com.bignerdanch.android.guessthenumberfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdanch.android.guessthenumberfragments.databinding.FragmentRangeInputBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RangeInput#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RangeInput extends Fragment {

    private FragmentRangeInputBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "start";
    private static final String ARG_PARAM2 = "end";

    // TODO: Rename and change types of parameters
    private int start;
    private int end;


    public RangeInput() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param start Parameter 1.
     * @param end Parameter 2.
     * @return A new instance of fragment RangeInput.
     */
    // TODO: Rename and change types and number of parameters
    public static RangeInput newInstance(int start, int end) {
        RangeInput fragment = new RangeInput();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, start);
        args.putInt(ARG_PARAM2, end);
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
        binding = FragmentRangeInputBinding.inflate(inflater, container, false);

        binding.a.setValue(0);
        binding.a.setMinValue(0);
        binding.a.setMaxValue(1000);

        binding.b.setMinValue(0);
        binding.b.setMaxValue(1000);
        binding.b.setValue(100);

        return binding.getRoot();
    }

    public int getEnd() {
        end = binding.b.getValue();
        return end;
    }

    public int getStart(){
        start = binding.a.getValue();
        return start;
    }
}