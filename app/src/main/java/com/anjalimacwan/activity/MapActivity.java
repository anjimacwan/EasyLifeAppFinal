package com.anjalimacwan.activity;

import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.anjalimacwan.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.HttpGet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MapActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private static final String API_KEY = "AIzaSyAvtK2ckGC4Aeml2gP63hT_ctqluww5wHg";
    TextView startPoint, endPoint, answer;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //final EditText name = (EditText) findViewById(R.id.placename);
        final Geocoder coder = new Geocoder(getApplicationContext());
        //final Button mapButton = (Button)findViewById(R.id.map);
        startPoint = (EditText) findViewById(R.id.txtStartPoint);
        endPoint = (EditText) findViewById(R.id.txtEndPoint);
        // set a default start and destinaton
        startPoint.setText(("941 Progress Avenue"));
        endPoint.setText(("CN Tower"));

        answer = (TextView) findViewById(R.id.txtDirections);

        /*Button geocode = (Button) findViewById(R.id.geocode);
        geocode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {*/

                    /*mapButton.setOnClickListener(new View.OnClickListener() {
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
*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void getInfo(View view) {
        //keep in mind your AVD should support Google API


        //
        String strUrl = "http://maps.googleapis.com/maps/api/directions/json?";
        //
        List<Address> geoCodes = null;
        //get the start point from the edit text startPoint
        String startName = startPoint.getText().toString();
        try {
            //Returns an array of Addresses that are known to describe the named location
            //do
            //get geocodes of start point
            geoCodes = new Geocoder(this, Locale.getDefault()).getFromLocationName(startName, 5);
            //while (geoCodes.size()<=0);
            //extract the latitude and longitude from the first address
            String origineLatitude = String.valueOf(geoCodes.get(0).getLatitude());
            String origineLongitude = String.valueOf(geoCodes.get(0).getLongitude());
            //encode the latitude and longitude
            origineLatitude = URLEncoder.encode(origineLatitude, "UTF-8"); // 941 Progress avenue
            origineLongitude = URLEncoder.encode(origineLongitude, "UTF-8");
            //while (geoCodes.get(0)==null)

            //String origineLatitude = URLEncoder.encode("43.7850417", "UTF-8"); // 941 Progress avenue
            //String origineLongitude = URLEncoder.encode("-79.2270519", "UTF-8");
            //get the end point from edit text endPoint
            String endName = endPoint.getText().toString();
            //get the geocodes of destinatopn point
            geoCodes = new Geocoder(this, Locale.getDefault()).getFromLocationName(endName, 5);
            String destinationLatitude = String.valueOf(geoCodes.get(0).getLatitude());
            String destinationLongitude = String.valueOf(geoCodes.get(0).getLongitude());
            //
            System.out.println("destination: " + destinationLatitude);
            System.out.println("destination: " + destinationLongitude);
            //

            //
            //String destinationLatitude = URLEncoder.encode("43.6425662", "UTF-8"); // Eaton Tower
            //String destinationLongitude = URLEncoder.encode("-79.3870567", "UTF-8");
            //create the complete url string
            String origine = "origin=" + origineLatitude + "," + origineLongitude;
            String destination = "destination=" + destinationLatitude + "," + destinationLongitude;
            //
            String sensorValue = URLEncoder.encode("false", "UTF-8");
            String sensor = "sensor=" + sensorValue;
            //
            /*year - the year minus 1900.
            month - the month between 0-11.
            date - the day of the month between 1-31.
            hrs - the hours between 0-23.
            min - the minutes between 0-59.
            sec - the seconds between 0-59.*/
            Date date = new Date(116, 11, 13, 16, 30, 30);
            long l = date.getTime() / 1000;
            System.out.println("Time: " + String.valueOf(l));
            String departureTimeValue = URLEncoder.encode(String.valueOf(l), "UTF-8");
            System.out.println(departureTimeValue);
            String departureTime = "departure_time=" + departureTimeValue;
            //
            String modeValue = URLEncoder.encode("transit", "UTF-8");
            String mode = "mode=" + modeValue;

            //create the url
            strUrl = strUrl + origine + "&" + destination + "&" + sensor + "&" + departureTime + "&" + mode;
            System.out.println(strUrl);

            new ReadTransitJSONFeedTask().execute(strUrl);

            //directions.setText(strUrl);
        } catch (UnsupportedEncodingException e) {
            Log.d("encodingerror", e.getMessage());
        } catch (IOException e) {
            Log.d("geocode error", e.getMessage());

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Map Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    //
    private class ReadTransitJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            String directions = ""; //to hold the result after parsing
            try {
                String str = result; //readJSONFeed(result);
                Log.d("link", str);
                // build a JSON object
                JSONObject obj = new JSONObject(str);
                if (obj.getString("status").equals("OK"))
                    System.out.println("OK");
                JSONArray routes = obj.getJSONArray("routes");
                JSONObject data, data1;
                JSONObject bounds;
                //System.out.println(routes.length());
                //
                for (int i = 0; i < routes.length(); i++) {
                    data = routes.getJSONObject(i);
                    Iterator keys = data.keys();
                    while (keys.hasNext()) {
                        System.out.println(keys.next());
                    }
                    //System.out.println(data);
                    bounds = data.getJSONObject("bounds");
                    System.out.println(bounds);
                    Iterator keys1 = bounds.keys();
                    while (keys1.hasNext()) {
                        System.out.println(keys1.next());
                    }

                    JSONArray legs = data.getJSONArray("legs");
                    //System.out.println(legs.length());
                    for (i = 0; i < legs.length(); i++) {
                        data = legs.getJSONObject(i);
                        //System.out.println(legs);
                        Iterator keys2 = data.keys();
                        while (keys2.hasNext()) {
                            System.out.println(keys2.next());

                        }
                        JSONArray steps = data.getJSONArray("steps");
                        for (int j = 0; j < steps.length(); j++) {
                            data1 = steps.getJSONObject(j);
                            //System.out.println(steps);

                            Iterator keys3 = data1.keys();
                            while (keys3.hasNext()) {
                                String key = (String) keys3.next();
                                if (key.equals("html_instructions")) {
                                    directions += "\n" + data1.getString(key);
                                    System.out.println(data1.getString(key));
                                }

                            }
                        }

                    }


                }
                // display the results in textview txtDirections
                answer.setText(directions);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                builder1.setMessage(directions);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    //
    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        Log.d("url", URL);
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
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
