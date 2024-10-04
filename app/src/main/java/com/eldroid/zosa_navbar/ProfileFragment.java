package com.eldroid.zosa_navbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText firstNameEditText;
    private EditText middleNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText contactNumberEditText;
    private RadioGroup genderRadioGroup;
    private RadioGroup statusRadioGroup;
    private CheckBox workingCheckBox;
    private CheckBox studyingCheckBox;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize EditText fields
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        middleNameEditText = view.findViewById(R.id.middleNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        contactNumberEditText = view.findViewById(R.id.contactNumberEditText);

        // Initialize RadioGroups
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        statusRadioGroup = view.findViewById(R.id.statusRadioGroup);

        // Initialize CheckBoxes
        workingCheckBox = view.findViewById(R.id.workingCheckBox);
        studyingCheckBox = view.findViewById(R.id.studyingCheckBox);

        // Initialize Save Button
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void saveProfile() {
        String firstName = firstNameEditText.getText().toString();
        String middleName = middleNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String contactNumber = contactNumberEditText.getText().toString();

        // Get selected gender
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = getView().findViewById(selectedGenderId);
        String gender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "Not Specified";

        // Get selected status
        int selectedStatusId = statusRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedStatusButton = getView().findViewById(selectedStatusId);
        String status = selectedStatusButton != null ? selectedStatusButton.getText().toString() : "Not Specified";

        // Get working or studying status
        boolean isWorking = workingCheckBox.isChecked();
        boolean isStudying = studyingCheckBox.isChecked();

        // Display the saved profile information
        String profileInfo = "Profile Saved:\n" +
                "Name: " + firstName + " " + middleName + " " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Contact Number: " + contactNumber + "\n" +
                "Gender: " + gender + "\n" +
                "Status: " + status + "\n" +
                "Working: " + isWorking + "\n" +
                "Studying: " + isStudying;

        Toast.makeText(getActivity(), profileInfo, Toast.LENGTH_LONG).show();
    }
}

