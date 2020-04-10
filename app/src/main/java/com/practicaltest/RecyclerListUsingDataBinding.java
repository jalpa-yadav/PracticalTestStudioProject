package com.practicaltest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.RecyclerAdapterDataBinding;
import com.pojo.Employee;

import java.util.ArrayList;


public class RecyclerListUsingDataBinding extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Employee> userList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("asdas","asd");
        setContentView(R.layout.activity_recycler_data_binding);
        for (int i = 0; i < 5; i++) {
            Employee emp = new Employee();
            emp.setEmail("aaa"+i+"@gmail.com");
            emp.setName("name"+i);
            userList.add(emp);
        }
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerAdapterDataBinding(userList) {
            @Override
            protected Object getObjForPosition(int position) {
                return userList.get(position);
            }

            @Override
            protected int getLayoutIdForPosition(int position) {
                    return R.layout.user_list_item_data_binding;
            }
        };
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    private Context getContext() {
        return  this;
    }
}
