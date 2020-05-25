package com.example.shoppingnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private RecyclerView recycler;
    private RecyclerViewAdapter adapter;
    private List<Lists> savedLists;
    private BottomNavigationView bottom_navigation;
    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.action_myLists, new FragmentListAll()).commit();
        helper = new DatabaseHelper(this);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Listsdao dao = new Listsdao();
        savedLists = dao.allLists(helper);
        adapter = new RecyclerViewAdapter(this,savedLists, helper);
        recycler.setAdapter(adapter);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                
                if(item.getItemId() == R.id.action_myLists){

                }
                if(item.getItemId() == R.id.action_addList){
                    addList();
                }
                return true;
            }
        });

    }

    public void addList(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.add_list_layout, null);

        final EditText newListName = view.findViewById(R.id.newListName);

        AlertDialog.Builder newList = new AlertDialog.Builder(this);
        newList.setTitle("Yeni Liste Oluştur");
        newList.setView(view);
        newList.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Listsdao().addList(helper, newListName.getText().toString());
                savedLists = new Listsdao().allLists(helper);
                adapter = new RecyclerViewAdapter(MainActivity.this, savedLists, helper);
                recycler.setAdapter(adapter);
            }
        });

        newList.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "iptal", Toast.LENGTH_SHORT).show();
            }
        });

        newList.create().show();
    }

}
