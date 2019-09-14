package com.example.searchcases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGotoSearchByBaseNumber,
            btnGotoSearchByLastname,
            btnGotoAbout,
            btnGotoSearchByNumberInRegistry;

    String[] listItemDemo = {"Java", "Kotlin", "C++"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListener();
    }

    private void initListener() {
        btnGotoSearchByBaseNumber = findViewById(R.id.goto_search_by_base_number_button);
        btnGotoSearchByBaseNumber.setOnClickListener(this);

        btnGotoSearchByLastname = findViewById(R.id.goto_search_by_lastname_button);
        btnGotoSearchByLastname.setOnClickListener(this);

        btnGotoSearchByNumberInRegistry = findViewById(R.id.goto_search_by_number_in_registry);
        btnGotoSearchByNumberInRegistry.setOnClickListener(this);

        btnGotoAbout = findViewById(R.id.about);
        btnGotoAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.goto_search_by_base_number_button:
                intent = new Intent(this, SearchByNumberInBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_search_by_lastname_button:
                intent = new Intent(this, SearchByLastnameActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_search_by_number_in_registry:
                intent = new Intent(this, SearchByNumberRegistryActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
