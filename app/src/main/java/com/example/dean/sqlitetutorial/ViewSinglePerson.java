package com.example.dean.sqlitetutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dean.sqlitetutorial.database.DatabaseHandler;
import com.example.dean.sqlitetutorial.person.Person;

public class ViewSinglePerson extends AppCompatActivity {

    private static Person personToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_person);


        personToDisplay = new Person();
        Intent intent = getIntent();
        personToDisplay.setID(intent.getIntExtra("PERSON_ID", -1));
        personToDisplay.setName(intent.getStringExtra("PERSON_NAME"));
        personToDisplay.setEmail(intent.getStringExtra("PERSON_MAIL"));
        personToDisplay.setPhoneNumber(intent.getStringExtra("PERSON_NUMBER"));
        initFields();
    }

    private void initFields(){
        TextView nameField = (TextView)findViewById(R.id.display_name_field);
        TextView numberField = (TextView)findViewById(R.id.display_phone_field);
        TextView mailField = (TextView)findViewById(R.id.display_mail_field);

        nameField.setText(personToDisplay.getName());
        mailField.setText(personToDisplay.getEmail());
        numberField.setText(personToDisplay.getPhoneNumber());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_single_person, menu);
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

    public void onRemoveClick(View view){
        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        databaseHandler.removePerson(personToDisplay.getID());
        setResult(1);
        finish();
    }
}
