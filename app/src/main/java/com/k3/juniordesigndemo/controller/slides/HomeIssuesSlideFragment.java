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

public class HomeIssuesSlideFragment extends MyFragment {

    CheckBox home_abandoned;
    CheckBox home_bars;
    CheckBox home_broken_windows;
    CheckBox home_condition;
    CheckBox home_disrepair;
    CheckBox home_renovated;
    CheckBox home_selling;
    CheckBox home_solar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home_issues_slide, container, false);
    }


    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        home_abandoned = view.findViewById(R.id.abandonedCheckbox);
        home_bars = view.findViewById(R.id.barsCheckbox);
        home_broken_windows = view.findViewById(R.id.brokenWindowCheckbox);
        home_disrepair = view.findViewById(R.id.disrepairCheckbox);
        home_condition = view.findViewById(R.id.houseConditionCheckbox);
        home_renovated = view.findViewById(R.id.renovationCheckbox);
        home_selling = view.findViewById(R.id.forSaleCheckbox);
        home_solar = view.findViewById(R.id.solarCheckbox);




    }



    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        home_abandoned.setChecked(currRep.isHome_abandoned());
        home_bars.setChecked(currRep.isHome_bars());
        home_broken_windows.setChecked(currRep.isHome_broken_windows() == 1);
        home_disrepair.setChecked(currRep.isHome_disrepair());
        home_condition.setChecked(currRep.getHome_condition() == 1);
        home_renovated.setChecked(currRep.isHome_renovated());
        home_selling.setChecked(currRep.isHome_selling());
        home_solar.setChecked(currRep.isHome_solar());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setHome_abandoned(home_abandoned.isChecked());
        currRep.setHome_bars(home_bars.isChecked());
        currRep.setHome_broken_windows(home_broken_windows.isChecked()? 1:0);
        currRep.setHome_disrepair(home_disrepair.isChecked());
        currRep.setHome_condition(home_condition.isChecked()? 1:0);
        currRep.setHome_renovated(home_renovated.isChecked());
        currRep.setHome_selling(home_selling.isChecked());
        currRep.setHome_solar(home_solar.isChecked());
    }
}
