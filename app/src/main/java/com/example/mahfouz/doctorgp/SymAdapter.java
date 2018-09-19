package com.example.mahfouz.doctorgp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MAHFOUZ on 10/4/2018.
 */

public class SymAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String, List<String>> major_sym;
    private List<String> major_sym_list;


    public SymAdapter(Context ctx, HashMap<String, List<String>> major_sym, List<String> major_sym_list )
    {
        this.ctx = ctx;
        this.major_sym = major_sym;
        this.major_sym_list = major_sym_list;

    }




    @Override
    public int getGroupCount() {
        return major_sym_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return major_sym.get(major_sym_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return major_sym_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return major_sym.get(major_sym_list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String group_title = (String) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.sym_parent_layout, parent,false);
        }
        TextView parent_textview = (TextView) convertView.findViewById(R.id.sym_parent);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child_title =  (String) getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.sym_child_layout, parent,false);
        }
        TextView child_textview = (TextView) convertView.findViewById(R.id.sym_child);
        child_textview.setText(child_title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        Toast.makeText(ctx, major_sym.get(major_sym_list.get(groupPosition)).get(childPosition) + " from Category " +
//                major_sym_list.get(groupPosition) + " is selected ", Toast.LENGTH_SHORT).show();
        //ser_sym.add(major_sym.get(major_sym_list.get(groupPosition)).get(childPosition));

    return true;
    }



}
