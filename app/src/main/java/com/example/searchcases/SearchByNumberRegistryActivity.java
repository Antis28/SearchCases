package com.example.searchcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SearchByNumberRegistryActivity extends AppCompatActivity {
    SearchManager searchManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_number_registry);
    }

    public void buttonsHandler(View view) {
        switch (view.getId()) {
            case R.id.search_by_number_in_registry:
                searchComparePensioner(view);
                break;
            case R.id.clear_button:
                clearInputNumberInBase(view);
                break;
            case R.id.back_button:
                onBackPressed();
                break;
        }
    }

    private void searchComparePensioner(View view) {
        EditText numberInRegistryTextView = findViewById(R.id.input_number_in_registry);
        String numberRegistry = numberInRegistryTextView.getText().toString();

        TextView testTextView = findViewById(R.id.testTextView);

        if (numberRegistry.equals("")) {
            testTextView.setText("Заполните поле выше.");
            return;
        }

        testTextView.setText(("Обработка Фамилии... " + numberRegistry));
        List<PensionInfo> pensList = getPensionsByNumberRegistry(numberRegistry);
        List<String> listString = searchManager.pensionerToStrings(pensList);
        ShowStringsInListView(listString);
        if (listString.size() > 0)
            testTextView.setText(("Найдено совпадений - " + listString.size()));
        else
            testTextView.setText(("Номер " + numberRegistry + " не найден"));

    }

    private List<PensionInfo> getPensionsByNumberRegistry(String numberRegistry) {
        if (searchManager == null) {
            searchManager = new SearchManager(this);
        }
        return searchManager.getPensNumberRegistry(numberRegistry);
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

    private void clearInputNumberInBase(View view) {
        EditText numberTextView = findViewById(R.id.input_number_in_registry);
        numberTextView.setText("");
    }
}
