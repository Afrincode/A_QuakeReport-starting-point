/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.android.quakereport.QueryUtils.extractEarthquakes;
import static com.example.android.quakereport.QueryUtils.makeHttpRequest;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public ListView earthquakeListView;
    public final static String URLstr="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
//        ArrayList<Earthquake> earthquakes = new ArrayList<>();
//        earthquakes.add(new Earthquake(7.2f,"San Francisco","Feb 2,2016"));
//        earthquakes.add(new Earthquake(6.1f,"London","July 20,2015"));
//        earthquakes.add(new Earthquake(3.9f,"Tokyo","Nov 10,2014"));
//        earthquakes.add(new Earthquake(5.4f,"Mexico City","May 3,2014"));
//        earthquakes.add(new Earthquake(2.8f,"Moscow","Jan 31,2013"));
//        earthquakes.add(new Earthquake(4.9f,"Rio de Janeiro","Aug 19,2012"));
//        earthquakes.add(new Earthquake(1.6f,"Paris","Oct 30,2011"));
        earthquakeListView = (ListView) findViewById(R.id.listview_id);
        new TsunamyAsyncTask().execute(URLstr);
//        ArrayList<Earthquake> earthquakes =
        // Find a reference to the {@link ListView} in the layout
        // Create a new {@link ArrayAdapter} of earthquakes
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//        EarthquakeAdapter earthquakeAdapter=new EarthquakeAdapter(EarthquakeActivity.this,R.layout.list_item,earthquakes);
//        earthquakeListView.setAdapter(earthquakeAdapter);
    }
    class TsunamyAsyncTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String jsonResponse="";
            try {
                URL url=new URL(strings[0]);
                jsonResponse= makeHttpRequest(url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Earthquake> earthquakes=extractEarthquakes(s);
            EarthquakeAdapter earthquakeAdapter=new EarthquakeAdapter(EarthquakeActivity.this,R.layout.list_item,earthquakes);
            earthquakeListView.setAdapter(earthquakeAdapter);
        }
    }
}

