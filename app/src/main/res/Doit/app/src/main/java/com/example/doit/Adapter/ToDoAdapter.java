package com.example.doit.Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.MainActivity;
import com.example.doit.Model.ToDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
   private List<ToDoModel> toDoList;
   private MainActivity activity;
    private List<ToDoModel> todoList;

    public ToDoAdapter (MainActivity activity){
      this.activity = activity;
    }
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
      View iteView= LayoutInflater.from(parent.getContext())
              .inflate(R.layout.task_layout,parent, false);
      return new ViewHolder(iteView);
   }

   public  void onBindViewHolder(ViewHolder holder, int posiiton){
        ToDoModel item = toDoList.get(posiiton);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
   }

    public int getItemCount() {
        return toDoList.size();
    }
   private boolean toBoolean(int n){
        return  n!=0;
   }

   public  void setTasks(List<ToDoModel>toDoList){
        this.todoList=toDoList;
        notifyDataSetChanged();
   }
   public static class  ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task= view.findViewById(R.id.todoCheckBox);
        }

   }
}
