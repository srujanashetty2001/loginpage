package com.example.pluswork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView profileImageView;
    private ImageView addIcon;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use your XML layout here

        profileImageView = findViewById(R.id.profile_image);
        addIcon = findViewById(R.id.add_icon);
        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        Button saveButton = findViewById(R.id.save_button);

        // Set a click listener on the add icon to open gallery
        addIcon.setOnClickListener(v -> openGallery());

        // Save button logic (if needed)
        saveButton.setOnClickListener(v -> {
            String firstNameText = firstName.getText().toString().trim();
            if (firstNameText.isEmpty()) {
                Toast.makeText(MainActivity.this, "First Name is required", Toast.LENGTH_SHORT).show();
            } else {
                // Save the profile data (this part is up to your needs)
                Toast.makeText(MainActivity.this, "Profile saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to open gallery
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    // This method gets called after an image is selected
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);  // Set selected image to the ImageView
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
