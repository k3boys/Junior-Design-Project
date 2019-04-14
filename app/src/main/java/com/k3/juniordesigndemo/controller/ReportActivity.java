package com.k3.juniordesigndemo.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.k3.juniordesigndemo.R;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {

  // Activity components
  private Spinner categorySpinner;
  private EditText addressEditText;
  private Button finishButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);

    // Get activity components
    categorySpinner = findViewById(R.id.categorySpinner);
    addressEditText = findViewById(R.id.addressEditTextReportActivity);
    finishButton = findViewById(R.id.finishButton);

    // Populate spinner
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.categories_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    categorySpinner.setAdapter(adapter);


    // Set address from intent
    String address = getIntent().getStringExtra("ADDRESS");
    addressEditText.setText(address);

    // Set finish button callback
    finishButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Toast.makeText(this, "Report Submitted!", Toast.LENGTH_SHORT).show();
    finish();
  }
}
