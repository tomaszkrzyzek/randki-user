package me.krzyzek.tomaszkrzyzek.randkiuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PickerActivity extends AppCompatActivity {

    EditText plec;
    EditText orientacja;
    EditText miasto;
    EditText minWiek;
    EditText maxWiek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        minWiek = (EditText) findViewById(R.id.minWiek);
        maxWiek = (EditText) findViewById(R.id.maxWiek);
        plec = (EditText) findViewById(R.id.plec);
        orientacja = (EditText) findViewById(R.id.orientacja);
        miasto = (EditText) findViewById(R.id.miasto);
    }

    public void finish(View view) {
        MainActivity.minWiek = Integer.parseInt(minWiek.getText().toString());
        MainActivity.maxWiek = Integer.parseInt(maxWiek.getText().toString());
        MainActivity.miasto = miasto.getText().toString();
        MainActivity.plec = plec.getText().toString();
        MainActivity.orientacja = orientacja.getText().toString();

        MainActivity.selectAll = false;
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
