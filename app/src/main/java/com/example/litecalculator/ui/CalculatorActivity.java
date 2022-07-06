package com.example.litecalculator.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.litecalculator.R;
import com.example.litecalculator.model.CalculatorImpl;
import com.example.litecalculator.model.Operator;
import com.example.litecalculator.model.Theme;
import com.example.litecalculator.model.ThemeRepository;
import com.example.litecalculator.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    private final String PRESENTER = "presenter";

    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.screen_text);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digits = new HashMap<>();


        digits.put(R.id.button_num_0, 0);
        digits.put(R.id.button_num_1, 1);
        digits.put(R.id.button_num_2, 2);
        digits.put(R.id.button_num_3, 3);
        digits.put(R.id.button_num_4, 4);
        digits.put(R.id.button_num_5, 5);
        digits.put(R.id.button_num_6, 6);
        digits.put(R.id.button_num_7, 7);
        digits.put(R.id.button_num_8, 8);
        digits.put(R.id.button_num_9, 9);

        View.OnClickListener digitClickListener = (view -> presenter.onDigitPressed(digits.get(view.getId())));



        findViewById(R.id.button_num_0).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_1).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_2).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_3).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_4).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_5).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_6).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_7).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_8).setOnClickListener(digitClickListener);
        findViewById(R.id.button_num_9).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.button_plus, Operator.ADD);
        operators.put(R.id.button_minus, Operator.SUB);
        operators.put(R.id.button_multi, Operator.MULTI);
        operators.put(R.id.button_division, Operator.DIV);

        View.OnClickListener operatorsClickListener = view -> presenter.onOperatorsPressed(operators.get(view.getId()));

        findViewById(R.id.button_plus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_minus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_multi).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_division).setOnClickListener(operatorsClickListener);
        findViewById(R.id.button_percent).setOnClickListener((operatorsClickListener));

        findViewById(R.id.button_dot).setOnClickListener(view -> presenter.onDotPressed());

        findViewById(R.id.button_sqrt).setOnClickListener(view -> presenter.onSqrtPressed());

        findViewById(R.id.button_percent).setOnClickListener(view -> presenter.onPercentPressed());

        findViewById(R.id.button_c).setOnClickListener(view -> presenter.onClearPressed());

        findViewById(R.id.button_result).setOnClickListener(view -> presenter.onResultPressed());

        findViewById(R.id.button_del).setOnClickListener(view -> presenter.onDelPressed());

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                    themeRepository.saveTheme(selectedTheme);

                    recreate();
                }
            }
        });

        findViewById(R.id.menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());
                themeLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.setArgOne(savedInstanceState.getDouble(PRESENTER));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putDouble(PRESENTER, presenter.getArgOne());
        super.onSaveInstanceState(outState);
    }




    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}