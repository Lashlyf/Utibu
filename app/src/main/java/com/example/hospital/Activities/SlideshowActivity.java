package com.example.hospital.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospital.R;

public class SlideshowActivity extends AppCompatActivity {

    private ImageView posterNormalImg;
    private TextView articleInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        posterNormalImg = findViewById(R.id.posterNormalImg);
        articleInfoTxt = findViewById(R.id.articleInfo);

        // Get the image resource and article information from the Intent extras
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        String articleInfo = getIntent().getStringExtra("articleInfo");

        // Set the image and article information
        posterNormalImg.setImageResource(imageResource);
        articleInfoTxt.setText(articleInfo);
    }

    // ... (Other code)
}