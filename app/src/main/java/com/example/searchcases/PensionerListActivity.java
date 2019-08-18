package com.example.searchcases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class PensionerListActivity extends AppCompatActivity {
    String numberInBase = "Номер не получен.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensioner_list);


        numberInBase = getIntent().getStringExtra("numberInBase");
        TextView testTextView = findViewById(R.id.testTextView);
        testTextView.setText(("Переданый номер - " + numberInBase));

        ShowStringsInListView();
    }

    private void ShowStringsInListView() {
        List<PensionInfo> pensList = getPensionerList();
        List<String> listString = pensionerToStrings(pensList);
        ListView listView = getListView();
        ArrayAdapter<String> adapterDemo = new ArrayAdapter<>(this,
                R.layout.list_item, listString);
        listView.setAdapter(adapterDemo);
    }

    private List<PensionInfo> getPensionerList() {
        List<PensionInfo> allPensList = null;
        List<PensionInfo> filterPensList = new ArrayList<>(10);

        SearchManeger searchManeger = new SearchManeger();
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

        for (int i = 0; i < allPensList.size(); i++) {
            String currNuberReg = allPensList.get(i).getNumberInBase();
            boolean valueEquals = currNuberReg.equals(numberInBase);
            if (valueEquals) {
                filterPensList.add(allPensList.get(i));
            }

        }


        return filterPensList;
    }

    private List<String> pensionerToStrings(List<PensionInfo> pensList) {
        List<String> listString = new ArrayList<>();
        for (int i = 0; i < pensList.size(); i++) {
            String s = "";
            s += pensList.get(i).getLastName() + " ";
            s += pensList.get(i).getName() + " ";
            s += pensList.get(i).getFartherName() + " - ";
            s += pensList.get(i).getNumberRegistry();
            listString.add(s);
        }
        return listString;
    }

    private ListView getListView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        return listView;
    }


    public void back(View view) {
        switch (view.getId()) {
            case R.id.pass_button:
                Intent intent = new Intent(this, SearchScreenActivity.class);
                startActivity(intent);
        }
    }
}
