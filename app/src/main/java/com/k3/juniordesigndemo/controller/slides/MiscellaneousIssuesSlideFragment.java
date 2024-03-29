package com.k3.juniordesigndemo.controller.slides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.model.ReportSingleton;
import com.k3.juniordesigndemo.model.Report;

public class MiscellaneousIssuesSlideFragment extends MyFragment {

    CheckBox need_beautification;
    CheckBox gentrification;
    CheckBox noise;
    CheckBox basketball_goals;
    CheckBox equipment_storage;
    CheckBox vacant_lot;
    CheckBox bus_stop;
    CheckBox benchmark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_miscellaneous_issues_slide, container, false);
    }

    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        need_beautification = view.findViewById(R.id.needBeautificationCheckbox);
        gentrification = view.findViewById(R.id.gentrificationCheckbox);
        noise = view.findViewById(R.id.noiseCheckbox);
        basketball_goals = view.findViewById(R.id.basketballGoalsCheckbox);
        equipment_storage = view.findViewById(R.id.equipmentStorageCheckbox);
        vacant_lot = view.findViewById(R.id.vacantLotCheckbox);
        bus_stop = view.findViewById(R.id.busStopCheckbox);
        benchmark = view.findViewById(R.id.benchmarkCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = ReportSingleton.getReport();
        need_beautification.setChecked(currRep.isMisc_need_beautification());
        gentrification.setChecked(currRep.isMisc_gentrification());
        noise.setChecked(currRep.isMisc_noise());
        basketball_goals.setChecked(currRep.isMisc_basketball_goals());
        equipment_storage.setChecked(currRep.isMisc_equipment_storage());
        vacant_lot.setChecked(currRep.isMisc_vacant_lot());
        bus_stop.setChecked(currRep.isMisc_bus_stop());
        benchmark.setChecked(currRep.isMisc_benchmark());
    }

    @Override
    public void saveBoxes() {
        Report currRep = ReportSingleton.getReport();
        currRep.setMisc_need_beautification(need_beautification.isChecked());
        currRep.setMisc_gentrification(gentrification.isChecked());
        currRep.setMisc_noise(noise.isChecked());
        currRep.setMisc_basketball_goals(basketball_goals.isChecked());
        currRep.setMisc_equipment_storage(equipment_storage.isChecked());
        currRep.setMisc_bus_stop(bus_stop.isChecked());
        currRep.setMisc_benchmark(benchmark.isChecked());
    }
}