package com.example.shan.recyclerlistDB;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateManufacturer extends AppCompatActivity {

    EditText etManufacturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manufacturer);

        String man = getIntent().getStringExtra("manufacturerName");
        etManufacturer = (EditText) findViewById(R.id.manufacturerEdit);
        etManufacturer.setText(man);
    }

    public void onDeleteManufacturer(View view){
        Intent intent = new Intent();
        intent.putExtra("operation", "delete");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onUpdateManufacturer(View view){
        String name = etManufacturer.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("operation", "update");
        intent.putExtra("name", name );
        setResult(RESULT_OK, intent);
        finish();
    }

}
