package com.k3.juniordesigndemo.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
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

    MyFragment[] slides;
    MyFragment currFrag;
    boolean flagInit = true;
    int currSlide = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paged_report);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Singleton.newReport(extras.getString("ADDRESS"));

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

    /**
     * Updates the dots at the bottom of the screen by highlighting the correct dot
     */
    private void updateNavigation() {
        if (currFrag != null && !flagInit) {
            currFrag.saveBoxes();
        }

        dotsLinearLayout.removeAllViews();

        for (int i = 0; i < slides.length; i++) {
            TextView dot = new TextView(this);
            dot.setText(Html.fromHtml("&#8226;"));
            dot.setTextSize(35);
            dot.setTextColor(i == currSlide ? Color.LTGRAY : Color.DKGRAY);
            dotsLinearLayout.addView(dot);
        }

        if (currSlide == 0) prevButton.setVisibility(View.INVISIBLE);
        else if (currSlide == slides.length - 1) nextButton.setText("Submit");
        else {
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setText("Next");
        }

        currFrag = slides[currSlide];

        if (!flagInit) {

            currFrag.getBoxes();
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
    }

    private class ScreenSlidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            currSlide = position;
//            updateNavigation();
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    }

    private class PrevButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int prev = Math.max(viewPager.getCurrentItem() - 1, 0);
            currSlide = prev;
            viewPager.setCurrentItem(prev);
            updateNavigation();
        }
    }

    private class NextButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (currSlide == slides.length - 1) {
                currFrag.saveBoxes();
                Singleton.submitReport();

                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("submitted-report", true); // Used to show toast when returning to MapActivity
                startActivity(intent);
            } else {
                int next = Math.min(viewPager.getCurrentItem() + 1, slides.length - 1);
                currSlide = next;
                viewPager.setCurrentItem(next);
                updateNavigation();
            }
        }
    }
}