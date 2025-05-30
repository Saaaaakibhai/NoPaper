package com.example.nopaper.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nopaper.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GalleryBottomSheetFragment extends BottomSheetDialogFragment {
    private static final int REQUEST_CODE = 22;
    Button btnpicture;
    Button btnUploadDevice;
    ActivityResultLauncher<String> pickImageLauncher;

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.activity_gallery_bottom_sheet_fragment, container, false);

        // Initialize the buttons
        btnpicture = view.findViewById(R.id.clicknewphoto);
        btnUploadDevice = view.findViewById(R.id.uploadphotodevice);

        // Register launcher for picking an image
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        // Handle the selected image URI here
                        // For example, you can display it in an ImageView or upload it to a server
                    }
                }
        );

        // Set click listener for "Click a new photo" button
        btnpicture.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CODE);
        });

        // Set click listener for "Upload from device" button
        btnUploadDevice.setOnClickListener(v -> {
            // Launch the content picker for images
            pickImageLauncher.launch("image/*");
        });

        return view;
    }
}
