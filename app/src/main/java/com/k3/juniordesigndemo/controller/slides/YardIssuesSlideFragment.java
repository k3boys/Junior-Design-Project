package com.k3.juniordesigndemo.controller.slides;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.Singleton;
import com.k3.juniordesigndemo.model.Report;

public class YardIssuesSlideFragment extends MyFragment {

    Spinner landscaping;
    CheckBox abandoned_appliance;
    CheckBox abandoned_equipment;
    CheckBox trash;
    CheckBox debris;
    CheckBox standing_water;
    CheckBox unused_green;
    CheckBox tree_loss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yard_issues_slide, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        landscaping = view.findViewById(R.id.landscapingSpinner);
        abandoned_appliance = view.findViewById(R.id.abandonedApplianceCheckbox);
        abandoned_equipment = view.findViewById(R.id.abandonedEquipmentCheckbox);
        trash = view.findViewById(R.id.trashCheckbox);
        debris = view.findViewById(R.id.debrisCheckbox);
        standing_water = view.findViewById(R.id.standingWaterCheckbox);
        unused_green = view.findViewById(R.id.unusedGreenCheckbox);
        tree_loss = view.findViewById(R.id.treeLossCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        landscaping.setSelection(currRep.isYard_landscaping());
        abandoned_appliance.setChecked(currRep.isYard_abandoned_appliance());
        abandoned_equipment.setChecked(currRep.isYard_abandoned_equipment());
        trash.setChecked(currRep.isYard_trash());
        debris.setChecked(currRep.isYard_debris());
        standing_water.setChecked(currRep.isYard_standing_water());
        unused_green.setChecked(currRep.isYard_unused_green());
        tree_loss.setChecked(currRep.isYard_tree_loss());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setYard_landscaping(Integer.parseInt(landscaping.getSelectedItem().toString()));
        currRep.setYard_abandoned_appliance(abandoned_appliance.isChecked());
        currRep.setYard_abandoned_equipment(abandoned_equipment.isChecked());
        currRep.setYard_trash(trash.isChecked());
        currRep.setYard_debris(debris.isChecked());
        currRep.setYard_standing_water(standing_water.isChecked());
        currRep.setYard_unused_green(unused_green.isChecked());
        currRep.setYard_tree_loss(tree_loss.isChecked());
    }
}
