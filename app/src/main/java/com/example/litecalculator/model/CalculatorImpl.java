package com.example.litecalculator.model;

public class CalculatorImpl implements Calculator{

    @Override
    public double perform(double firstArg, double secondArg, Operator operator) {
        switch (operator) {
            case ADD:
                return firstArg + secondArg;
            case SUB:
                return firstArg - secondArg;
            case MULTI:
                return firstArg * secondArg;
            case DIV:
                return firstArg / secondArg;
        }
        return 0.0;
    }
}
