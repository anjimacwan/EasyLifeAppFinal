package com.anjalimacwan.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anjalimacwan.R;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MapActivity extends AppCompatActivity implements LocationListener{

    private LocationManager locationManager;
    private static final String API_KEY = "AIzaSyAvtK2ckGC4Aeml2gP63hT_ctqluww5wHg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final EditText name = (EditText) findViewById(R.id.placename);
        final Geocoder coder = new Geocoder(getApplicationContext());
        final Button mapButton = (Button)findViewById(R.id.map);

        /*Button geocode = (Button) findViewById(R.id.geocode);
        geocode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {*/

                    mapButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String placeName = name.getText().toString();
                            try {
                                List<Address> geocodeResults = coder.getFromLocationName(placeName, 3);
                                Iterator<Address> locations = geocodeResults.iterator();

                                String locInfo = "Results:\n";
                                double lat = 0f;
                                double lon = 0f;
                                while (locations.hasNext()) {
                                    Address loc = locations.next();
                                    locInfo += String.format("Location: %f, %f\n", loc.getLatitude(), loc.getLongitude());
                                    lat = loc.getLatitude();
                                    lon = loc.getLongitude();
                                }
                               // results.setText(locInfo);

                                final String geoURI = String.format("geo:%f,%f", lat, lon);
                                Uri geo = Uri.parse(geoURI);
                                Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
                                startActivity(geoMap);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    });
                    mapButton.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geo, menu);
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
