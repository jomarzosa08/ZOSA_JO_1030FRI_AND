package com.eldroid.zosa_navbar;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {
    private EditText firstNum, secNum;
    private TextView results;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Initialize UI components
        firstNum = view.findViewById(R.id.firstNum);
        secNum = view.findViewById(R.id.secNum);
        results = view.findViewById(R.id.results);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnSubtract = view.findViewById(R.id.btnSubtract);
        btnMultiply = view.findViewById(R.id.btnMultiply);
        btnDivide = view.findViewById(R.id.btnDivide);

        // Set button click listeners
        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSubtract.setOnClickListener(v -> calculate("-"));
        btnMultiply.setOnClickListener(v -> calculate("*"));
        btnDivide.setOnClickListener(v -> calculate("/"));

        return view;
    }

    private void calculate(String operation) {
        String num1 = firstNum.getText().toString();
        String num2 = secNum.getText().toString();

        if (!num1.isEmpty() && !num2.isEmpty()) {
            double firstNumber = Double.parseDouble(num1);
            double secondNumber = Double.parseDouble(num2);
            double result = 0;

            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        results.setText("Error: Division by Zero");
                        return;
                    }
                    break;
            }

            results.setText(decimalFormat.format(result));
        } else {
            results.setText("ERROR! Please enter both numbers");
        }
    }
}
