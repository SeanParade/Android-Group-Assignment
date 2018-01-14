package com.gbc.flightbooker.utilities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gbc.flightbooker.R;
import com.gbc.flightbooker.db.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nooran on 2017-12-20.
 */

public class ExpandableFlightListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> flightHeader;
    private HashMap<String, List<Flight>> flightChild;

    public ExpandableFlightListAdapter(Context context, List<String> flightHeader,
                                       HashMap<String, List<Flight>> flightChild)
    {
        this.context = context;
        this.flightHeader = flightHeader;
        this.flightChild = flightChild;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition)
    {
        return this.flightChild.get(this.flightHeader.get(listPosition)).get(expandedListPosition);

    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition)
    {
        return expandedListPosition;
    }
    @Override
    public View getChildView(int listPosition, int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        Flight flightDetails =  (Flight)getChild(listPosition, expandedListPosition);

        if(convertView== null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedTextView = convertView.findViewById(R.id.expandedListItem);
        expandedTextView.setText(flightDetails.getFlightDetail());
        return convertView;
    }
    @Override
    public int getChildrenCount(int listPosition)
    {
        return this.flightChild.get(this.flightHeader.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition)
    {
        return this.flightHeader.get(listPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.flightHeader.size();
    }
    @Override
    public long getGroupId(int listPosition)
    {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                            View convertView, ViewGroup parent)
    {
        String headerTitle = (String)getGroup(listPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listHeader = (TextView) convertView.findViewById(R.id.expandedListHeader);
        listHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }
    @Override
    public boolean isChildSelectable(int listPosition, int childPosition)
    {
        return false;
    }

}
