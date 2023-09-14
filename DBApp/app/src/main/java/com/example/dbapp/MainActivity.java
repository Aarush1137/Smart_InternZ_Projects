package com.example.dbapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    EditText editRollNo, editName, editMarks;
    Button btnSubmit, btnUpdate, btnViewAll, btnView, btnDelete;

    // Database variables
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editRollNo = findViewById(R.id.editRollNo);
        editName = findViewById(R.id.editName);
        editMarks = findViewById(R.id.editMarks);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);

        // Create or open the database
        db = openOrCreateDatabase("StudentDatabase.db", Context.MODE_PRIVATE, null);

        // Create the student table (if not exists)
        db.execSQL("CREATE TABLE IF NOT EXISTS student (_id INTEGER PRIMARY KEY AUTOINCREMENT, roll_no TEXT, name TEXT, marks TEXT)");

        // Set click listeners for buttons

        // Submit Button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from EditText fields
                String rollNo = editRollNo.getText().toString();
                String name = editName.getText().toString();
                String marks = editMarks.getText().toString();

                // Insert data into the database
                ContentValues values = new ContentValues();
                values.put("roll_no", rollNo);
                values.put("name", name);
                values.put("marks", marks);

                long rowId = db.insert("student", null, values);
                if (rowId != -1) {
                    Toast.makeText(MainActivity.this, "Student added!", Toast.LENGTH_SHORT).show();
                    // Clear EditText fields
                    editRollNo.setText("");
                    editName.setText("");
                    editMarks.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Error adding student", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from EditText fields
                String rollNo = editRollNo.getText().toString();
                String name = editName.getText().toString();
                String marks = editMarks.getText().toString();

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("marks", marks);

                int updatedRows = db.update("student", values, "roll_no=?", new String[]{rollNo});
                if (updatedRows > 0) {
                    Toast.makeText(MainActivity.this, "Student updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Student not found for updating", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View All Button
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.query("student", null, null, null, null, null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No students found", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder allStudents = new StringBuilder();
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String rollNo = cursor.getString(cursor.getColumnIndex("roll_no"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String marks = cursor.getString(cursor.getColumnIndex("marks"));

                    allStudents.append("Roll No: ").append(rollNo).append("\n");
                    allStudents.append("Name: ").append(name).append("\n");
                    allStudents.append("Marks: ").append(marks).append("\n\n");
                }

                // Display all student details
                TextView textStudentDetails = findViewById(R.id.textStudentDetails);
                textStudentDetails.setText(allStudents.toString());
            }
        });

        // View Button
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from EditText fields
                String rollNo = editRollNo.getText().toString();

                Cursor cursor = db.query("student", null, "roll_no=?", new String[]{rollNo}, null, null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Student not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                cursor.moveToFirst();
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String marks = cursor.getString(cursor.getColumnIndex("marks"));

                // Display the student details
                TextView textStudentDetails = findViewById(R.id.textStudentDetails);
                textStudentDetails.setText("Name: " + name + "\nMarks: " + marks);
            }
        });

        // Delete Button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from EditText fields
                String rollNo = editRollNo.getText().toString();

                int deletedRows = db.delete("student", "roll_no=?", new String[]{rollNo});
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Student deleted!", Toast.LENGTH_SHORT).show();
                    // Clear EditText fields
                    editRollNo.setText("");
                    editName.setText("");
                    editMarks.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Student not found for deletion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
