package com.eldroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText firstNum, secNum;
    private TextView results;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = findViewById(R.id.firstNum);
        secNum = findViewById(R.id.secNum);
        results = findViewById(R.id.results);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);

        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSubtract.setOnClickListener(v -> calculate("-"));
        btnMultiply.setOnClickListener(v -> calculate("*"));
        btnDivide.setOnClickListener(v -> calculate("/"));
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
