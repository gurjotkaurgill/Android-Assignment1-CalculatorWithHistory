package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
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

        //getting the state of visibility of history
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
        historyTextView.setMovementMethod(new ScrollingMovementMethod());
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
            //clear textview and user string
            resultTextView.setText("");
            calculator.setNumbersEntered("");
        }
        else if(v.getId() == R.id.btnEquals) {
            //if nothing was entered: the app crashes, so display an error message
            if(calculator.getNumbersEntered().length() == 0)
                Toast.makeText(this, "No expression entered", Toast.LENGTH_SHORT).show();
            else {
                //perform calculation
                int result = calculator.calculate();

                //if the result is invalid, display error message and clear textView and expression string
                if (!calculator.getIsCorrect()) {
                    Toast.makeText(this,
                            "Invalid input: " + calculator.getNumbersEntered() + "\n" + calculator.getErrorMessage(),
                            Toast.LENGTH_SHORT).show();
                    resultTextView.setText("");
                    calculator.setNumbersEntered("");
                } else {
                    //else display the result
                    String currentComputation = calculator.getNumbersEntered() + " = " + result;
                    resultTextView.setText(currentComputation);
                    if (((ResultsHistory) getApplication()).isHistoryVisible) {
                        //if history is visible, update it
                        ((ResultsHistory) getApplication()).results.add(currentComputation);
                        updateHistoryTextView();
                    }
                }
            }
        }

        else if(v.getId() == R.id.btnHistory){
            //history button pressed: change button's text and switch its visibility factor
            ((ResultsHistory)getApplication()).isHistoryVisible = !((ResultsHistory)getApplication()).isHistoryVisible;
            if(((ResultsHistory)getApplication()).isHistoryVisible){
                btnHistory.setText(R.string.btnWithoutHistory);
                historyTextView.setVisibility(View.VISIBLE);
                updateHistoryTextView();
            }
            else {
                btnHistory.setText(R.string.btnWithHistory);
                historyTextView.setVisibility(View.INVISIBLE);
            }
        }
        else{
            //number buttons are pressed: get text of the number button
            String pressedBtnText = ((Button)v).getText().toString();

            //push input to expression string and write on textview
            //resultTextView.setText(resultTextView.getText() + pressedBtnText);
            resultTextView.setText(String.format("%s%s", resultTextView.getText(), pressedBtnText));
            calculator.push(pressedBtnText);
        }
    }
    void updateHistoryTextView() {
        historyTextView.setText("");
        for (String result : ((ResultsHistory)getApplication()).results) {
            //traverse through the linked list at app level and till it's not null: display its items
            if (result != null) {
                historyTextView.append("\n" + result);
            }
        }

        // find the amount we need to scroll.  This works by
        // asking the TextView's internal layout for the position
        // of the final line and then subtracting the TextView's height
        if(historyTextView.getText() != null) {
            final Layout layout = historyTextView.getLayout();
            if(layout != null) {
                int scrollAmount = layout.getLineTop(historyTextView.getLineCount()) - historyTextView.getHeight();
                // if there is no need to scroll, scrollAmount will be <=0
                if (scrollAmount > 0)
                    historyTextView.scrollTo(0, scrollAmount);
                else
                    historyTextView.scrollTo(0, 0);
            }
        }
    }
}