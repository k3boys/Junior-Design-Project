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

public class MiscellaneousIssuesSlideFragment extends MyFragment {

    CheckBox misc_abandoned_eqpt;
    CheckBox misc_gentrification;
    CheckBox misc_need_beautification;
    CheckBox misc_noise;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_miscellaneous_issues_slide, container, false);
    }


    public void onViewCreated (View view,
                               Bundle savedInstanceState) {


        misc_abandoned_eqpt = view.findViewById(R.id.abandonedEquipmentCheckbox);
        misc_gentrification = view.findViewById(R.id.gentrificationCheckbox);
        misc_need_beautification = view.findViewById(R.id.beautificationCheckbox);
        misc_noise = view.findViewById(R.id.noiseCheckbox);

    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        misc_abandoned_eqpt.setChecked(currRep.isMisc_abandoned_eqpt());
        misc_gentrification.setChecked(currRep.isMisc_gentrification());
        misc_need_beautification.setChecked(currRep.isMisc_need_beautification());
        misc_noise.setChecked(currRep.isMisc_noise());

    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setMisc_abandoned_eqpt(misc_abandoned_eqpt.isChecked());
        currRep.setMisc_gentrification(misc_gentrification.isChecked());
        currRep.setMisc_need_beautification(misc_need_beautification.isChecked());
        currRep.setMisc_noise(misc_noise.isChecked());
    }
}
