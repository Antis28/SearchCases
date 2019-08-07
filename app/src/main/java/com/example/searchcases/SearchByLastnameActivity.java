package com.example.searchcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SearchByLastnameActivity extends AppCompatActivity {
    SearchManeger searchManeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_lastname);
    }

    public void buttonsHandler(View view) {
        switch (view.getId()) {
            case R.id.search_by_lastname_button:
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
        EditText lastnameTextView = findViewById(R.id.input_lastname);
        String lastname = lastnameTextView.getText().toString();

        TextView testTextView = findViewById(R.id.testTextView);

        if (lastname.equals("")) {
            testTextView.setText("Заполните поле выше.");
            return;
        }

        testTextView.setText(("Обработка Фамилии... " + lastname));
        List<PensionInfo> pensList = getPensionsByLastname(lastname);
        List<String> listString = searchManeger.pensionerToStrings(pensList);
        ShowStringsInListView(listString);
        if (listString.size() > 0)
            testTextView.setText(("Найдено совпадений - " + listString.size()));
        else
            testTextView.setText(("Фамилия " + lastname + " не найдена"));

    }

    private List<PensionInfo> getPensionsByLastname(String lastname) {
        if (searchManeger == null) {
            searchManeger = new SearchManeger(this);
        }
        return searchManeger.getPensByLastName(lastname);
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
        EditText numberTextView = findViewById(R.id.input_lastname);
        numberTextView.setText("");
    }
}
