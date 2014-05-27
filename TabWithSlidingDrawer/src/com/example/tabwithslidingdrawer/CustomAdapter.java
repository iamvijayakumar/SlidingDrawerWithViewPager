package com.example.tabwithslidingdrawer;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

 Context context;
 List<DrawingRowItem> rowItem;

 CustomAdapter(Context context, List<DrawingRowItem> rowItem) {
  this.context = context;
  this.rowItem = rowItem;
 }


 @Override
 public View getView(int position, View convertView, ViewGroup parent) {

if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        DrawingRowItem row_pos = rowItem.get(position);
        txtTitle.setText(row_pos.getTitle());

        return convertView;
 }

 @Override
 public int getCount() {
  return rowItem.size();
 }

 @Override
 public Object getItem(int position) {
  return rowItem.get(position);
 }

 @Override
 public long getItemId(int position) {
  return rowItem.indexOf(getItem(position));
 }

}