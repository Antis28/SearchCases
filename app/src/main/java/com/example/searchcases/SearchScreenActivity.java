package com.example.searchcases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

    }

    public void goToPensionerList(View view) {
        switch(view.getId()){
            case R.id.pass_button:
                String numberInBase = "123456";
                EditText numberTextView = findViewById(R.id.input_number_in_base);
                numberInBase = numberTextView.getText().toString();

                Intent intent = new Intent(this,PensionerListActivity.class);
                intent.putExtra("numberInBase",numberInBase);
                startActivity(intent);
        }
    }
}
