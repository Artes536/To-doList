package com.example.to_dolist;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.data.Task;
import com.example.to_dolist.ui.TaskAdapter;
import com.example.to_dolist.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    private TaskAdapter uncompletedTaskAdapter;
    private TaskAdapter completedTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerViewUncompleted = findViewById(R.id.uncompletedTasks);
        RecyclerView recyclerViewCompleted = findViewById(R.id.completedTasks);

        recyclerViewUncompleted.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCompleted.setLayoutManager(new LinearLayoutManager(this));

        uncompletedTaskAdapter = new TaskAdapter(new ArrayList<>(), (task, isChecked) -> {
            task.setComplete(isChecked);
            taskViewModel.update(task);
        });

        completedTaskAdapter = new TaskAdapter(new ArrayList<>(), (task, isChecked) -> {
            task.setComplete(isChecked);
            taskViewModel.update(task);
        });

        recyclerViewUncompleted.setAdapter(uncompletedTaskAdapter);
        recyclerViewCompleted.setAdapter(completedTaskAdapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Загружаем список незавершённых задач
        taskViewModel.getUncompletedTasks().observe(this, tasks -> uncompletedTaskAdapter.setTasks(tasks));

        // Загружаем список завершённых задач
        taskViewModel.getCompletedTasks().observe(this, tasks -> completedTaskAdapter.setTasks(tasks));

        FloatingActionButton fabAddTask = findViewById(R.id.floating_add_task_button);
        fabAddTask.setOnClickListener(view -> showAddTaskDialog());
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить задачу");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Добавить", (dialog, which) -> {
            String taskText = input.getText().toString().trim();
            if (!taskText.isEmpty()) {
                Task newTask = new Task(taskText); // Создаём новую задачу (по умолчанию невыполненная)
                taskViewModel.insert(newTask);
            }
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}

