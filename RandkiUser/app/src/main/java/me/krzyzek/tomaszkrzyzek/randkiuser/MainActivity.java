package me.krzyzek.tomaszkrzyzek.randkiuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static boolean selectAll = true;
    static ArrayList<Osoba> detailsArray = new ArrayList<Osoba>();
    static ArrayList<String> peopleArray = new ArrayList<String>();
    static ArrayAdapter<String> adapter;
    static int minWiek;
    static int maxWiek;
    static String miasto;
    static String orientacja;
    static String plec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        update();
        adapter.notifyDataSetChanged();
    }

    public void searchButtonClick(View v){
        Intent i = new Intent(this, PickerActivity.class);
        startActivity(i);
        selectAll = false;
        update();
        adapter.notifyDataSetChanged();
    }

    public void viewAllClick(View v){
        selectAll = true;
        update();
        adapter.notifyDataSetChanged();
    }

    void update(){
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        getFromDatabase.execute();

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1 , peopleArray);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void refreshClick(View v){
        update();
    }
}
