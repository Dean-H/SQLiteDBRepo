package com.example.dean.sqlitetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.dean.sqlitetutorial.database.DatabaseHandler;
import com.example.dean.sqlitetutorial.person.Person;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_person, menu);
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

    public void onClick(View view){
        EditText nameField = (EditText)findViewById(R.id.enter_name_field);
        EditText numberField = (EditText)findViewById(R.id.enter_phone_field);
        EditText emailField = (EditText)findViewById(R.id.enter_email_field);

        Person person = new Person();
        person.setName(nameField.getText().toString());
        person.setPhoneNumber(numberField.getText().toString());
        person.setEmail(emailField.getText().toString());

        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        databaseHandler.addPerson(person);
        setResult(1);
        finish();
    }
}
