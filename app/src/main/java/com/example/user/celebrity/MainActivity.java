package com.example.user.celebrity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStartDragListener{



    private List<Person> PersonList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;
    ItemTouchHelper mItemTouchHelper;

    DBAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        openDB();
        populateMyDB();
        Cursor cursor = myDb.getAllRows();




        PersonList = getCelebrityRecordSet(cursor);

        mAdapter = new PersonAdapter(this,PersonList ,  this);


        ItemTouchHelper.Callback callback =
                new EditItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.myFave:
                Toast.makeText(this, "My Fave Clicked", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this,MyFaveActivity.class);
                startActivity(intent);


                return true;
            case R.id.help:
              
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void closeDB() {
        myDb.close();
    }


    private ArrayList<Person> getCelebrityRecordSet(Cursor cursor) {

        ArrayList<Person> PersonList = new ArrayList<Person>();

        String message = "";
        // populate the message from the cursor

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                // Process the data:
                int id = cursor.getInt(DBAdapter.COL_ROWID);
                String name = cursor.getString(DBAdapter.COL_NAME);
                String title = cursor.getString(DBAdapter.COL_TITLE);
                int image = cursor.getInt(DBAdapter.COL_IMAGE);
                int like  = cursor.getInt(DBAdapter.COL_LIKE);


              Person person = new  Person(id, name, title, image, like);

                 PersonList.add(person);


            } while (cursor.moveToNext());
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();


        return  PersonList;
    }




    private void populateMyDB() {


        myDb.deleteAll();

        myDb.insertRow("Adam Sandler", "American actor, comedian, screenwriter, film producer, and musician", R.drawable.adam_sandler,0);


        myDb.insertRow("Beyonce ", "American singer, songwriter, dancer, and actress.",R.drawable.beyonce,0);


        myDb.insertRow("Brintney Spears", "American singer, songwriter, dancer, and actress",R.drawable.britney_spears,0);


        myDb.insertRow("Oprah Winfrey", "Talk show host, actress, producer, and philanthropist", R.drawable.oprah,0);

        myDb.insertRow("David Beckham", "English former professional footballer", R.drawable.david_beckham,0);


        myDb.insertRow("Drake", " Canadian rapper, singer, songwriter, record producer, actor", R.drawable.drake,0);


        myDb.insertRow("Dwayne Johnson", " American actor, producer, and semi-retired professional wrestler.", R.drawable.dwayne,0);

        myDb.insertRow("Halle Berry", "American actress", R.drawable.halle_berry,0);

        myDb.insertRow("Jay Z", "American rapper and businessman..", R.drawable.jayz,0);


        myDb.insertRow("Rihanna", "Barbadian singer, songwriter, and actress.", R.drawable.rihanna,0);


        myDb.insertRow("Sean Combs", "American rapper, singer, songwriter, actor, record producer", R.drawable.sean_combs,0);

        myDb.insertRow("Tupac Shakur", "American rapper and actor", R.drawable.tupac,0);


        myDb.insertRow("Ellen DeGeneres", "Comedian, television host, actress, writer, and producer.",R.drawable.ellen_degeneres,0 );



    }




    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}