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

public class OtherSlideFragment extends MyFragment {

    CheckBox basketball_goals;
    CheckBox eqpt_storage;
    CheckBox vacant_lot;
    CheckBox bus_stop;
    CheckBox benchmark;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_other_issues_slide, container, false);
    }


    public void onViewCreated (View view,
                               Bundle savedInstanceState) {

        basketball_goals = view.findViewById(R.id.basketballGoalCheckbox);
        eqpt_storage = view.findViewById(R.id.equipmentStorageCheckbox);
        vacant_lot = view.findViewById(R.id.vacantLotCheckbox);
        bus_stop = view.findViewById(R.id.busStopCheckbox);
        benchmark = view.findViewById(R.id.benchmarkCheckbox);
    }

    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        basketball_goals.setChecked(currRep.isOther_basketball_goals());
        eqpt_storage.setChecked(currRep.isOther_eqpt_storage());
        vacant_lot.setChecked(currRep.isOther_vacant_lot());
        bus_stop.setChecked(currRep.isOther_bus_stop());
        benchmark.setChecked(currRep.isOther_benchmark());

    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setOther_basketball_goals(basketball_goals.isChecked());
        currRep.setOther_eqpt_storage(eqpt_storage.isChecked());
        currRep.setOther_vacant_lot(vacant_lot.isChecked());
        currRep.setOther_bus_stop(bus_stop.isChecked());
        currRep.setOther_benchmark(benchmark.isChecked());
    }
}
