package com.example.dean.sqlitetutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dean.sqlitetutorial.database.DBOpenHelper;
import com.example.dean.sqlitetutorial.database.DatabaseHandler;
import com.example.dean.sqlitetutorial.person.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private DatabaseHandler mDatabaseHandler;
    private List<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHandler = new DatabaseHandler(getApplicationContext());
        initListView();

    }
    private void initListView(){
        people = mDatabaseHandler.getAllPeople();
        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_text_view,
                people);
        setListAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ((ArrayAdapter) l.getAdapter()).notifyDataSetChanged();
        Person person = people.get(position);
        Intent intent = new Intent(this, ViewSinglePerson.class);
        intent.putExtra("PERSON_NAME", person.getName());
        intent.putExtra("PERSON_MAIL", person.getEmail());
        intent.putExtra("PERSON_NUMBER", person.getPhoneNumber());
        intent.putExtra("PERSON_ID", person.getID());
        startActivityForResult(intent, 1);
    }

    public void onAddClicked(View view){
        Intent intent = new Intent(this, AddPersonActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        

        if(resultCode == 1){
            initListView();
        }
    }
}
