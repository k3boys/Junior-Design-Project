package com.k3.juniordesigndemo.controller.slides;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.Singleton;
import com.k3.juniordesigndemo.model.Report;

public class YardIssuesSlideFragment extends MyFragment {

    CheckBox landscaping;
    CheckBox abandoned_stuff;
    CheckBox backyard_junk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_yard_issues_slide, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        landscaping = view.findViewById(R.id.landscapingIssueCheckbox);
        abandoned_stuff = view.findViewById(R.id.frontTrashCheckbox);
        backyard_junk = view.findViewById(R.id.backTrashCheckbox);


    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        landscaping.setChecked(currRep.isYard_landscaping());
        abandoned_stuff.setChecked(currRep.isYard_abandoned_junk());
        backyard_junk.setChecked(currRep.isYard_back_junk());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setYard_landscaping(landscaping.isChecked());
        currRep.setYard_abandoned_junk(abandoned_stuff.isChecked());
        currRep.setYard_back_junk(backyard_junk.isChecked());
    }
}
