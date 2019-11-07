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

public class HomeIssuesSlideFragment extends MyFragment {
    Spinner condition;
    CheckBox disrepair;
    CheckBox bars;
    EditText broken_windows;
    CheckBox abandoned;
    CheckBox for_sale;
    CheckBox renovated;
    CheckBox solar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_issues_slide, container, false);
    }

    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        condition = view.findViewById(R.id.conditionSpinner);
        disrepair = view.findViewById(R.id.homeDisrepairCheckbox);
        bars = view.findViewById(R.id.barsCheckbox);
        broken_windows = view.findViewById(R.id.brokenWindowsEditText);
        abandoned = view.findViewById(R.id.abandonedCheckbox);
        renovated = view.findViewById(R.id.renovatedCheckbox);
        for_sale = view.findViewById(R.id.forSaleCheckbox);
        solar = view.findViewById(R.id.solarCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        condition.setSelection(currRep.getHome_condition());
        disrepair.setChecked(currRep.isHome_disrepair());
        abandoned.setChecked(currRep.isHome_abandoned());
        bars.setChecked(currRep.isHome_bars());
        broken_windows.setText(currRep.isHome_broken_windows().toString());
        renovated.setChecked(currRep.isHome_renovated());
        for_sale.setChecked(currRep.isHome_for_sale());
        solar.setChecked(currRep.isHome_solar());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setHome_condition(Integer.parseInt(condition.getSelectedItem().toString()));
        currRep.setHome_disrepair(disrepair.isChecked());
        currRep.setHome_abandoned(abandoned.isChecked());
        currRep.setHome_bars(bars.isChecked());
        currRep.setHome_broken_windows(Integer.parseInt(broken_windows.getText().toString())); /// editText is probably not a good idea; check parsing
        currRep.setHome_renovated(renovated.isChecked());
        currRep.setHome_for_sale(for_sale.isChecked());
        currRep.setHome_solar(solar.isChecked());
    }
}