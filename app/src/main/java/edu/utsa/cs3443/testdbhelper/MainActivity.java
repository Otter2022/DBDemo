package edu.utsa.cs3443.testdbhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.utsa.cs3443.testdbhelper.Model.CustomerModel;
import edu.utsa.cs3443.testdbhelper.Model.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    Button btn_viewAll, btn_add;
    Switch sw_active;
    ListView lv_customerList;
    TextView et_name, et_age;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_viewAll = findViewById(R.id.btn_viewall);
        btn_add = findViewById(R.id.btn_add);
        sw_active = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerlist);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel = null;
                try {
                    customerModel = new CustomerModel(et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), -1, sw_active.isChecked());
                    if (dataBaseHelper.addOne(customerModel)) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        List<CustomerModel> everyone = dataBaseHelper.getEveryone();
                        ArrayAdapter<CustomerModel> customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                        lv_customerList.setAdapter(customerArrayAdapter);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Check Input", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<CustomerModel> everyone = dataBaseHelper.getEveryone();

                ArrayAdapter<CustomerModel> customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv_customerList.setAdapter(customerArrayAdapter);

            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                List<CustomerModel> everyone = dataBaseHelper.getEveryone();
                ArrayAdapter<CustomerModel> customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv_customerList.setAdapter(customerArrayAdapter);
                Toast.makeText(MainActivity.this, "Deleted " + clickedCustomer, Toast.LENGTH_SHORT).show();
            }
        });

    }
}