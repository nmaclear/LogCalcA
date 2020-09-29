package me.nolan.logcalca;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final double wetDensityOak = 63;
        final double wetDensityCherryBlack = 45;
        final double wetDensityAshWhite = 48;
        final double wetDensitySpruceSitka = 33;

        final double dryDensitySpruceSitka = 28;
        final double dryDensityOak = 43;
        final double dryDensityCherryBlack = 35;
        final double dryDensityAshWhite = 41;

        

        final Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Oak", "Spruce", "Ash", "Cherry"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button calc = findViewById(R.id.calculate);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText smallend = findViewById(R.id.smalldiatext);
                EditText largeend = findViewById(R.id.largediatext);
                EditText lengthtext = findViewById(R.id.lengthtext);
                EditText moisturetext = findViewById(R.id.moisture);
                double smallEnd = 1;
                double largeEnd = 1;
                double length = 0;
                double moisture = 1;

                try {
                    smallEnd = Double.parseDouble(smallend.getText().toString());
                    largeEnd = Double.parseDouble(largeend.getText().toString());
                    length = Double.parseDouble(lengthtext.getText().toString());
                    moisture = Double.parseDouble(moisturetext.getText().toString());
                } catch(Exception e) {

                }

                double firsthalfdia = Math.PI*Math.pow(smallEnd/2, 2);
                double secondhalfdia = Math.PI*Math.pow(largeEnd/2, 2);

                double totalVolume = ((firsthalfdia+secondhalfdia)/2)*length;
                double totalWeight = 0;
                String type = dropdown.getSelectedItem().toString();
                if(type.equalsIgnoreCase("Oak"))
                    totalWeight = totalVolume*densityOak;
                else if(type.equalsIgnoreCase("Spruce"))
                    totalWeight = totalVolume*densitySpruceSitka;
                else if(type.equalsIgnoreCase("Ash"))
                    totalWeight = totalVolume*densityAshWhite;
                else if(type.equalsIgnoreCase("Cherry"))
                    totalWeight = totalVolume*densityCherryBlack;

                TextView output = findViewById(R.id.Output);
                output.setText("Volume: " + totalVolume + " ft^3 Total Weight: " + totalWeight);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}