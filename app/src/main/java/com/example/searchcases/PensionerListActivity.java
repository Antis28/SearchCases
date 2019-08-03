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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensioner_list);


        String  numberInBase = "Номер не получен.";
        numberInBase = getIntent().getStringExtra("numberInBase");
        TextView testTextView = findViewById(R.id.testTextView);
        testTextView.setText(("Переданый номер - " + numberInBase));

        //ShowStringsInListView();
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
        List<PensionInfo> pensList = null;
        SearchManeger searchManeger = new SearchManeger();
        try {
            XmlPullParser parser = getResources().getXml(R.xml.export_2019);
            pensList = searchManeger.parseRegistry(parser);
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
        return pensList;
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
        switch(view.getId()){
            case R.id.pass_button:
                Intent intent = new Intent(this,SearchScreenActivity.class);
                startActivity(intent);
        }
    }
}
