package com.hari.development.faasosdelight;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by development on 26/09/15.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private List<Faassos> faassosList ;
    private LayoutInflater inflater;
    ImageLoader imageLoader = IssueTracker.getInstance().getImageLoader();


    public CustomListAdapter(Activity activity, List<Faassos> faassosList) {
        this.activity = activity;
        this.faassosList = faassosList;
    }

    @Override
    public int getCount() {
        return faassosList.size();
    }

    @Override
    public Object getItem(int position) {
        return faassosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_layout, null);

        if (imageLoader == null)
            imageLoader = IssueTracker.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Faassos faassos = faassosList.get(position);
        // thumbnail image
        thumbNail.setImageUrl(faassos.getImage(), imageLoader);

        // title
        name.setText(faassos.getName());

        // rating
        rating.setText("Rating: " + String.valueOf(faassos.getRating()));

        genre.setText(faassos.getCategory());

        // release year
        year.setText("Rs." + String.valueOf(faassos.getPrice()) + "/-");
        return convertView;
    }

}

