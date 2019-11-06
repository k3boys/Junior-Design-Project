package com.k3.juniordesigndemo.controller.slides;

import android.database.DataSetObserver;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.Singleton;
import com.k3.juniordesigndemo.model.Report;

public class HomeIssuesSlideFragment extends MyFragment {

    CheckBox home_abandoned;
    CheckBox home_bars;
    EditText home_broken_windows;
    Spinner home_condition;
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
        home_condition = view.findViewById(R.id.houseConditionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
            R.array.intensity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        home_condition.setAdapter(adapter);
        home_abandoned = view.findViewById(R.id.abandonedCheckbox);
        home_bars = view.findViewById(R.id.barsCheckbox);
        home_broken_windows = view.findViewById(R.id.brokenWindowEditText);
        home_disrepair = view.findViewById(R.id.disrepairCheckbox);
        home_renovated = view.findViewById(R.id.renovationCheckbox);
        home_selling = view.findViewById(R.id.forSaleCheckbox);
        home_solar = view.findViewById(R.id.solarCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        home_abandoned.setChecked(currRep.isHome_abandoned());
        home_bars.setChecked(currRep.isHome_bars());
        home_broken_windows.setText(Integer.toString(currRep.isHome_broken_windows()));
        home_disrepair.setChecked(currRep.isHome_disrepair());
        home_condition.setSelection(currRep.getHome_condition());
        home_renovated.setChecked(currRep.isHome_renovated());
        home_selling.setChecked(currRep.isHome_selling());
        home_solar.setChecked(currRep.isHome_solar());
    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setHome_abandoned(home_abandoned.isChecked());
        currRep.setHome_bars(home_bars.isChecked());
        currRep.setHome_broken_windows(Integer.parseInt(home_broken_windows.getText().toString()));
        currRep.setHome_disrepair(home_disrepair.isChecked());
        currRep.setHome_condition(home_condition.getSelectedItemPosition());
        currRep.setHome_renovated(home_renovated.isChecked());
        currRep.setHome_selling(home_selling.isChecked());
        currRep.setHome_solar(home_solar.isChecked());
    }
}
