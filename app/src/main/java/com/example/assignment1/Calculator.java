package com.example.assignment1;

import static android.util.Half.NaN;
import static java.lang.Character.isDigit;

public class Calculator {

    boolean isCorrect = true;
    String errorMessage;
    String numbersEntered;

    public boolean getIsCorrect() {
        return isCorrect;
    }
    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getNumbersEntered() {
        return numbersEntered;
    }

    public void setNumbersEntered(String numbersEntered) {
        this.numbersEntered = numbersEntered;
    }
    void push (String value){
        if(numbersEntered != null)
            numbersEntered = numbersEntered.concat(value);
        else
            numbersEntered = value;
    }
    int calculate(){
        int result=NaN;
        if(validate(numbersEntered)) {
            setIsCorrect(true);
            int firstOperand, secondOperand;
            char operator;
            firstOperand = Integer.parseInt(String.valueOf(numbersEntered.charAt(0)));
            for(int i=0;i<numbersEntered.length()-2;i+=2){
                secondOperand = Integer.parseInt(String.valueOf(numbersEntered.charAt(i+2)));
                operator = numbersEntered.charAt(i+1);
                switch (operator) {
                    case '+': result = firstOperand+secondOperand; break;
                    case '-': result = firstOperand-secondOperand; break;
                    case '*': result = firstOperand*secondOperand; break;
                    case '/': {
                        if(secondOperand == 0) {
                            setErrorMessage("Cannot divide by 0");
                            setIsCorrect(false);
                            break;
                        }
                        result = firstOperand/secondOperand; break;
                    }
                }
                firstOperand = result;
            }
        }
        else
            setIsCorrect(false);
        return result;
    }
    boolean validate(String problemString) {
        if(problemString.length() < 3)
            return false;
        if(!(isDigit(problemString.charAt(problemString.length()-1)))) {
            setErrorMessage("Need an operand after the operator.");
            return false;
        }
        boolean flag = true;
        for(int i=0;i<problemString.length();i++) {
            if(i%2 == 0) {
                //even index - input character should be a number
                //if(!(problemString.charAt(i)>=0 && problemString.charAt(i)<=9)) {
                if(!isDigit(problemString.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            else {
                //odd index - input character should be an operator
                //if(problemString.charAt(i)>=0 && problemString.charAt(i)<=9) {
                if(isDigit(problemString.charAt(i))){
                    flag = false;
                    break;
                }
            }
        }
        if(!flag) {
            setErrorMessage("Cannot accept multiple digit numbers");
        }
        return flag;
    }
}