package mmu.android.app.vehicles;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class InsertActivity extends AppCompatActivity {

    // declare global variables
    TextView operation;
    EditText getMake;
    EditText getModel;
    EditText getYear;
    EditText getPrice;
    EditText getLicense_number;
    EditText getColour;
    EditText getNumber_doors;
    EditText getTransmission;
    EditText getMileage;
    EditText getFuel_type;
    EditText getEngine_size;
    EditText getBody_style;
    EditText getCondition;
    EditText getNotes;
    Button submitButton;
    ImageButton homeButton;
    ImageButton addButton;

    // store the api_key to use globally
    String api_key = "cf5f280af1aeb4a4e2c3b0b5be807e6e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // get the references into the variables
        operation = findViewById(R.id.operationView);
        getMake = findViewById(R.id.makeEditText);
        getModel = findViewById(R.id.modelEditText);
        getYear = findViewById(R.id.yearEditText);
        getPrice = findViewById(R.id.priceEditText);
        getLicense_number = findViewById(R.id.license_numberEditText);
        getColour = findViewById(R.id.colourEditText);
        getNumber_doors = findViewById(R.id.number_doorsEditText);
        getTransmission = findViewById(R.id.transmissionEditText);
        getMileage = findViewById(R.id.mileageEditText);
        getFuel_type = findViewById(R.id.fuel_typeEditText);
        getEngine_size = findViewById(R.id.engine_sizeEditText);
        getBody_style = findViewById(R.id.body_styleEditText);
        getCondition = findViewById(R.id.conditionEditView);
        getNotes = findViewById(R.id.notesEditText);

        submitButton= findViewById(R.id.submitButton);
        homeButton = findViewById(R.id.homeButton);
        addButton = findViewById(R.id.addButton);

        // set the backgroundcolours
        homeButton.setBackgroundColor(Color.WHITE);
        addButton.setBackgroundColor(Color.BLACK);
        submitButton.setBackgroundColor(Color.rgb(255,193,8));

        // get the extras received from the previous activity
        Bundle extras = getIntent().getExtras();

        // declare a vehicle object
        Vehicle thisVehicle = null;

        // set the request method to POST
        String requestMethod = "POST";

        // check if the extras is null
        if (extras != null)
        {
            // store the data into the vehicle object
            thisVehicle = (Vehicle) extras.get("vehicle");

            // call the method to display all the data on the form
            setVehicleData(thisVehicle);

            // set the request method to PUT
            requestMethod = "PUT";
        }

        // call the method
        onItemClick(thisVehicle, requestMethod);
    }

    /**
     * the user has inserted the data in the forms retrieve all the data
     * @param thisVehicle, vehicle object is sent to store the vehicle_id in the vehicle_id variable
     * @return vehicleInJson, vehicle object in json format is returned
     */
    public String getVehicleData(Vehicle thisVehicle)
    {
        // declare a string variable
        String vehicleInJson = "";
        try
        {
            // store all the vehicle data inserted into the variables
            Gson gson = new Gson();
            int vehicle_id = -999;
            if(thisVehicle != null)
            {
                vehicle_id = thisVehicle.getVehicle_id();
            }
            String make = getMake.getText().toString();
            String model = getModel.getText().toString();
            int year = Integer.parseInt(getYear.getText().toString());
            int price = Integer.parseInt(getPrice.getText().toString());
            String license_number = getLicense_number.getText().toString();
            String colour = getColour.getText().toString();
            int number_doors = Integer.parseInt(getNumber_doors.getText().toString());
            String transmission = getTransmission.getText().toString();
            int mileage = Integer.parseInt(getMileage.getText().toString());
            String fuel_type = getFuel_type.getText().toString();
            int engine_size = Integer.parseInt(getEngine_size.getText().toString());
            String body_style = getBody_style.getText().toString();
            String condition = getCondition.getText().toString();
            String notes = getNotes.getText().toString();

            // create a new vehicle object
            Vehicle newVehicle = new Vehicle(vehicle_id, make, model, year, price, license_number,
                    colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes, false);

            // convert the vehicle into the json string
            vehicleInJson = gson.toJson(newVehicle);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        // return the vehicle in json string
        return vehicleInJson;

    }

    /**
     * AsyncTask to insert and update the vehicles
     */
    private class InsertVehicle extends AsyncTask<String, Integer, String>
    {
        /**
         * makes POST and PUT requests to the server
         * @param strings, consists of all the strings required to make the server request
         * @return response, the data retrieved from the server
         */
        protected String doInBackground(String... strings)
        {
            // declare objects and variables
            URL url;
            String response = "";
            String requestURL = strings[0];
            String requestMethod = strings[1];
            String result = strings[2];
            try
            {
                // store the url in the url object
                url = new URL(requestURL);

                // create the connection object
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // set the timout and the method type for the request
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod(requestMethod);

                // creates an outputstream connection
                OutputStream os = conn.getOutputStream();

                // creates a buffered writer object to send the data to the server
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                // sends the data to the server
                writer.write(result);

                writer.flush();

                // closes the writer
                writer.close();

                // closes the outputstream
                os.close();

                // stored the response code
                int responseCode = conn.getResponseCode();

                // check the response code if it is ok
                if(responseCode == HttpsURLConnection.HTTP_OK)
                {
                    // receive the response from the server
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while((line = br.readLine()) != null)
                    {
                        response += line;
                    }
                }
                else
                {
                    // display a toast message on the screen
                    Toast.makeText(getApplicationContext(), "Error: failed to insert the vehicle :(", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return response;
        }

        /**
         *
         * @param response
         */
        protected void onPostExecute(String response)
        {
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        }
    }

    public void onItemClick(final Vehicle thisVehicle, final String requestMethod)
    {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create an intent to go to the mainactivity page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                // start the activity
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store the url into the requestURL variable
                String requestUrl = "http://10.0.2.2:8005/vehiclesdb/simpleServer?api_key=" + api_key;

                // store the vehicle in json string into the variable
                String vehicleinserting = getVehicleData(thisVehicle);

                // create hashmap object
                HashMap<String, String> data = new HashMap<>();

                // insert the data into the hashmap
                data.put("json", vehicleinserting);
                data.put("api_key", api_key);

                try
                {
                    // store the result into after converting the data from the hashmap
                    String result = getPostDataString(data);

                    // check if the result is empty
                    if(!result.equals(""))
                    {
                        // call the insertvehicle asynctask
                        new InsertVehicle().execute(requestUrl, requestMethod, result);

                        // create a new intent
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        // start the intent
                        startActivity(intent);
                    }
                    else
                    {
                        // display a toast message
                        Toast.makeText(getApplicationContext(), "Error: error inserting vehicle", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    public void setVehicleData(Vehicle vehicleData)
    {
        // set the data on the screen in the forms
        operation.setText("Update Vehicle");

        getMake.setText(vehicleData.getMake());
        getModel.setText(vehicleData.getModel());
        getYear.setText(Integer.toString(vehicleData.getYear()));
        getPrice.setText(Integer.toString(vehicleData.getPrice()));
        getLicense_number.setText(vehicleData.getLicense_number());
        getColour.setText(vehicleData.getColour());
        getNumber_doors.setText(Integer.toString(vehicleData.getNumber_doors()));
        getTransmission.setText(vehicleData.getTransmission());
        getMileage.setText(Integer.toString(vehicleData.getMileage()));
        getFuel_type.setText(vehicleData.getFuel_type());
        getEngine_size.setText(Integer.toString(vehicleData.getEngine_size()));
        getBody_style.setText(vehicleData.getBody_style());
        getCondition.setText(vehicleData.getCondition());
        getNotes.setText(vehicleData.getNotes());
    }

    // this method converts a hashmap to a URL query string of key/values pairs (e.g.: name=kaleem&job=tutors)
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        // create a  new Strringbuilder object
        StringBuilder result = new StringBuilder();

        // declare a boolean variable
        boolean first = true;

        // loop throught the hashmap
        for(Map.Entry<String, String> entry : params.entrySet())
        {
            // check for the boolean value
            if(first)
            {
                first = false;
            }
            else
            {
                // insert & character to the string
                result.append("&");
            }

            // insert the key value pairs into the string
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        // return the string.
        return result.toString();
    }
}