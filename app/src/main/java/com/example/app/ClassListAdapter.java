package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app.Retrofit.ClassModel;
import com.example.app.Retrofit.StudentModel;

import java.util.List;

public class ClassListAdapter extends ArrayAdapter<ClassModel> {
    private static final String TAG = "PersonalListAdapter";

    private Context mContext;

    public ClassListAdapter(@NonNull Context context, @NonNull List<ClassModel> objects) {
        super(context, 0,objects);
        //this.mContext = mContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.adapter_view_layout, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
         ClassModel currentClass = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        timeTextView.setText(currentClass.getTime());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView moduleTextView = (TextView) listItemView.findViewById(R.id.module);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        moduleTextView.setText(currentClass.getModule());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView venueTextView = (TextView) listItemView.findViewById(R.id.venue);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        venueTextView.setText(currentClass.getVenue());


        return listItemView;
    }



}
