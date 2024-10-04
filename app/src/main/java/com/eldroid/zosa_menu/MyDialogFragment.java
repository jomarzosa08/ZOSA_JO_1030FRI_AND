package com.eldroid.zosa_menu;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment, null);

        // Set up the message
        TextView messageTextView = view.findViewById(R.id.dialog_message);
        messageTextView.setText("This is a message.");

        // Handle positive button click
        Button positiveButton = view.findViewById(R.id.dialog_positive_button);
        positiveButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).navigateToSecondFragment();
            dismiss();
        });

        // Handle negative button click (Cancel)
        Button negativeButton = view.findViewById(R.id.dialog_negative_button);
        negativeButton.setOnClickListener(v -> dismiss());

        builder.setView(view);

        return builder.create();
    }
}
