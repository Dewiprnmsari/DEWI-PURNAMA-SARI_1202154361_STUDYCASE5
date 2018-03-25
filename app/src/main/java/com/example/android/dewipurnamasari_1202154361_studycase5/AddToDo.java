package com.example.android.dewipurnamasari_1202154361_studycase5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {

    private EditText ToDo, Description, Priority;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //mengatur title menjadi set to do
        setTitle("Add To Do");

       //merefer semua variabel yang ada
       ToDo = (EditText) findViewById(R.id.et_Todo);
       Description = (EditText) findViewById(R.id.et_Desc);
       Priority = (EditText) findViewById(R.id.et_Priority);
       db = new Database(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddToDo.this, MainActivity.class);
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    public void addTodo(View view){
        //menjalankan ketika todolist ditambahkan
        if (db.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //menampilkan toast bawha data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List Ditambahkan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddToDo.this, MainActivity.class));
            this.finish();
        }else {
            //menampilkan toast list tidak boleh kosong
            Toast.makeText(this, "List tidak boleh kosong", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}
