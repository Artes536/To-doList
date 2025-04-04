package com.example.to_dolist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.R;
import com.example.to_dolist.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private OnTaskCheckedChangeListener listener;
    private OnTaskDeleteListener deleteListener;

    public TaskAdapter(List<Task> taskList,
                       OnTaskCheckedChangeListener listener,
                       OnTaskDeleteListener deleteListener) {
        this.taskList = taskList;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskText.setText(task.getText());
        holder.taskCheckbox.setOnCheckedChangeListener(null); // Убираем обработчик перед установкой состояния
        holder.taskCheckbox.setChecked(task.getComplete());

        // Обработчик изменения состояния чекбокса
        holder.taskCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            listener.onTaskCheckedChanged(task, isChecked); // Это только для изменения состояния, не удаляет задачу
        });

        // Обработчик нажатия на кнопку удаления (корзину)
        holder.deleteButton.setOnClickListener(v -> {
            deleteListener.onTaskDeleted(task); // Удаление при нажатии на кнопку корзины
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox taskCheckbox;
        TextView taskText;
        ImageButton deleteButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskCheckbox = itemView.findViewById(R.id.task_checkbox);
            taskText = itemView.findViewById(R.id.task_text);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnTaskCheckedChangeListener {
        void onTaskCheckedChanged(Task task, boolean isChecked);
    }
    public interface OnTaskDeleteListener {
        void onTaskDeleted(Task task);
    }
}

