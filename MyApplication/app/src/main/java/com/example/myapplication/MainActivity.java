package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskukirjaViewModel mTaskukirjaViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskukirjaListAdapter adapter = new TaskukirjaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTaskukirjaViewModel = new ViewModelProvider(this).get(TaskukirjaViewModel.class);

        mTaskukirjaViewModel.getKaikkiTaskukirjat().observe(this, new Observer<List<Taskukirja>>() {
            @Override
            public void onChanged(@Nullable final List<Taskukirja> taskukirjat) {
                // Update the cached copy of the words in the adapter.
                adapter.setTaskukirjat(taskukirjat);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String arvot=data.getStringExtra(NewWordActivity.EXTRA_REPLY);
            String[] osa=arvot.split(";");
            int numero=Integer.parseInt(osa[0]);

            Taskukirja taskukirja=new Taskukirja(numero, osa[1]);
            //Log.d(LOG,taskukirja.toString());
            mTaskukirjaViewModel.insert(taskukirja);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
