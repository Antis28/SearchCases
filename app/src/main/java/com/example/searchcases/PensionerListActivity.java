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

    SearchManeger searchManeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensioner_list);

        String numberInBase = "Номер не получен.";
        numberInBase = getIntent().getStringExtra("numberInBase");
        TextView testTextView = findViewById(R.id.testTextView);
        testTextView.setText(("Переданый номер - " + numberInBase));


        List<PensionInfo> pensList = getPensionInfos(numberInBase);
        List<String> listString = pensionerToStrings(pensList);
        ShowStringsInListView(listString);
    }

    private List<PensionInfo> getPensionInfos(String numberInBase) {
        if (searchManeger == null){
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


    public void back(View view) {
        switch (view.getId()) {
            case R.id.pass_button:
                Intent intent = new Intent(this, SearchScreenActivity.class);
                startActivity(intent);
        }
    }
}
