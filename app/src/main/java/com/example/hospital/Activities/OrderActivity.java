package com.example.hospital.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.hospital.Adapter.SliderAdapter;
import com.example.hospital.Domain.SliderItems;
import com.example.hospital.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private final Handler slideHandler = new Handler();
    private Runnable slideRunnable;
    private List<SliderItems> sliderItems;
    private final long AUTO_SLIDE_DELAY = 3000;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        setupSlideshow();
    }

    private void initView() {
        viewPager2 = findViewById(R.id.recycler);
        if (viewPager2 == null) {
            return;
        }
    }

    private void setupSlideshow() {
        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.image1));
        sliderItems.add(new SliderItems(R.drawable.intro_image));
        sliderItems.add(new SliderItems(R.drawable.male_reproductive));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderItems, viewPager2);
        sliderAdapter.setOnItemClickListener(this::handleItemClick);
        viewPager2.setAdapter(sliderAdapter);

        configureViewPager();
        setupInfiniteLoop();
        //startAutoSlide();
    }

    private void configureViewPager() {
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(0);
    }

    private void setupInfiniteLoop() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, AUTO_SLIDE_DELAY);
            }
        });
    }
    private void startAutoSlide() {
        slideRunnable = new Runnable() {
            @Override
            public void run() {
                int nextPosition = (currentPosition + 1) % sliderItems.size();
                viewPager2.setCurrentItem(nextPosition, true);
            }
        };
    }



    private void handleItemClick(int position) {
        Log.d("SlideshowActivity", "Item clicked at position: " + position);

        // Handle the click event here
        // You can start a new activity or navigate to a fragment based on the clicked position
        switch (position) {
            case 0:
                // Start a new activity or perform an action for the first image
                break;
            case 1:
                // Start a new activity or perform an action for the second image
                break;
            case 2:
                // Start a new activity or perform an action for the third image
                break;
            // Add more cases for other positions if needed
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoSlide();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        slideHandler.removeCallbacks(slideRunnable);
    }
}











