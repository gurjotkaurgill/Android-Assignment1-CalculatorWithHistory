package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals;
    Button btnClear, btnHistory;
    TextView resultTextView, historyTextView;
    Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setClickListeners();
        if(!((ResultsHistory)getApplication()).isHistoryVisible) {
            historyTextView.setVisibility(View.INVISIBLE);
            btnHistory.setText(R.string.btnWithHistory);
        }
        else {
            historyTextView.setVisibility(View.VISIBLE);
            btnHistory.setText(R.string.btnWithoutHistory);
            updateHistoryTextView();
        }
    }
    void initializeViews(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnHistory = findViewById(R.id.btnHistory);
        resultTextView = findViewById(R.id.resultTextView);
        historyTextView = findViewById(R.id.historyTextView);
    }
    void setClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnClear){
            //clear textview
            resultTextView.setText("");
            calculator.setNumbersEntered("");
        }
        else if(v.getId() == R.id.btnEquals) {
            int result = calculator.calculate();
            if (!calculator.getIsCorrect()) {
                Toast.makeText(this,
                        "Invalid input: "+calculator.getNumbersEntered()+"\n"+calculator.getErrorMessage(),
                        Toast.LENGTH_SHORT).show();
                resultTextView.setText("");
                calculator.setNumbersEntered("");
            } else {
                String currentComputation = calculator.getNumbersEntered() + " = " + result;
                resultTextView.setText(currentComputation);
                if (((ResultsHistory)getApplication()).isHistoryVisible) {
                    ((ResultsHistory)getApplication()).results.add(currentComputation);
                    updateHistoryTextView();
                }
                //perform calculation
            }
        }

        else if(v.getId() == R.id.btnHistory){
            //history button pressed
            ((ResultsHistory)getApplication()).isHistoryVisible = !((ResultsHistory)getApplication()).isHistoryVisible;
            if(((ResultsHistory)getApplication()).isHistoryVisible){
                btnHistory.setText(R.string.btnWithoutHistory);
                historyTextView.setVisibility(View.VISIBLE);
                //updateHistoryTextView();
            }
            else {
                btnHistory.setText(R.string.btnWithHistory);
                historyTextView.setVisibility(View.INVISIBLE);
            }
        }
        else{
            //if(v.getId() != R.id.btnEquals && v.getId() != R.id.btnClear && v.getId() != R.id.btnHistory){
            //get button text
            String pressedBtnText = ((Button)v).getText().toString();
            //1. push input to string
            //calculator.push(pressedBtnText);
            //2. write on textview
            resultTextView.setText(resultTextView.getText() + pressedBtnText);
            calculator.push(pressedBtnText);
        }
    }
    void updateHistoryTextView() {
        historyTextView.setText("");
        for (String result : ((ResultsHistory)getApplication()).results) {
            if (result != null) {
                historyTextView.append("\n" + result);
            }
        }
    }
}