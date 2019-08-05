package com.example.searchcases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class SearchScreenActivity extends AppCompatActivity {
    SearchManeger searchManeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

    }

    public void goToPensionerList(View view) {
        switch (view.getId()) {
            case R.id.pass_button:
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
                List<PensionInfo> pensList = getPensionInfos(numberInBase);
                List<String> listString = pensionerToStrings(pensList);
                ShowStringsInListView(listString);
                if (listString.size() > 0)
                    testTextView.setText(("Номер " + numberInBase + " найден"));
                else
                    testTextView.setText(("Номер " + numberInBase + " не найден"));
                break;
        }

    }

    private List<PensionInfo> getPensionInfos(String numberInBase) {
        if (searchManeger == null) {
            searchManeger = new SearchManeger();
            searchManeger.setAllPensList(getPensionerList());
        }
        return searchManeger.getPensWithNumber(numberInBase);
    }

    private void ShowStringsInListView(List<String> listString) {

        ListView listView = getListView();
        ArrayAdapter<String> adapterDemo = new ArrayAdapter<>(this,
                R.layout.list_item, listString);
        listView.setAdapter(adapterDemo);
    }

    private List<PensionInfo> getPensionerList() {
        List<PensionInfo> allPensList = null;
        try {
            XmlPullParser parser2014 = getResources().getXml(R.xml.export_2014);
            XmlPullParser parser2015 = getResources().getXml(R.xml.export_2015);
            XmlPullParser parser2016 = getResources().getXml(R.xml.export_2016);
            XmlPullParser parser2017 = getResources().getXml(R.xml.export_2017);
            XmlPullParser parser2018 = getResources().getXml(R.xml.export_2018);
            XmlPullParser parser2019 = getResources().getXml(R.xml.export_2019);

            allPensList = searchManeger.parseRegistry(parser2014);
            allPensList.addAll(searchManeger.parseRegistry(parser2015));
            allPensList.addAll(searchManeger.parseRegistry(parser2016));
            allPensList.addAll(searchManeger.parseRegistry(parser2017));
            allPensList.addAll(searchManeger.parseRegistry(parser2018));
            allPensList.addAll(searchManeger.parseRegistry(parser2019));
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
        if (allPensList == null)
            return null;
        return allPensList;
    }


    private List<String> pensionerToStrings(List<PensionInfo> pensList) {
        List<String> listString = new ArrayList<>();
        for (int i = 0; i < pensList.size(); i++) {
            String s = "";
            s += pensList.get(i).getLastName() + " ";
            s += pensList.get(i).getName() + " ";
            s += pensList.get(i).getFartherName() + "\n";
            s += "Номер по описи - " + pensList.get(i).getNumberRegistry() + "\n";
            s += pensList.get(i).getRegistryName() + "\n";
            if (!pensList.get(i).getRemark().equals(""))
                s += "Примечание - " + pensList.get(i).getRemark();

            listString.add(s);
        }
        return listString;
    }

    private ListView getListView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        return listView;
    }

    public void backToMain(View view) {
        onBackPressed();
    }

    public void clearInputNumberInBase(View view) {
        EditText numberTextView = findViewById(R.id.input_number_in_base);
        numberTextView.setText("");
    }
}
