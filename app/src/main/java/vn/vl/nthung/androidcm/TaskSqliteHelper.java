package vn.vl.nthung.androidcm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskSqliteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "task.db";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TaskEntry.COLUMN_NAME + " (" +
            TaskEntry._ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," +
            TaskEntry.COLUMN_NAME + " TEXT)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

    public TaskSqliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public long addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskEntry.COLUMN_NAME, task.getName());
        return db.insert(TaskEntry.TABLE_NAME, null, values);
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        String where = TaskEntry.COLUMN_NAME + " LIKE ?";
        String[] whereArgs = {task.getName()};
        db.delete(TaskEntry.TABLE_NAME, where, whereArgs);
    }

    public List<Task> getTasks() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {TaskEntry._ID, TaskEntry.COLUMN_NAME};
        String orderBy = TaskEntry._ID + " ASC";
        Cursor cursor = db.query(TaskEntry.TABLE_NAME, columns, null, null, null, null, orderBy);
        List<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TaskEntry._ID));
            String name = cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME));
            Task task = new Task(id, name);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME = "name";
    }
}
