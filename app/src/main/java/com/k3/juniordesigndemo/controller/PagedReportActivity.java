package com.k3.juniordesigndemo.controller;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.controller.slides.EnvironmentalIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.HomeIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.MiscellaneousIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.OtherSlideFragment;
import com.k3.juniordesigndemo.controller.slides.StreetIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.TrashIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.VehicleIssuesSlideFragment;
import com.k3.juniordesigndemo.controller.slides.YardIssuesSlideFragment;

public class PagedReportActivity extends AppCompatActivity {

    // Activity components
    ViewPager viewPager;
    LinearLayout dotsLinearLayout;
    Button prevButton, nextButton;

    Fragment[] slides;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paged_report);

        // Get activity components
        viewPager = findViewById(R.id.viewPager);
        dotsLinearLayout = findViewById(R.id.dotsLinearLayout);
        prevButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        // Set slides for view pager
        slides = new Fragment[]{
                new StreetIssuesSlideFragment(),
                new HomeIssuesSlideFragment(),
                new YardIssuesSlideFragment(),
                new VehicleIssuesSlideFragment(),
                new TrashIssuesSlideFragment(),
                new EnvironmentalIssuesSlideFragment(),
                new MiscellaneousIssuesSlideFragment(),
                new OtherSlideFragment()
        };

        // Disable swiping
        viewPager.setOnTouchListener((v, event) -> true);

        // Set viewPager's adapter
        viewPager.setAdapter(new ScreenSlidePagerAdapter());

        // Set initial dots to first page
        updateNavigation(0);

        // Set prev and next buttons' click listeners
        prevButton.setOnClickListener(new PrevButtonOnClickListener());
        nextButton.setOnClickListener(new NextButtonOnClickListener());
    }

    /**
     * Updates the dots at the bottom of the screen by highlighting the correct dot
     *
     * @param currSlide The slide corresponding to the dot to be highlighted
     */
    private void updateNavigation(int currSlide) {
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

    private class PrevButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int prev = Math.max(viewPager.getCurrentItem() - 1, 0);
            viewPager.setCurrentItem(prev);
            updateNavigation(prev);
        }
    }

    private class NextButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int next = Math.min(viewPager.getCurrentItem() + 1, slides.length - 1);
            viewPager.setCurrentItem(next);
            updateNavigation(next);
        }
    }
}
