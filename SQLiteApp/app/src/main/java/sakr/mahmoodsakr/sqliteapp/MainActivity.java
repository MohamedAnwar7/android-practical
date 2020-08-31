package sakr.mahmoodsakr.sqliteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DB dbobject;
    EditText name, email, id;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbobject = new DB(this); // this declare only the database details and doesn't create the db
        // until executing any of the getwritable/readable method which call the life cycle of helper class
        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        id = (EditText) findViewById(R.id.editText_id);
        listView = (ListView) findViewById(R.id.listViewId);
        showData();
    }

    public void addData(View view) {


        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            Toast.makeText(this, "EditText fields are Empty !", Toast.LENGTH_SHORT).show();

        } else {
            String Name = name.getText().toString();
            String Email = email.getText().toString();
            boolean status = dbobject.insertDate(Name, Email);
            if (status == true) {
                Toast.makeText(this, "Adding data is Done Successfully .", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                id.setText("");
                showData();
            } else {
                Toast.makeText(this, "Adding data is failed ! ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showData() {
        ArrayList arrayList = dbobject.getAllData();
        ArrayAdapter arrayAdapter = null;
        if (arrayList.isEmpty()) {
            Toast.makeText(this, "Database is Empty !", Toast.LENGTH_SHORT).show();
            // the two line make the listview empty due to the empty arraylist make the adapter take no value and set listview as empty listview
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(arrayAdapter);
        } else {
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(arrayAdapter);
            Toast.makeText(this, "Refresh Database .", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view) {
        if (id.getText().toString().isEmpty() || name.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            Toast.makeText(this, "EditText fields are Empty !", Toast.LENGTH_SHORT).show();

        } else {
            boolean status = dbobject.updateDate(id.getText().toString(), name.getText().toString(), email.getText().toString());
            if (status == true) {
                name.setText("");
                email.setText("");
                id.setText("");
                showData();
                Toast.makeText(this, " Update data is Done successfully .", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, " Update data is Failed !", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void removeData(View view) {

        if (id.getText().toString().isEmpty()) {
            Toast.makeText(this, "ID field is Empty !", Toast.LENGTH_SHORT).show();

        } else {
            boolean status = dbobject.removeData(id.getText().toString());
            if (status == true) {
                Toast.makeText(this, " Delete done successfully", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                id.setText("");
                showData();
            } else {
                Toast.makeText(this, " Not Founded in Database ", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
            }
        }
    }

    public void searchByName(View view) {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name field is Empty !", Toast.LENGTH_SHORT).show();
        } else {
            boolean status = dbobject.searchByName(name.getText().toString());
            if (status == true) {
                Toast.makeText(this, name.getText().toString() + " is Founded .", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Not Founded !", Toast.LENGTH_SHORT).show();
            }
        }
        name.setText("");
        email.setText("");
        id.setText("");

    }
}
