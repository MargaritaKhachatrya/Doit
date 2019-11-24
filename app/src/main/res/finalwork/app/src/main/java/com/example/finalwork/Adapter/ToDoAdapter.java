package com.example.finalwork.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.finalwork.AddNewTask;
import com.example.finalwork.DatabaseHandler;


import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.MainActivity;
import com.example.finalwork.Model.ToDoModel;
import com.example.gafandfirebase.R;

import java.util.List;



public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;


    public ToDoAdapter(com.example.finalwork.Utils.DatabaseHandler db, MainActivity activity){
        this.db = this.db;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public  void onBindViewHolder(ViewHolder holder, int posiiton){
        db.openDatabase();
        ToDoModel item = todoList.get(posiiton);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    db.updateStatus(item.getId(), 1);
                }
                else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public int getItemCount() {
        return todoList.size();
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    public void  setTasks(List<ToDoModel>todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }


    public void  editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle =new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public  static  class ViewHolder extends  RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}

