package com.k3.juniordesigndemo.controller.slides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.model.ReportSingleton;
import com.k3.juniordesigndemo.model.Report;

public class StreetIssuesSlideFragment extends MyFragment {

    CheckBox disrepair;
    CheckBox no_sidewalks;
    CheckBox drain_blocked;
    CheckBox fire_hydrant_oos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_street_issues_slide, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        disrepair = view.findViewById(R.id.streetDisrepairCheckbox);
        no_sidewalks = view.findViewById(R.id.noSidewalksCheckbox);
        drain_blocked = view.findViewById(R.id.drainBlockedCheckbox);
        fire_hydrant_oos = view.findViewById(R.id.fireHydrantOOSCheckbox);

    }
    @Override
    public void getBoxes() {
        Report currRep = ReportSingleton.getReport();
        disrepair.setChecked(currRep.isStreet_disrepair());
        no_sidewalks.setChecked(currRep.isStreet_no_sidewalks());
        drain_blocked.setChecked(currRep.isStreet_drain_blocked());
        fire_hydrant_oos.setChecked(currRep.isStreet_hydrant_oos());

    }

    @Override
    public void saveBoxes() {
        Report currRep = ReportSingleton.getReport();
        currRep.setStreet_disrepair(disrepair.isChecked());
        currRep.setStreet_no_sidewalks(no_sidewalks.isChecked());
        currRep.setStreet_drain_blocked(drain_blocked.isChecked());
        currRep.setStreet_hydrant_oos(fire_hydrant_oos.isChecked());
    }
}
