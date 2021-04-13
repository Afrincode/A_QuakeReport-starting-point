package com.example.android.quakereport;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    private ArrayList<Earthquake> earthquakeslist;
    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Earthquake> objects) {
        super(context, resource, objects);
        this.earthquakeslist=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView magTV=(TextView)listItemView.findViewById(R.id.magnitudetv);
        TextView locTV=(TextView)listItemView.findViewById(R.id.locationtv);
        TextView dateTV=(TextView)listItemView.findViewById(R.id.datetv);

//        magTV.setFloat(earthquakeslist.get(position).getMagnitude());
        magTV.setText(String.valueOf(earthquakeslist.get(position).getMagnitude()));
        locTV.setText(earthquakeslist.get(position).getLocation());
        //dateTV.setText(earthquakeslist.get(position).getDate());
        Date dateObject = new Date(earthquakeslist.get(position).getTimeInMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.datetv);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.timetv);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);
        return listItemView;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
