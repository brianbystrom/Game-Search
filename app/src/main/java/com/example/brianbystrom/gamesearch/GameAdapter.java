/*
Assignment #: Homework 05
File Name: GameAdapter.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.gamesearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by brianbystrom on 2/16/17.
 */

public class GameAdapter extends ArrayAdapter<Data> {
    List<Data> mData;
    Context mContext;
    int mResource;
    int selectedPosition = -1;
    RadioGroup rg;

    public GameAdapter(Context context, int resource, List<Data> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            rg = (RadioGroup) convertView.findViewById(R.id.game_list_radio_group);

            Data data = mData.get(position);

            RadioButton rb = (RadioButton) convertView.findViewById(R.id.game_radio_button);
            rb.setText(data.getTitle());
            rg.addView(rb);

        }




        return convertView;
    }

}
