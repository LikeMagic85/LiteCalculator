package com.example.litecalculator.ui;


import com.example.litecalculator.model.Calculator;
import com.example.litecalculator.model.CalculatorImpl;
import com.example.litecalculator.model.Operator;


import java.text.DecimalFormat;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculator calculator;

    private double argOne = 0;

    private double argTwo = 0;

    private boolean isDouble = false;

    private static int pow = 0;

    private Operator selectedOperator = null;

    private DecimalFormat formatter = new DecimalFormat("#.#######");

    public CalculatorPresenter(CalculatorView view, CalculatorImpl calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (selectedOperator == null) {

            if (isDouble == false) {
                argOne = argOne * 10 + digit;
            } else {
                pow--;
                argOne = argOne * Math.pow(10, -pow) + digit;
                argOne = argOne * Math.pow(10, pow);
            }

            showFormatted(argOne);

        } else {
            if (isDouble == false) {
                argTwo = argTwo * 10 + digit;
            } else {
                pow--;
                argTwo = argTwo * Math.pow(10, -pow) + digit;
                argTwo = argTwo * Math.pow(10, pow);
            }

            showFormatted(argTwo);
        }

    }

    public void onOperatorsPressed(Operator operator) {

        if (selectedOperator != null) {
            isDouble = false;

            argOne = calculator.perform(argOne, argTwo, selectedOperator);


            showFormatted(argOne);
        }

        argTwo = 0.0;

        selectedOperator = operator;

        isDouble = false;

        pow = 0;

    }

    public void onDotPressed() {
        isDouble = true;
    }

    public void onClearPressed() {

        argOne = 0;

        argTwo = 0;

        selectedOperator = null;

        isDouble = false;

        pow = 0;

        showFormatted(argOne);

    }

    public void onResultPressed() {

        if (argTwo == 0 | selectedOperator == null) {
            argOne = argOne;
        } else {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);

            showFormatted(argOne);

            argTwo = 0;

            selectedOperator = null;

        }

        isDouble = false;

        pow = 0;
    }

    public void onDelPressed() {

        if (argTwo == 0) {
            argOne = (int) argOne / 10;
            showFormatted(argOne);
        } else {
            argTwo = (int) argTwo / 10;
            showFormatted(argTwo);
        }

    }

    public void onSqrtPressed() {

        argOne = Math.sqrt(argOne);

        showFormatted(argOne);

        argTwo = 0;

        selectedOperator = null;

        isDouble = false;
    }

    public double onPercentPressed() {
        isDouble = false;
        if (selectedOperator == Operator.DIV | selectedOperator == Operator.MULTI) {

            argTwo = argTwo / 100;

            return argTwo;
        } else {

            argTwo = argOne / 100 * argTwo;

        }
        return 0;
    }

    private void showFormatted(double value) {

        view.showResult(formatter.format(value));

    }

    public double getArgOne() {
        return argOne;
    }

    public void setArgOne(double aDouble) {
        argOne = aDouble;
        showFormatted(argOne);
    }
}
