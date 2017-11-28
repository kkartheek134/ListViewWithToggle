package com.example.kartheek.listviewwithtoggle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Kartheek on 11/27/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<MyModel> myList;
    public CustomAdapter(Context context, List<MyModel> myList) {
        this.context = context;
        this.myList = myList;
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        return myList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
    static class ViewHolder {
        protected TextView text;
        protected ToggleButton button;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.textview);
            viewHolder.button = (ToggleButton) convertView.findViewById(R.id.toggle);
            viewHolder.button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int pos = (Integer)buttonView.getTag();
                    myList.get(pos).setSelected(buttonView.isChecked());
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.textview, viewHolder.text);
            convertView.setTag(R.id.toggle, viewHolder.button);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.button.setTag(position); // This line is important.

        viewHolder.text.setText(myList.get(position).getName());
        viewHolder.button.setChecked(myList.get(position).isSelected());

        return convertView;
    }
}
