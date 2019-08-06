package com.example.searchcases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String[] listItemDemo = {"Java", "Kotlin", "C++"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buttonsHandler(View view) {
        Intent intent;
		switch(view.getId()){
			case R.id.goto_search_by_base_number_button:
                intent= new Intent(this, SearchByNumberInBaseActivity.class);
				startActivity(intent);
				break;
            case R.id.goto_search_by_lastname_button:
                intent = new Intent(this, SearchByLastnameActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_search_by_number_in_registry:
                intent = new Intent(this, SearchByNumberInBaseActivity.class);
                startActivity(intent);
                break;
		}

    }

}
