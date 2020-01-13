package com.example.shan.recyclerlistDB;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MyRecyclerViewAdapter.ItemClickListener {
    //https://shanniz.github.io/courses/index.html

    //https://github.com/shanniz/RecyclerListDB

    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private Manufacturer itemClicked;

    private final DBHelper mydb = DBHelper.getDBHelper(this); //DBHelper(this);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvManufacturer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listManufacturers();
    }

    @Override
    public void onItemClick(View view, int position) {
        this.itemClicked = adapter.getItem(position);
        Intent intent = new Intent(this, UpdateManufacturer.class);
        intent.putExtra("manufacturerName", this.itemClicked.getName());
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){
        //in case of back button press
        if (resCode==0){
            return;
        }
        //Toast.makeText(this, data.getStringExtra("operation"), Toast.LENGTH_LONG).show();
        if (data.getStringExtra("operation").compareTo( "delete")==0){
            mydb.deleteManufacturer(this.itemClicked.getId());
        }else if (data.getStringExtra("operation").compareTo( "update") == 0){
            this.itemClicked.setName(data.getStringExtra("name"));
            mydb.updateManufacturer(this.itemClicked);
        }
        listManufacturers();
    }

    public void addManufacturer(View view){
        EditText et = (EditText)findViewById(R.id.manufacturerName);
        mydb.insertManufacturer(et.getText().toString());
        listManufacturers();
        et.setText("");
    }
    public void listManufacturers(){
        List<Manufacturer> OEMs = mydb.getAllManufacturers();
        adapter = new MyRecyclerViewAdapter(this, OEMs);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    /*public void showAll(View view) {
        listManufacturers();
        List<Manufacturer> OEMs = mydb.getAllManufacturers();
        for (Manufacturer oem : OEMs) {
            String details = "Id: " + oem.getId() + " ,Name: " + oem.getName() + " ,Phone: " + oem.getPhone();
        }
    }*/
}
