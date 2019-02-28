package edu.uga.cs6060.topcompanies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DetailsActivity extends AppCompatActivity {

    final static String LOG_TAG = "DetailsActivity";
    TextView tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String company = intent.getStringExtra("EXTRA_MESSAGE");

        tvDetails = findViewById(R.id.tvDetails);

        displayContentOf(company);
    }


    /**
     * A helper method which picks appropriate company and asks the loadDescription method with
     * appropriate params.
     * @param company
     */
    private void displayContentOf(String company) {
        switch (company.toLowerCase()) {
            case "apple":
                Log.i(LOG_TAG, "Load Apple's description!");
                loadDescription(R.raw.apple_details);
                break;

            case "microsoft":
                Log.i(LOG_TAG, "Load Microsoft's description!");
                loadDescription(R.raw.microsoft_details);
                break;

            case "uber":
                loadDescription(R.raw.uber_details);
                break;

            case "facebook":
                loadDescription(R.raw.facebook_details);
                break;

            case "google":
                loadDescription(R.raw.google_details);
                break;

            default:
                break;
        }
    }

    /**
     * A helper method to abstract content loading process for different companies
     * @param id
     */
    private void loadDescription(int id) {
        final InputStream inputStream = getResources().openRawResource(id);
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            tvDetails.setText(new String(b));
            inputStream.close();
        } catch (IOException e) {
            // handle exception
        }

    }
}
