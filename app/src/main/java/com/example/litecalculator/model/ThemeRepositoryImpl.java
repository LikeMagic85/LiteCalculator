package com.example.litecalculator.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository{

    private static ThemeRepository INSTANCE;

    private SharedPreferences preferences;

    private static final String KEY_THEME = "KEY_THEME";

    private ThemeRepositoryImpl(Context context){
        preferences = context.getSharedPreferences("theme.xls", Context.MODE_PRIVATE);
    }

    public static ThemeRepository getInstance(Context context) {

        if(INSTANCE == null){
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public Theme getSavedTheme() {
        String savedKey = preferences.getString(KEY_THEME, Theme.LIGHT.getKey());

        for (Theme theme: Theme.values()){
            if (theme.getKey().equals(savedKey)){
                return theme;
            }
        }

        return Theme.LIGHT;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();
    }

    @Override
    public List<Theme> getAll() {
        return Arrays.asList(Theme.values());
    }
}
