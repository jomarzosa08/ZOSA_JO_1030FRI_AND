package com.eldroid.zosa_navbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.core.view.GestureDetectorCompat;

import java.util.ArrayList;

public class ToDoListFragment extends Fragment {

    private ArrayList<ToDoItem> toDoItems;
    private ToDoAdapter adapter;
    private ListView listView;
    private EditText editText;
    private Button addButton;
    private GestureDetectorCompat gestureDetector;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.editText);
        addButton = view.findViewById(R.id.addButton);

        toDoItems = new ArrayList<>();
        adapter = new ToDoAdapter(getActivity(), toDoItems);
        listView.setAdapter(adapter);

        gestureDetector = new GestureDetectorCompat(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                int position = listView.pointToPosition((int) e.getX(), (int) e.getY());
                if (position != ListView.INVALID_POSITION) {
                    showEditDeleteDialog(position);
                }
                return true;
            }
        });

        listView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        addButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                toDoItems.add(new ToDoItem(false, text, R.drawable.ic_launcher_foreground));
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        return view;
    }

    private void showEditDeleteDialog(int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Edit or Delete");
        dialogBuilder.setMessage("Do you want to edit or delete this item?");

        dialogBuilder.setPositiveButton("Edit", (dialog, which) -> {
            showEditDialog(position);
        });

        dialogBuilder.setNegativeButton("Delete", (dialog, which) -> {
            toDoItems.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Item Deleted", Toast.LENGTH_SHORT).show();
        });

        dialogBuilder.show();
    }

    private void showEditDialog(int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View editView = inflater.inflate(R.layout.dialog_edit_item, null);

        EditText editItemText = editView.findViewById(R.id.editItemText);
        editItemText.setText(toDoItems.get(position).getText());

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(editView);
        dialogBuilder.setTitle("Edit Item");

        dialogBuilder.setPositiveButton("Save", (dialog, which) -> {
            String newText = editItemText.getText().toString();
            if (!newText.isEmpty()) {
                toDoItems.get(position).setText(newText);
                adapter.notifyDataSetChanged();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", null);
        dialogBuilder.show();
    }
}
