package mmu.android.app.vehicles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;

/**
 * Activity that shows all the vehicle details
 */
public class DetailsActivity extends AppCompatActivity {

    // declare variables, objects, and references
    ImageView vehicleImage;
    TextView makeAndModelTextView;
    TextView regNoTextView;
    ListView vehicleDataLV;
    Gson gson = new Gson();
    String [] retrieveVehicleData;
    Vehicle thisVehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // call displayDetails method
        displayDetails();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // create an option menu on the action bar
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);

        return true;
    }

    /**
     * deals with the option selected from the menu bar on the action bar
     * @param item,  item displayed in the menu
     * @return boolean, returns true boolean value
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // check which case to run
        switch (item.getItemId())
        {
            case R.id.updateVehicle:
                // create a new intent
                Intent startUpdateActivity = new Intent(getApplicationContext(), InsertActivity.class);

                // store some data to send to the next activity
                startUpdateActivity.putExtra("vehicle", thisVehicle);

                // start the activity
                startActivity(startUpdateActivity);
                break;
        }
        return true;
    }

    /**
     * retrieves a logo from an API online using the urls of the companies
     */
    private class GetVehicleLogo extends AsyncTask<String, Integer, Bitmap>
    {
        /**
         * makes a GET request to retrieve a logo from an online API
         * @param strings, strings required to make the Get request
         * @return response, a bitmap image logo of the vehicle make
         */
        protected Bitmap doInBackground(String... strings)
        {
            // declare objects and variables
            URL url;
            Bitmap response = null;
            String requestUrl = strings[0];

            try {
                // store the url in the url object
                url = new URL(requestUrl);

                // create the connection object
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // set the timout and the method type for the request
                conn.setReadTimeout(1500);
                conn.setConnectTimeout(1500);
                conn.setRequestMethod("GET");

                // get the server response code to determine what to do next (i.e success/error)
                int responseCode = conn.getResponseCode();

                // check if the response is ok
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    // store the logo image received
                    response = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    // close the input stream
                    url.openConnection().getInputStream().close();
                }
                else
                {
                    // display a message on the screen
                    Toast.makeText(DetailsActivity.this, "Error: failed to retrieve the image :(", Toast.LENGTH_LONG).show();
                }
                // close the connection
                conn.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return response;
        }

        /**
         * displays the logo on the screen once the request is complete
         * @param response, an image is passed as a parameter to display on the screen
         */
        protected void onPostExecute(Bitmap response)
        {
            // check if the response is null
            if(response != null)
            {
                // display the image logo in vehicleImage
                vehicleImage.setImageBitmap(response);
            }
        }
    }

    /**
     * display all the details on the screen
     */
    public void displayDetails()
    {
        // declare the variables and references
        Bundle extras = getIntent().getExtras();
        vehicleImage = findViewById(R.id.vehicleImage);
        makeAndModelTextView = findViewById(R.id.makeAndModelTextView);
        regNoTextView = findViewById(R.id.regNoTextView);
        vehicleDataLV = findViewById(R.id.vehicleDataLV);
        thisVehicle = (Vehicle) extras.get("vehicle");

        // store the url
        String requestURL = "https://logo.clearbit.com/"+ thisVehicle.getMake() + ".co.uk";

        // call the AsyncTask to retrieve make logo
        new GetVehicleLogo().execute(requestURL);

        // convert the vehicle to string
        String getVehicleData = gson.toJson(thisVehicle);
        JsonParser parseObject = new JsonParser();

        JsonObject vehicleData = (JsonObject) parseObject.parse(getVehicleData);

        // set the text colour
        makeAndModelTextView.setTextColor(Color.BLACK);

        // display the text on the textview
        makeAndModelTextView.setText(vehicleData.get("make").getAsString() + " " + vehicleData.get("model").getAsString() + " (" + vehicleData.get("year").getAsString() + ")" );

        // set the background colour for the reg number
        regNoTextView.setBackgroundColor(Color.rgb(249,202,36));
        // set the text colour
        regNoTextView.setTextColor(Color.BLACK);
        //set the text
        regNoTextView.setText(thisVehicle.getLicense_number());

        // create a hashset object
        HashSet <Map.Entry<String,JsonElement>> entries = new HashSet<>(vehicleData.entrySet());

        // set the size of the string array
        retrieveVehicleData = new String [11];

        // declare a variable to count numbers of entries
        int i = 0;
        for(Map.Entry<String,JsonElement> entry: entries)
        {
            // filter the data to store in the array
            if(!"make".equalsIgnoreCase(entry.getKey()) && !"model".equalsIgnoreCase(entry.getKey())
                    && !"year".equalsIgnoreCase(entry.getKey()) && !"vehicle_id".equalsIgnoreCase(entry.getKey())
                    && !"license_number".equalsIgnoreCase(entry.getKey()))
            {
                // replace the character
                String key = entry.getKey().replace("_" , " ");

                // store the value into the variable
                String value = entry.getValue().getAsString();

                // check for the value
                if(value.equalsIgnoreCase("true"))
                {
                    // store the string into the value varialbe
                    value = "Vehicle Sold";
                }
                else if(value.equalsIgnoreCase("false"))
                {
                    // store the string into the value variable
                    value = "Vehicle available";
                }

                // store the string into the array index
                retrieveVehicleData[i] = key.substring(0, 1).toUpperCase()  + key.substring(1) + ": " + value;

                // increase the index by one
                i++;
            }
        }
        // store the array into the arrayadapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, retrieveVehicleData);

        // set the vehicleDataLV adapter.
        vehicleDataLV.setAdapter(arrayAdapter);
    }
}
