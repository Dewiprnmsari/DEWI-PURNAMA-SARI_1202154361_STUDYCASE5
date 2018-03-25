package com.example.android.dewipurnamasari_1202154361_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Database dB;
    private RecyclerView rV;
    private Adapter adapter;
    private ArrayList<AddData> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("To Do List App");

        //merefer variabel yang ada
        rV = findViewById(R.id.rec_view); //mengakses recyclerview
        data_list = new ArrayList<>(); //membuat arraylist baru
        dB = new Database(this); //membuat database baru
        dB.readdata(data_list); //memanggil method readdata

        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        adapter = new Adapter(this,data_list, color);
        rV.setHasFixedSize(true); //mengatur ukuran
        rV.setLayoutManager(new LinearLayoutManager(this));
        rV.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        swipe();
    }

    //membuat method untuk menghapus item pada todolist
    public void swipe(){
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //apabila item dihapus(swipe) ke kiri
                if(direction == ItemTouchHelper.LEFT){
                    if (dB.removedata(current.getTodo())){
                        adapter.deleteData(position);
                        Snackbar.make(findViewById(R.id.coordinator), "List Telah dihapus", 3000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recyclerview
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rV);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang dijalankan ketika item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //penganturan item setting
        if (id == R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    //method yang dijalankan ketika mengklik tombol add
    public void addlist(View view){
        Intent intent = new Intent(MainActivity.this, AddToDo.class);
        startActivity(intent);
    }
}
