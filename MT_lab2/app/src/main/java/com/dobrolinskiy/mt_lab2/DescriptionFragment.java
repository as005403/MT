package com.dobrolinskiy.mt_lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class DescriptionFragment extends Fragment {

    private TextView textView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_description, container, false);
        textView = view.findViewById(R.id.textView);
        DetailsActivity activity = (DetailsActivity) getActivity();
        String desc = activity.getIntent().getExtras().getString("desc");
        textView.setText(desc);

        return view;
    }


}