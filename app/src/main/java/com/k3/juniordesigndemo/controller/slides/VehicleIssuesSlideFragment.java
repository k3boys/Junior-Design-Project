package com.k3.juniordesigndemo.controller.slides;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.Singleton;
import com.k3.juniordesigndemo.model.Report;

public class VehicleIssuesSlideFragment extends MyFragment {

    CheckBox abandoned_street;
    CheckBox abandoned_driveway;
    CheckBox abandoned_other;
    EditText parked;
    CheckBox oversized;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_vehicle_issues_slide, container, false);
    }


    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        abandoned_street = view.findViewById(R.id.abandonedStreetCheckbox);
        abandoned_driveway = view.findViewById(R.id.abandonedDrivewayCheckbox);
        abandoned_other = view.findViewById(R.id.abandonedOtherCheckbox);
        parked = view.findViewById(R.id.parkedEditText);
        oversized = view.findViewById(R.id.oversizedCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        abandoned_street.setChecked(currRep.isVehicle_abandoned_street());
        abandoned_driveway.setChecked(currRep.isVehicle_abandoned_driveway());
        abandoned_other.setChecked(currRep.isVehicle_abandoned_other());
        parked.setText(currRep.getVehicle_parked().toString());
        oversized.setChecked(currRep.isVehicle_oversized());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setVehicle_abandoned_street(abandoned_street.isChecked());
        currRep.setVehicle_abandoned_driveway(abandoned_driveway.isChecked());
        currRep.setVehicle_abandoned_other(abandoned_other.isChecked());
        String str = parked.getText().toString();
        currRep.setVehicle_parked(str.isEmpty() ? 0 : Integer.parseInt(str));
        currRep.setVehicle_oversized(oversized.isChecked());
    }
}
