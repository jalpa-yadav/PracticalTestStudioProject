package com.practicaltest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.practicaltest.databinding.ActivityDataBindingActivityBinding;
import com.pojo.Employee;

public class DataBindingActivity extends AppCompatActivity {
private Employee emp;
private Button btnChangeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_activity);
        ActivityDataBindingActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_activity);

        emp = new Employee();
        emp.setName("Parag");
        emp.setEmail("Parag@gmail.info");

        binding.setEmp(emp);

        btnChangeData = findViewById(R.id.btnChangeData);
        btnChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setName("Jalpa");
                emp.setEmail("jalpa@gmail.com");
            }
        });
    }
}
