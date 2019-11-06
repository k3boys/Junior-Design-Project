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

public class VehicleIssuesSlideFragment extends MyFragment {

    CheckBox abandoned_street;
    CheckBox abandoned_driveway;
    CheckBox abandoned_other;
    CheckBox parked;
    CheckBox oversized;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_vehicle_issues_slide, container, false);
    }


    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        abandoned_street = view.findViewById(R.id.streetCarCheckbox);
        abandoned_driveway = view.findViewById(R.id.drivewayCarCheckbox);
        abandoned_other = view.findViewById(R.id.carAbandonedCheckbox);
        parked = view.findViewById(R.id.yardCarCheckbox);
        oversized = view.findViewById(R.id.carOversizedCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        abandoned_street.setChecked(currRep.isVehicle_abandoned_street());
        abandoned_driveway.setChecked(currRep.isVehicle_abandoned_driveway());
        abandoned_other.setChecked(currRep.isVehicle_abandoned_other());
        parked.setChecked(currRep.getVehicle_parked() == 1);
        oversized.setChecked(currRep.isVehicle_oversized());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setVehicle_abandoned_street(abandoned_street.isChecked());
        currRep.setVehicle_abandoned_driveway(abandoned_driveway.isChecked());
        currRep.setVehicle_abandoned_other(abandoned_other.isChecked());
        currRep.setVehicle_parked(parked.isChecked()? 1:0);
        currRep.setVehicle_oversized(oversized.isChecked());
    }
}
