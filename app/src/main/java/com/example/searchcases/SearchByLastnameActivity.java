package com.example.searchcases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.searchcases.MyUtils.KeyboardUtils;

import java.util.List;

public class SearchByLastnameActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnKeyListener{
    SearchManager searchManager;
    EditText etLastname;

    Button btnSearchByLastname,
            btnClearTextEdit,
            btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_lastname);

        initListener();
        KeyboardUtils.initFocusAndShowKeyBoard(etLastname,this);
    }

    private void initListener() {
        // Добавляем слушателя к компонентам
        btnSearchByLastname = findViewById(R.id.search_by_lastname_button);
        btnSearchByLastname.setOnClickListener(this);

        btnClearTextEdit = findViewById(R.id.clear_button);
        btnClearTextEdit.setOnClickListener(this);

        btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(this);

        etLastname = findViewById(R.id.input_lastname);
        etLastname.setOnKeyListener(this);
    }

    private void searchComparePensioner() {
        EditText lastnameTextView = findViewById(R.id.input_lastname);
        String lastname = lastnameTextView.getText().toString();

        TextView testTextView = findViewById(R.id.testTextView);

        if (lastname.equals("")) {
            testTextView.setText("Заполните поле выше.");
            return;
        }

        testTextView.setText(("Обработка Фамилии... " + lastname));
        List<PensionInfo> pensList = getPensionsByLastname(lastname);
        List<String> listString = searchManager.pensionerToStrings(pensList);
        ShowStringsInListView(listString);
        if (listString.size() > 0)
            testTextView.setText(("Найдено совпадений - " + listString.size()));
        else
            testTextView.setText(("Фамилия " + lastname + " не найдена"));

    }

    private List<PensionInfo> getPensionsByLastname(String lastname) {
        if (searchManager == null) {
            searchManager = new SearchManager(this);
        }
        return searchManager.getPensByLastName(lastname);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_by_lastname_button:
                searchComparePensioner();
                KeyboardUtils.hideKeyBoard(etLastname,this);
                break;
            case R.id.clear_button:
                clearInputNumberInBase(view);
                KeyboardUtils.initFocusAndShowKeyBoard(etLastname,this);
                break;
            case R.id.back_button:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // Обработайте нажатие клавиши, верните true, если
        // обработка выполнена

        if (keyCode == KeyEvent.KEYCODE_ENTER){
            searchComparePensioner();
            KeyboardUtils.hideKeyBoard(etLastname,this);
        }
        return false;
    }
}
