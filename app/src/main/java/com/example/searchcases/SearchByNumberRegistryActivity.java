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
import android.widget.Toast;

import com.example.searchcases.MyUtils.KeyboardUtils;

import java.util.List;

public class SearchByNumberRegistryActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnKeyListener{

    SearchManager searchManager;

    Button btnSearchByNumberInRegistry,
            btnClearTextEdit,
            btnBack;
    EditText etNumberInRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_number_registry);

        initListener();
        KeyboardUtils.initFocusAndShowKeyBoard(etNumberInRegistry,this);
    }

    private void initListener() {
        // Добавляем слушателя к компонентам
        btnSearchByNumberInRegistry = findViewById(R.id.search_by_number_in_registry);
        btnSearchByNumberInRegistry.setOnClickListener(this);

        btnClearTextEdit = findViewById(R.id.clear_button);
        btnClearTextEdit.setOnClickListener(this);

        btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(this);

        etNumberInRegistry = findViewById(R.id.input_number_in_registry);
        etNumberInRegistry.setOnKeyListener(this);
    }

    private void searchComparePensioner() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_by_number_in_registry:
                searchComparePensioner();
                KeyboardUtils.hideKeyBoard(etNumberInRegistry, this);
                break;
            case R.id.clear_button:
                clearInputNumberInBase(view);
                KeyboardUtils.initFocusAndShowKeyBoard(etNumberInRegistry,this);
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
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            searchComparePensioner();
            KeyboardUtils.hideKeyBoard(etNumberInRegistry, this);
        }
        return false;
    }
}
