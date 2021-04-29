package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskSqliteHelper dbTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbTask = new TaskSqliteHelper(this);

        Button addTaskBtn = findViewById(R.id.add_task_btn);
        addTaskBtn.setOnClickListener(this.onAddTask);

        Button deleteTaskBtn = findViewById(R.id.delete_task_btn);
        deleteTaskBtn.setOnClickListener(this.onDeleteTask);

        updateTaskList();
    }

    private View.OnClickListener onAddTask = (view) -> {
        TextView text = findViewById(R.id.task_name);
        Task task = new Task(text.getText().toString());
        dbTask.addTask(task);
        text.setText("");
        updateTaskList();
    };

    private View.OnClickListener onDeleteTask = (view) -> {
        TextView text = findViewById(R.id.task_name);
        Task task = new Task(text.getText().toString());
        dbTask.deleteTask(task);
        text.setText("");
        updateTaskList();
    };

    private void updateTaskList() {
        TextView text = findViewById(R.id.task_list);
        List<Task> tasks = dbTask.getTasks();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(tasks.get(i).getName() + "\n");
        }
        text.setText(builder.toString());
    }

    @Override
    protected void onDestroy() {
        dbTask.close();
        super.onDestroy();
    }
}