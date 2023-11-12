# Android-Assignment1-CalculatorWithHistory

Building a calculator app in
Android Studio Giraffe | 2022.3.1 Patch 2

**This is made as part of an assignment for Seneca College Android Mobile Development course.**

This mobile app uses Linear layout for portrait as well as landscape mode and has its custom icon. For now, this calculator handles calculations only for single digit numbers. 

This app handles all computation errors within the calculator like:
1. Division by zero
2. Equals button pressed without any query
3. Operator missing
4. One or both operands missing
5. Last input is not a digit
6. Multiple digit numbers entered

This calculator is not limited to only 2 operands and 1 operator. It can work with multiple operands and operators within a single expression. The result is calculated without any operator precedence. The calculation starts from left and goes to right, doing computations in the order of operators.

Another feature of this app is it has an option for the user to save history of calculations and this history is retained even when the phone is rotated and the activity state is recreated. The user can save as many computation histories as needed and there is a feature to scroll through those histories.
