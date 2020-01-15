package com.example.shan.recyclerlistDB;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateManufacturer extends AppCompatActivity {

    EditText mEditTextManufacturerName, mEditTextManufacturerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manufacturer);

        String name = getIntent().getStringExtra("manufacturerName");
        mEditTextManufacturerName = (EditText) findViewById(R.id.editTextManufacturerName);
        mEditTextManufacturerName.setText(name);

        String address = getIntent().getStringExtra("manufacturerAddress");
        mEditTextManufacturerAddress = (EditText) findViewById(R.id.editTextManufacturerAddress);
        mEditTextManufacturerAddress.setText(address);
    }

    public void onDeleteManufacturer(View view){
        Intent intent = new Intent();
        intent.putExtra("operation", "delete");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onUpdateManufacturer(View view){
        String name = mEditTextManufacturerName.getText().toString();
        String address = mEditTextManufacturerName.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("operation", "update");
        intent.putExtra("name", name );
        intent.putExtra("address", address);
        setResult(RESULT_OK, intent);
        finish();
    }

}
