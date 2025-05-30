package com.example.nopaper.ui.document;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.nopaper.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DocumentBottomSheetFragment extends BottomSheetDialogFragment {
    private ActivityResultLauncher<String> documentPickerLauncher;

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_document_bottom_sheet_fragment, container, false);

        // Initialize the button
        Button btnUploadFileDevice = view.findViewById(R.id.uploadfiledevice);

        // Register the launcher for picking a document
        documentPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        // Handle the selected document URI here
                        // For example, upload the document or display its name
                    }
                }
        );

        // Set the click listener for the button
        btnUploadFileDevice.setOnClickListener(v -> {
            // Launch the document picker with the appropriate MIME types
            documentPickerLauncher.launch("*/*");
        });

        return view;
    }
}
