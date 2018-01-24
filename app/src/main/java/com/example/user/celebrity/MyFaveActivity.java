package com.example.user.celebrity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyFaveActivity extends AppCompatActivity {
    private List<Person> PersonList;
    private RecyclerView recyclerView;
    DBAdapter myDb;
    private PersonAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fave);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Favorite Celebrity List");

        recyclerView = (RecyclerView) findViewById(R.id.myfave_recycler_view);

        openDB();


        Cursor cursor = myDb.getAllRows();


        PersonList = new ArrayList<>();

        PersonList = getMyFavoritRecordSet(cursor);

        mAdapter = new PersonAdapter(this,PersonList );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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




    private ArrayList<Person> getMyFavoritRecordSet(Cursor cursor) {

        ArrayList<Person> myFavoriteelebrityList = new ArrayList<Person>();

        String message = "";
        // populate the message from the cursor

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                int like  = cursor.getInt(DBAdapter.COL_LIKE);

                if (like == 1)

                {  // Process the data:
                    int id = cursor.getInt(DBAdapter.COL_ROWID);
                    String name = cursor.getString(DBAdapter.COL_NAME);
                    String title = cursor.getString(DBAdapter.COL_TITLE);
                    int image = cursor.getInt(DBAdapter.COL_IMAGE);


                    Person person = new  Person(id, name,title,image, like);

                    myFavoriteelebrityList.add( person);

                }


            } while (cursor.moveToNext());
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();


        return myFavoriteelebrityList;
    }




}
