package mmu.android.app.vehicles;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main activity of the app, displays a list of all vehicles on the homepage
 */
public class MainActivity extends AppCompatActivity {

    // declare variables and objects to use in global scope
    Gson gson = new Gson();
    ArrayList<Vehicle> allVehicles = new ArrayList<>();
    ListView vehiclesList;
    ImageButton homeButton;
    ImageButton addButton;
    SwipeRefreshLayout swipeLayout;
    List<String> displayVehicles;
    ArrayAdapter arrayAdapter;
    String [] vehicles;
    String api_key = "cf5f280af1aeb4a4e2c3b0b5be807e6e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // store a reference to all the
        swipeLayout = findViewById(R.id.swipe_container);
        vehiclesList = findViewById(R.id.vehiclesList);
        homeButton = findViewById(R.id.homeButton);
        addButton = findViewById(R.id.addButton);

        // store the url string into the variable
        String url = "http://10.0.2.2:8005/vehiclesdb/simpleServer?api_key=" + api_key;

        // call refreshPage method
        refreshPage(url);

        // execute GetAllVehicles AsyncTask to retrieve all the vehicles
        new GetAllVehicles().execute(url);

        //call onItemClick method
        onItemClick();

        // set the background colours of the buttons
        homeButton.setBackgroundColor(Color.BLACK);
        addButton.setBackgroundColor(Color.WHITE);
    }

    /**
     * AsyncTask to retreive all the vehicles on the network thread
     */
    private class GetAllVehicles extends AsyncTask<String, Integer, String>
    {
        /**
         * makes a get request and retrieves the list of all vehicles from the server
         * @param strings, stores all the strings required to successfully complete the Get request
         * @return response, returns the response received from the server
         */
        protected String doInBackground(String... strings)
        {
            // declare objects and variables
            URL url;
            InputStream in = null;
            String response = "";
            String requestURL = strings[0];
            try
            {
                // store the url in the url object
                url = new URL(requestURL);
                // create the connection object
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // set the timout and the method type for the request
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");

                // get the server response code to determine what to do next (i.e success/error)
                int responseCode = conn.getResponseCode();

                System.out.println("response code = " + responseCode);

                // check if the response is ok
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    // store the information retrieved in the Input Stream
                    in = new BufferedInputStream(conn.getInputStream());
                }
                else
                {
                    // display an error message on the screen
                    Toast.makeText(MainActivity.this, "Error: failed to retrieve the vehicles :(", Toast.LENGTH_LONG).show();
                    response = "";
                }
                // convert the information received into a string
                response = convertStreamToString(in);

                // close the input stream
                in.close();

                // end the connection by disconnecting
                conn.disconnect();
            }
            // deal with the exception
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //return the response string
            return response;
        }

        /**
         * deals with the data received after the get request is successful
         * @param response, response consists of all the data the will be displayed in the listview
         */
        protected void onPostExecute(String response)
        {
            //casts all the data received into vehicle object type
            Type vehicle = new TypeToken<ArrayList<Vehicle>>(){}.getType();

            // convert the json to fruit objects and store into arraylist fruit
            allVehicles = gson.fromJson(response, vehicle);

            // checks if the arraylist is null
            if(allVehicles != null)
            {
                // declare the size of the vehicles string
                vehicles = new String[allVehicles.size()];

                // loop through allVehicles array
                for(int i = 0; i < allVehicles.size(); i++)
                {
                    // store the vehicle at index of arraylist into thisVehicle object
                    Vehicle thisVehicle = allVehicles.get(i);

                    // store the string into the vehicles index
                    vehicles[i] = thisVehicle.getMake() + " " + thisVehicle.getModel() + " (" + thisVehicle.getYear() + ")\n" + thisVehicle.getLicense_number();
                }

                // retrieve all the data into the arraylist as a list
                displayVehicles = new ArrayList<String>(Arrays.asList(vehicles));

                // store the list into the arrayadapter
                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, displayVehicles);

                // set the adaptter of the vehiclesList listview to the arrayadapter
                vehiclesList.setAdapter(arrayAdapter);
            }
            else
            {
                // display an error message on the screen
                Toast.makeText(MainActivity.this, "Error: failed to retrieve the vehicles :(", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * AsyncTask to delete the vehicle from the database
     */
    private class DeleteVehicle extends AsyncTask <String, Integer, String>
    {
        /**
         * makes a delete request to the server to delete the vehicle
         * @param strings, consists of the data required to make the request successful
         * @return response, retrieves a message from the server
         */
        protected String doInBackground(String... strings)
        {
            // declare objects and variables
            URL url;
            InputStream in = null;
            String response = "";
            String requestURL = strings[0];
            try
            {
                // store the url in the url object
                url = new URL(requestURL);

                // create the connection object
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // set the timout and the method type for the request
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("DELETE");

                // get the server response code to determine what to do next (i.e success/error)
                int responseCode = conn.getResponseCode();

                System.out.println("response code = " + responseCode);

                // check if the response is ok
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    // receive the response from the server
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    // check if the line is null
                    while((line = br.readLine()) != null)
                    {
                        response += line;
                    }
                }
                else
                {
                    // display a message on the screen
                    Toast.makeText(MainActivity.this, "Error: failed to delete the vehicles :(", Toast.LENGTH_LONG).show();
                    response = "";
                }
            }
            // deal with the exception
            catch (Exception e)
            {
                e.printStackTrace();
                return "Error: error deleting the vehicle" + response;
            }
            return "Vehicle deleted" + response;
        }

        protected void onPostExecute(String response)
        {
            // display a message on the screen
            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * deals with the refresh request when a user swipes down the screen to refresh the page
     * @param url, consists of the url required to make a GET request to retrieve all the vehicles
     */
    public void refreshPage(final String url)
    {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // call the asynctask to retrieve all the vehicles
                new GetAllVehicles().execute(url);

                // set the refreshing to false to hide the loading animation
                swipeLayout.setRefreshing(false);
            }
        });

        // Scheme colors for animation to display when reloading or refreshing the page
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
    }

    // method consisting of all the click events of the buttons and the long clicks
    public void onItemClick()
    {
        vehiclesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // display a toast message on the screen
                Toast.makeText(MainActivity.this, "you pressed " + allVehicles.get(i).getMake(),Toast.LENGTH_SHORT).show();

                // store the intent in the intent object which defince which activity to start next
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                // store data to receive in the next activity
                intent.putExtra("vehicle", allVehicles.get(i));

                // start the new activity
                startActivity(intent);
            }
        });

        // deals with the long click of the list
        vehiclesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                // creates a window
                AlertDialog.Builder adb= new AlertDialog.Builder(MainActivity.this);

                // set the title of the window
                adb.setTitle("Delete Vehicle");

                // set the message on the window
                adb.setMessage("Are you sure you want to delete this Vehicle?");

                // store the position index into a variable
                final int position = i;

                // display an option to cancel
                adb.setNegativeButton("Cancel", null);

                // display a yes option
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener(){

                    // the user clicked on yes, call the method
                    public void onClick(DialogInterface dialog, int which){

                        // create a vehicle object and store vehicle information
                        Vehicle thisVehicle = allVehicles.get(position);

                        // store the url into a string variable
                        String url = "http://10.0.2.2:8005/vehiclesdb/simpleServer?vehicle_id=" + thisVehicle.getVehicle_id() + "&api_key=" + api_key;

                        // execute delete asynctask
                        new DeleteVehicle().execute(url);

                        // remove the vehicle from the list on a specific position
                        displayVehicles.remove(position);

                        // tell the arrayadapter of the changes
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                // show the window
                adb.show();

                // return boolean value
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // store the intent of starting the next activity
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);

                // start the activity
                startActivity(intent);
            }
        });
    }

    /**
     * convert the information retrieved from the server into String
     * @param is, the inputstream received in the get request from the server
     * @return String, the inputstream converted into the stream
     */
    public String convertStreamToString(InputStream is)
    {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}