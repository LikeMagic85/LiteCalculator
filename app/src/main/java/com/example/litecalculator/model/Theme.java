package com.example.litecalculator.model;

import androidx.annotation.StyleRes;

import com.example.litecalculator.R;

public enum Theme {

    LIGHT(R.style.Theme_LiteCalculator_Light, R.string.theme_1, "themeOne"),
    DARK(R.style.Theme_LiteCalculator_Dark, R.string.theme_2, "themeTwo");

    @StyleRes
    private  int themeRes;
    @StyleRes
    private int title;
    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
