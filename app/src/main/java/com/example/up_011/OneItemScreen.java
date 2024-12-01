package com.example.up_011;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OneItemScreen extends AppCompatActivity {

    private ImageView imageView, imageSelected;
    private TextView dishSelected, priceSelected;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_one_item_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView = findViewById(R.id.goback);
        imageSelected = findViewById(R.id.imageSelected);
        dishSelected = findViewById(R.id.dishSelected);
        priceSelected = findViewById(R.id.priceSelected);
        Bitmap bitmap = getIntent().getParcelableExtra("image_bitmap");
        imageSelected.setImageBitmap(bitmap);
        dishSelected.setText(getIntent().getStringExtra("dish"));
        priceSelected.setText(getIntent().getStringExtra("price"));
        imageView.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, MainScreen.class);
            startActivity(intent1);
        });
    }


}