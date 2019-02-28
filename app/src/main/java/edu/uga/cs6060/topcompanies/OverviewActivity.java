package edu.uga.cs6060.topcompanies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class OverviewActivity extends AppCompatActivity {

    final static String LOG_TAG = "OverviewActivity";
    TextView tvMoreOverview;
    ImageView imgOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
        String company = intent.getStringExtra("EXTRA_MESSAGE");

        tvMoreOverview = findViewById(R.id.tvMoreOverview);
        imgOverview = findViewById(R.id.imgOverview); // To display apt image
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
                loadContent(R.raw.apple_overview);
                imgOverview.setImageResource(R.drawable.apple);
                break;

            case "microsoft":
                loadContent(R.raw.microsoft_overview);
                imgOverview.setImageResource(R.drawable.microsoft);
                break;

            case "uber":
                loadContent(R.raw.uber_overview);
                imgOverview.setImageResource(R.drawable.uber);
                break;

            case "facebook":
                loadContent(R.raw.facebook_overview);
                imgOverview.setImageResource(R.drawable.facebook);
                break;

            case "google":
                loadContent(R.raw.google_overview);
                imgOverview.setImageResource(R.drawable.google);
                break;

            default:
                break;
        }
    }

    /**
     * A helper method to abstract content loading process for different companies
     * @param id
     */
    private void loadContent(int id) {

        final InputStream inputStream = getResources().openRawResource(id);
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            tvMoreOverview.setText(new String(b));
            inputStream.close();

        } catch (IOException e) {
            // handle exception
        }
    }

}
