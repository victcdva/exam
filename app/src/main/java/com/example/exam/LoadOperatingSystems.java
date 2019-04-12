package com.example.exam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LoadOperatingSystems extends AsyncTask<String, Void, ArrayList<OperatingSystem>> {
    private static final String API_URL = "http://www.cisatj.com/20185a/realstate/realstate.php";
    private static final String KEY_PROPERTIES = "properties";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_CITY = "city";
    private static final String KEY_NAME = "name";
    private static final String KEY_MEASUREMENTS = "measurements";
    private static final String KEY_LOTSIZE = "lotSize";
    private static final String KEY_CONSTRUCTION = "constructionSize";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_BEDROOMS = "bedrooms";
    private static final String KEY_BATHROOMS = "bathrooms";
    private static final String KEY_PRICE = "price";

    private Activity activity;
    private ProgressDialog progress;

    //constructor
    public LoadOperatingSystems(Activity activity) { this.activity = activity; }
    //methods
    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }
    @Override
    protected ArrayList<OperatingSystem> doInBackground(String... params) {
        //define list
        ArrayList<OperatingSystem> list = new ArrayList<OperatingSystem>();
        //variables
        InputStream data = null;
        String result = "";
        JSONObject resultJSON = null;
        boolean error = false;
        //connect to API
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(API_URL);
            connection = (HttpURLConnection)url.openConnection();
            data = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(data));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            Log.d("result", result);
        }
        catch (MalformedURLException ex) {
            error = true;
            Log.e("Error", ex.getMessage());
        }
        catch (IOException ex) {
            error = true;
            Log.e("Error", ex.getMessage());
        }
        finally {
            if (connection != null) connection.disconnect();
        }

        //parse data to JSON
        if (!error) {
            try {
                resultJSON = new JSONObject(result);
            }
            catch (JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }

        //create object and populate list
        if (!error) {
            try {
                JSONArray array = resultJSON.getJSONArray(KEY_PROPERTIES);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i); //read each item
                    JSONObject city =  item.getJSONObject(KEY_CITY);
                    JSONObject measurements =  item.getJSONObject(KEY_MEASUREMENTS);
                    JSONObject details =  item.getJSONObject(KEY_DETAILS);
                    //read keys
                    String description = item.getString(KEY_DESCRIPTION);
                    String iconUrl = item.getString(KEY_IMAGE);
                    String name = city.getString(KEY_NAME);
                    int lotSize = measurements.getInt(KEY_LOTSIZE);
                    int construction = measurements.getInt(KEY_CONSTRUCTION);
                    int bedrooms = details.getInt(KEY_BEDROOMS);
                    double bathrooms = details.getDouble(KEY_BATHROOMS);
                    int price = item.getInt(KEY_PRICE);
                    //create object
                    OperatingSystem os = new OperatingSystem(description, iconUrl, name, lotSize, construction, bedrooms, bathrooms, price);
                    //add object to list
                    list.add(os);
                }
            }
            catch (JSONException ex) {
                error = true;
                Log.e("Error", ex.getMessage());
            }
        }

        //return list
        return list;
    }
    @Override
    protected void onPostExecute(ArrayList<OperatingSystem> list) {
        //list view
        ListView listOperatingSystems = this.activity.findViewById(R.id.listOperatingSystems);
        //adapter
        ListAdapter adapter = new ListAdapter(list, this.activity);
        //Bind list to adapter
        listOperatingSystems.setAdapter(adapter);
        //stop progress dialog
        progress.dismiss();
    }
}
