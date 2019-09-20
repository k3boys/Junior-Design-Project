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

public class TrashIssuesSlideFragment extends MyFragment {

    CheckBox junk_pile;
    CheckBox debris;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_trash_issues_slide, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        junk_pile = view.findViewById(R.id.trashCheckbox);
        debris = view.findViewById(R.id.debrisCheckbox);


    }


    @Override
    public void getBoxes() {
        Report currRep = Singleton.getReport();
        junk_pile.setChecked(currRep.isEnvironment_trash());
        debris.setChecked(currRep.isEnvironment_debris());

    }

    @Override
    public void saveBoxes() {
        Report currRep = Singleton.getReport();
        currRep.setEnvironment_trash(junk_pile.isChecked());
        currRep.setEnvironment_debris(debris.isChecked());
    }
}
