package com.example.searchcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SearchByNumberInBaseActivity extends AppCompatActivity {
    SearchManeger searchManeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_number_in_base);

    }
    public void buttonsHandler(View view) {
        switch (view.getId()) {
            case R.id.search_by_number_in_base:
                searchComparePensioner(view);
                break;
            case R.id.clear_button:
                clearInputNumberInBase(view);
                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    private void searchComparePensioner(View view) {
        switch (view.getId()) {
            case R.id.search_by_number_in_base:
                /*
                String numberInBase = "123456";
                EditText numberTextView = findViewById(R.id.input_number_in_base);
                numberInBase = numberTextView.getText().toString();

                Intent intent = new Intent(this,PensionerListActivity.class);
                intent.putExtra("numberInBase",numberInBase);
                startActivity(intent);
                */

                EditText numberTextView = findViewById(R.id.input_number_in_base);
                String numberInBase = numberInBase = numberTextView.getText().toString();
                TextView testTextView = findViewById(R.id.testTextView);

                if (numberInBase.equals("")) {
                    testTextView.setText("Заполните поле выше.");
                    break;
                } else if (numberInBase.length() < 6) {
                    testTextView.setText("Номер не шестизначный.");
                    break;
                }

                testTextView.setText(("Обработка номера " + numberInBase));
                List<PensionInfo> pensList = getPensionsByNumberBase(numberInBase);
                List<String> listString = searchManeger.pensionerToStrings(pensList);
                ShowStringsInListView(listString);
                if (listString.size() > 0)
                    testTextView.setText(("Номер " + numberInBase + " найден"));
                else
                    testTextView.setText(("Номер " + numberInBase + " не найден"));
                break;
        }

    }

    private List<PensionInfo> getPensionsByNumberBase(String numberInBase) {
        if (searchManeger == null) {
            searchManeger = new SearchManeger(this);
        }
        return searchManeger.getPensWithNumber(numberInBase);
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
        EditText numberTextView = findViewById(R.id.input_number_in_base);
        numberTextView.setText("");
    }
}
