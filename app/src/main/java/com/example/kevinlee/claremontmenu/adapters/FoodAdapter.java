package com.example.kevinlee.claremontmenu.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kevinlee.claremontmenu.R;
import com.example.kevinlee.claremontmenu.data.Food;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by kevinlee on 12/23/16.
 */

public class FoodAdapter extends ArrayAdapter<Food> {

    public FoodAdapter(Activity context, ArrayList<Food> foodList) {
        super(context, 0, foodList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.food_list_item, parent, false);
        }

        Food currentFood = getItem(position);

        String currentName = currentFood.getName();
        currentName = currentName.replace("&#38;", "&");
        Log.i("Food name", currentName);
        int review_count = currentFood.getReview_count();
        double rating = currentFood.getRating();
        String imageURL = currentFood.getImage();

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        TextView reviewCountTextView = (TextView) listItemView.findViewById(R.id.review_count_text_view);
        TextView ratingTextView = (TextView) listItemView.findViewById(R.id.rating_text_view);
        ImageView foodImageView = (ImageView) listItemView.findViewById(R.id.food_image_view);
        RatingBar ratingBar = (RatingBar) listItemView.findViewById(R.id.list_view_rating_bar);


        Resources res = getContext().getResources();

        nameTextView.setText(currentName);
//        if(nameTextView.getLineCount() != 1) {
//            nameTextView.setMaxLines(1);
//            nameTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//            nameTextView.setMarqueeRepeatLimit(-1);
//        }
        ratingTextView.setText(String.format(res.getString(R.string.list_view_rating_), rating));
        ratingBar.setRating((float) rating);
        reviewCountTextView.setText(String.format(res.getString(R.string.review_count), review_count));
        if(!imageURL.equals("null")) {
            ImageLoader.getInstance().displayImage(imageURL, foodImageView);
        } else {
            foodImageView.setImageResource(R.drawable.no_image);
        }

        return listItemView;

    }
}
