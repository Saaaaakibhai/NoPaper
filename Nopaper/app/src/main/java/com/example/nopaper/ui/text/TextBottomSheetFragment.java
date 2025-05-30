package com.example.nopaper.ui.text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.nopaper.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TextBottomSheetFragment extends BottomSheetDialogFragment {
    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_text_bottom_sheet_fragment, container, false);

        // Initialize the button
        Button createTextFileButton = view.findViewById(R.id.createtextfile);

        // Set click listener for the button
        createTextFileButton.setOnClickListener(v -> showCreateTextPopup());

        return view;
    }

    private void showCreateTextPopup() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Write Something");

        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_text, null);
        builder.setView(dialogView);

        // Get references to the dialog's EditText and buttons
        EditText editText = dialogView.findViewById(R.id.editTextInput);
        Button saveButton = dialogView.findViewById(R.id.saveButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        // Create the dialog
        AlertDialog dialog = builder.create();

        // Set click listeners for Save and Cancel buttons
        saveButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                // Handle the save action, e.g., save to file or database
                Toast.makeText(requireContext(), "Text saved: " + text, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(requireContext(), "Text cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }
}
