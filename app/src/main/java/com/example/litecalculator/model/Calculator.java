package com.example.litecalculator.model;

import java.io.Serializable;

public interface Calculator {
    double perform(double firstArg, double secondArg, Operator operator);
}
