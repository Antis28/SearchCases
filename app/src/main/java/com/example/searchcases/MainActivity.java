package com.example.searchcases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] listItemDemo = {"Java", "Kotlin", "C++"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void goToFormTwo(View view) {
		switch(view.getId()){
			case R.id.pass_button:
				Intent intent = new Intent(this,SearchScreenActivity.class);
				startActivity(intent);
		}
		
    }
}
