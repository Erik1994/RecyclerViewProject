package com.erikantonyan.recyclerviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ExampleItem> exampleItems;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();

        initRecyclerView();
        initFields();
        setClickListeners();

    }
    private void initFields() {
      buttonInsert = findViewById(R.id.button_insert);
      buttonRemove = findViewById(R.id.button_remove);
      editTextInsert = findViewById(R.id.edittext_insert);
      editTextRemove = findViewById(R.id.edittext_remove);
    }

    private void setClickListeners() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
    }

    public void insertItem(int position) {
        if(position >= 0 && position <= exampleItems.size()) {
            exampleItems.add(position, new ExampleItem(R.drawable.ic_android1,"new item added at " + position,"Line " + position));
            adapter.notifyItemInserted(position);
        }

    }

    public void removeItem(int position) {
        if(position >= 0 && position <= exampleItems.size()) {
            exampleItems.remove(position);
            adapter.notifyItemRemoved(position);
        }


    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String s = exampleItems.get(position).getmText1().toString() + " is clicked!!!!";
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onItemClickRemove(int position) {
                removeItem(position);
            }
        });

    }

    public void createExampleList() {
        exampleItems = new ArrayList<>();
        exampleItems.add(new ExampleItem(R.drawable.ic_android,"Image", "Line"));
        exampleItems.add(new ExampleItem(R.drawable.ic_android1,"Image 1", "Lin1 1"));
        exampleItems.add(new ExampleItem(R.drawable.ic_android2,"Image 2", "Line 2"));
    }
}
