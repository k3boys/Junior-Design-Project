package com.k3.juniordesigndemo.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.slides.HomeIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.MiscellaneousIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.MyFragment;
import com.k3.juniordesigndemo.controller.slides.StreetIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.VehicleIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.YardIssuesSlideFragment;

public class PagedReportActivity extends AppCompatActivity {

    // Activity components
    ViewPager viewPager;
    LinearLayout dotsLinearLayout;
    Button prevButton, nextButton;

    SharedPreferences preferences;
    MyFragment[] slides;
    MyFragment currFrag;
    boolean flagInit = true;
    int currSlide = 0;
    int numPages = 1;
    int lastSlide;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paged_report);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Singleton.newReport(extras.getString("ADDRESS"), extras.getDouble("LAT"), extras.getDouble("LNG"));
        }

        // Get activity components
        viewPager = findViewById(R.id.viewPager);
        dotsLinearLayout = findViewById(R.id.dotsLinearLayout);
        prevButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        // Set slides for view pager
        slides = new MyFragment[]{
                new StreetIssuesSlideFragment(),
                new HomeIssuesSlideFragment(),
                new YardIssuesSlideFragment(),
                new VehicleIssuesSlideFragment(),
                new MiscellaneousIssuesSlideFragment()
        };

        // Load numPages from storage and set lastSlide
        preferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        numPages = preferences.getInt("NUM_PAGES", 1);
        lastSlide = slides.length - (numPages - 1) - 1;

        // Disable swiping
        //viewPager.setOnTouchListener((v, event) -> true);

        // Set viewPager's adapter and listener
        viewPager.setAdapter(new ScreenSlidePagerAdapter());
        viewPager.addOnPageChangeListener(new ScreenSlidePageChangeListener());

        // Set initial dots to first page
        updateNavigation();

        // Set prev and next buttons' click listeners
        prevButton.setOnClickListener(new PrevButtonOnClickListener());
        nextButton.setOnClickListener(new NextButtonOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_page) {
            numPages = Math.min(numPages + 1, slides.length);
        } else {
            numPages = Math.max(numPages - 1, 1);
        }
        lastSlide = slides.length - (numPages - 1) - 1;
        currSlide = Math.min(currSlide, lastSlide);
        viewPager.setAdapter(new ScreenSlidePagerAdapter());
        viewPager.setCurrentItem(currSlide, false);
        updateNavigation();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("NUM_PAGES", numPages);
        editor.apply();
    }

    /**
     * Updates the dots at the bottom of the screen by highlighting the correct dot
     */
    private void updateNavigation() {
        if (currFrag != null && !flagInit) {
            currFrag.saveBoxes();
        }

        dotsLinearLayout.removeAllViews();

        for (int i = 0; i <= lastSlide; i++) {
            TextView dot = new TextView(this);
            dot.setText(Html.fromHtml("&#8226;"));
            dot.setTextSize(35);
            dot.setTextColor(i == currSlide ? Color.LTGRAY : Color.DKGRAY);
            dotsLinearLayout.addView(dot);
        }

        if (currSlide == 0) prevButton.setVisibility(View.INVISIBLE);
        else prevButton.setVisibility(View.VISIBLE);

        if (currSlide == lastSlide) nextButton.setText("Submit");
        else nextButton.setText("Next");

        currFrag = slides[currSlide];

        if (!flagInit) {
            for (int i = 0; i < numPages; i++) {
                slides[Math.min(currSlide + numPages, slides.length - 1)].getBoxes();
            }
            //currFrag.getBoxes();
        }

        flagInit = false;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return slides[position];
        }

        @Override
        public int getCount() {
            return slides.length;
        }

        @Override
        public float getPageWidth(int position) {
            return 1f / numPages;
        }
    }

    private class ScreenSlidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            position = Math.min(position, lastSlide);
            position = Math.max(position, 0);
            currSlide = position;
            updateNavigation();
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    }

    private class PrevButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < numPages; i++) {
                slides[Math.min(currSlide + numPages, slides.length - 1)].saveBoxes();
            }
            currSlide = Math.max(currSlide - 1, 0);
            viewPager.setCurrentItem(currSlide);
            updateNavigation();
        }
    }

    private class NextButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (currSlide  == lastSlide) {
                for (int i = 0; i < numPages; i++) {
                    slides[Math.min(currSlide + numPages, slides.length - 1)].saveBoxes();
                }
                //currFrag.saveBoxes();
                Singleton.submitReport();

                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("submitted-report", true); // Used to show toast when returning to MapActivity
                startActivity(intent);
            } else {
                for (int i = 0; i < numPages; i++) {
                    slides[Math.min(currSlide + numPages, slides.length - 1)].saveBoxes();
                }
                currSlide = Math.min(currSlide + 1, lastSlide);

                viewPager.setCurrentItem(currSlide);
                updateNavigation();
            }
        }
    }
}