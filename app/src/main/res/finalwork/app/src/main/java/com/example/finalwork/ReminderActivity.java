package com.example.finalwork;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.Adapter.LoginActivity;
import com.example.finalwork.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderResult;
import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {

    ImageButton menuBtn;
    FloatingActionButton mCreateRem;
    RecyclerView mRecyclerview;
    ArrayList<ToDoModel> dataholder = new ArrayList<ToDoModel>();                                               //Array list to add reminders and display in recyclerview
    MyAdapter adapter;
    ReminderAdapter reminderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        menuBtn.setOnClickListener((v)-> showMenu());
        setupRecyclerView();
        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReminderDetailsActivity.class);
                startActivity(intent);                                                              //Starts the new activity to add Reminders
            }
        });

        Cursor cursor = new dbManager(getApplicationContext()).readallreminders();                  //Cursor To Load data From the database
        while (cursor.moveToNext()) {
            ToDoModel model = new ToDoModel(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            dataholder.add(model);
        }
    }

    @Override
    public void onBackPressed() {
        finish();                                                                                   //Makes the user to exit from the app
        super.onBackPressed();

    }

    void setupRecyclerView() {
        Query query = Utility.getCollectionReferenceForReminders().orderBy("timestamp",Query.Direction.DESCENDING);
        CertPathBuilderResult options = (CertPathBuilderResult) new Builder<Task>();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(reminderAdapter);
    }

    void showMenu() {
        PopupMenu popupMenu = new PopupMenu(ReminderActivity.this, menuBtn);
        popupMenu.getMenu().add("My notes");
        popupMenu.getMenu().add("About us");
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (menuItem.getTitle() == "Logout") {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ReminderActivity.this, LoginActivity.class));
                    finish();
                    return true;

                } else if (menuItem.getTitle() == "My notes") {
                    startActivity(new Intent(ReminderActivity.this, MainActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reminderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        reminderAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reminderAdapter.notifyDataSetChanged();
    }

    private class Builder<T> {
        public CertPathBuilder setQuery(Query query, Class<T> taskClass) {

            return null;
        }
    }
}

