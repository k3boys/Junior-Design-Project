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

public class EnvironmentalIssuesSlideFragment extends MyFragment {

    CheckBox standing_water;
    CheckBox unused_green;
    CheckBox loss_of_trees;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_environmental_issues_slide, container, false);

    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        standing_water = view.findViewById(R.id.standingWaterCheckbox);
        unused_green = view.findViewById(R.id.unusedGreenCheckbox);
        loss_of_trees = view.findViewById(R.id.treeLossCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        standing_water.setChecked(currRep.isEnvironment_standing_water());
        unused_green.setChecked(currRep.isEnvironment_unused_green());
        loss_of_trees.setChecked(currRep.isEnvironment_tree_loss());

    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setEnvironment_standing_water(standing_water.isChecked());
        currRep.setEnvironment_unused_green(unused_green.isChecked());
        currRep.setEnvironment_tree_loss(loss_of_trees.isChecked());
    }
}
