package com.eldroid.zosa_listview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDoItem> toDoItems;
    private ToDoAdapter adapter;
    private ListView listView;
    private EditText editText;
    private Button addButton;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.addButton);

        toDoItems = new ArrayList<>();
        adapter = new ToDoAdapter(this, toDoItems);
        listView.setAdapter(adapter);

        gestureDetector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
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
    }

    private void showEditDeleteDialog(int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Edit or Delete");
        dialogBuilder.setMessage("Do you want to edit or delete this item?");

        dialogBuilder.setPositiveButton("Edit", (dialog, which) -> {
            showEditDialog(position);
        });

        dialogBuilder.setNegativeButton("Delete", (dialog, which) -> {
            toDoItems.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
        });

        dialogBuilder.show();
    }

    private void showEditDialog(int position) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View editView = inflater.inflate(R.layout.dialog_edit_item, null);

        EditText editItemText = editView.findViewById(R.id.editItemText);
        editItemText.setText(toDoItems.get(position).getText());

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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
