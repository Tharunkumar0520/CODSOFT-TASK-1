package com.example.dolist;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText taskedit;
    private ListView tasklist;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;
    private int selectedtask = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskedit = findViewById(R.id.addtask);
        tasklist = findViewById(R.id.taskview);
        Button addbutton = findViewById(R.id.addBtn);
        Button deletebutton = findViewById(R.id.deleteBtn);
        Button updatebutton = findViewById(R.id.updateBtn);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        tasklist.setAdapter(adapter);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = taskedit.getText().toString();
                if (!newTask.isEmpty()) {
                    tasks.add(newTask);
                    adapter.notifyDataSetChanged();
                    taskedit.setText("");
                }
            }
        });

        tasklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedtask = position;
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedtask != -1) {
                    tasks.remove(selectedtask);
                    adapter.notifyDataSetChanged();
                    selectedtask = -1;
                }
            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTask = taskedit.getText().toString();
                if (!updatedTask.isEmpty() && selectedtask != -1) {
                    tasks.set(selectedtask, updatedTask);
                    adapter.notifyDataSetChanged();
                    taskedit.setText("");
                    selectedtask = -1;
                }
            }
        });

    }
}