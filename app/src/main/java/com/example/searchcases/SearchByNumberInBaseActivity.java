package com.example.searchcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SearchByNumberInBaseActivity extends AppCompatActivity implements View.OnClickListener {
    SearchManager searchManager;
    Button btnSearchByNumberInBase,
            btnClearTextEdit,
            btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_number_in_base);

        btnSearchByNumberInBase = findViewById(R.id.search_by_number_in_base);
        btnSearchByNumberInBase.setOnClickListener(this);

        btnClearTextEdit = findViewById(R.id.clear_button);
        btnClearTextEdit.setOnClickListener(this);

        btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_by_number_in_base:
                searchComparePensioner();
                break;
            case R.id.clear_button:
                clearInputNumberInBase();
                break;
            case R.id.back_button:
                onBackPressed();
                break;
        }
    }

    private void searchComparePensioner() {
        EditText numberTextView = findViewById(R.id.input_number_in_base);
        String numberInBase = numberInBase = numberTextView.getText().toString();
        TextView testTextView = findViewById(R.id.testTextView);

        if (numberInBase.equals("")) {
            testTextView.setText("Заполните поле выше.");
            return;
        } else if (numberInBase.length() < 6) {
            testTextView.setText("Номер не шестизначный.");
            return;
        }

        testTextView.setText(("Обработка номера " + numberInBase));
        List<PensionInfo> pensList = getPensionsByNumberBase(numberInBase);
        List<String> listString = searchManager.pensionerToStrings(pensList);
        ShowStringsInListView(listString);
        if (listString.size() > 0)
            testTextView.setText(("Номер " + numberInBase + " найден"));
        else
            testTextView.setText(("Номер " + numberInBase + " не найден"));


    }

    private List<PensionInfo> getPensionsByNumberBase(String numberInBase) {
        if (searchManager == null) {
            searchManager = new SearchManager(this);
        }
        return searchManager.getPensWithNumber(numberInBase);
    }

    private void ShowStringsInListView(List<String> listString) {
        ListView listView = getListView();
        ArrayAdapter<String> adapterDemo = new ArrayAdapter<>(this,
                R.layout.list_item, listString);
        listView.setAdapter(adapterDemo);
    }

    private ListView getListView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        return listView;
    }

    private void clearInputNumberInBase() {
        EditText numberTextView = findViewById(R.id.input_number_in_base);
        numberTextView.setText("");
    }
}
