package com.eldroid.zosa_navbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ToDoItem> toDoItems;

    public ToDoAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        this.context = context;
        this.toDoItems = toDoItems;
    }

    @Override
    public int getCount() {
        return toDoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView textView = convertView.findViewById(R.id.textView);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        ToDoItem currentItem = (ToDoItem) getItem(position);

        checkBox.setChecked(currentItem.isChecked());
        textView.setText(currentItem.getText());
        imageView.setImageResource(currentItem.getImageResource());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> currentItem.setChecked(isChecked));

        return convertView;
    }
}

