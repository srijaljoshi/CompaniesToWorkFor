package edu.uga.cs6060.topcompanies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "MainActivity";
    final String[] companies = {"Apple", "Microsoft", "Google", "Facebook", "Uber"};

    Spinner spinner;
    Button btnOverview;
    Button btnDetails;
    TextView tvOverview;

    String selectedSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        initializeSpinner();

        initializeButtons();

        loadResources();
    }

    private void initializeViews() {
        spinner = findViewById(R.id.spinner);
        btnOverview = findViewById(R.id.btnOverview);
        tvOverview = findViewById(R.id.tvAppOverview);
        btnDetails = findViewById(R.id.btnPayDetails);
    }

    private void initializeSpinner() {
        // The parameter here is an implementation of the interface AdapterView.OnItemSelectedListener()
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // Override this method to invoke behavior when an option is selected from the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void initializeButtons() {

        btnOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedSpinner = ((TextView) spinner.getSelectedView()).getText().toString();
                Log.i(LOG_TAG, "Selected from Spinner; " + selectedSpinner);
                openActivity(OverviewActivity.class, selectedSpinner);
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedSpinner = ((TextView) spinner.getSelectedView()).getText().toString();
                Log.i(LOG_TAG, "CLICKED ON DETAILS FOR " + selectedSpinner);
                openActivity(DetailsActivity.class, selectedSpinner);

            }
        });
    }

    private void loadResources() {
        try {
            InputStream is = getResources().openRawResource(R.raw.app_overview);
            byte[] b = new byte[is.available()];
            is.read(b);
            tvOverview.setText(new String(b));
            is.close();
        } catch (Exception e) {

        }
    }

    private void openActivity(Class<?> nextActivity, String message) {

        Intent intent = new Intent(getApplicationContext(), nextActivity);
        intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);
    }

}
