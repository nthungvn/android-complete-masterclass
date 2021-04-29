package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TaskSqliteHelper dbTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbTask = new TaskSqliteHelper(this);
    }

    @Override
    protected void onDestroy() {
        dbTask.close();
        super.onDestroy();
    }
}