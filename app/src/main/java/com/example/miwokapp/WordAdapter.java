package com.example.miwokapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class WordAdapter extends ArrayAdapter<Word> {

    int mcolourResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words,int colourResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
        mcolourResourceId=colourResourceId;
    }


        @Override
        public View getView ( int position, View convertView, ViewGroup parent){
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link AndroidFlavor} object located at this position in the list
            Word currentWord = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            // Get the version name from the current AndroidFlavor object and
            // set this text on the name TextView
            miwokTextView.setText(currentWord.getMmiwokTranslate());

            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            defaultTextView.setText(currentWord.getMdefaultTranslate());

            ImageView imageView=(ImageView)listItemView.findViewById(R.id.images);


            if(currentWord.hasImage())
            {
            imageView.setImageResource(currentWord.getmImageResourseId());
            imageView.setVisibility(View.VISIBLE);
            }
            else
            {
                imageView.setVisibility(View.GONE);
            }

            View textContainer=listItemView.findViewById(R.id.text_container);
            int colour= ContextCompat.getColor(getContext(),mcolourResourceId);
            textContainer.setBackgroundColor(colour);

            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;
        }
}
