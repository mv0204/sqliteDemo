package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitedemo.db.MyDbHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button insert, delete, view, update;
    TextView id, name, phone;
    ListView listView;
    ArrayList<DmModel> list;
    ArrayList<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert = findViewById(R.id.insertbtn);
        delete = findViewById(R.id.deletebtn);
        view = findViewById(R.id.viewbtn);
        update = findViewById(R.id.updatebtn);
        name = findViewById(R.id.editTextName);
        id = findViewById(R.id.editTextId);
        phone = findViewById(R.id.editTextPhoneNo);
        listView = findViewById(R.id.listview);


        MyDbHandler db = new MyDbHandler(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || id.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                else {
                    db.insertData(id.getText().toString(), name.getText().toString(), phone.getText().toString());
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }
                id.setText("");
                name.setText("");
                phone.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Id to be deleted", Toast.LENGTH_SHORT).show();
                } else {
                    db.deleteData(Integer.parseInt(id.getText().toString()));
                    Toast.makeText(MainActivity.this, "Successful...Click view to see updated data", Toast.LENGTH_SHORT).show();
                }
                id.setText("");
                name.setText("");
                phone.setText("");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts = new ArrayList<>();
                list = db.viewData();
                for (int i = 0; i < list.size(); i++)
                    contacts.add("Id :" + list.get(i).KEY_Id + "\n" + "Name:" + list.get(i).KEY_NAME + "(" + list.get(i).KEY_PHONE + ")");

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, contacts);
                listView.setAdapter(adapter);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DmModel model=new DmModel();
//                model.setKEY_Id(5);
//                model.setKEY_NAME("pyli mma");
//                model.setKEY_PHONE("9466260531");
                db.updateData(id.getText().toString(), name.getText().toString(), phone.getText().toString());
            }
        });

//        db.insertData("mohit","8295904531");
//        db.insertData("manisha","9350247596");
//        db.insertData("mma","9050466977");


//        DmModel model=new DmModel();
//        model.setKEY_Id(5);
//        model.setKEY_NAME("pyli mma");
//        model.setKEY_PHONE("9466260531");
//        db.updateData(model);


    }
}