package com.practicaltest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.RecyclerAdapter;
import com.database.AppDatabase;
import com.database.User;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class RecyclerListLocalDB extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> userList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerAdapter(userList);
        recyclerView.setAdapter(mAdapter);
        new loadData(RecyclerListLocalDB.this).execute();
    }


    private class loadData extends AsyncTask<Void, Void, Void> {
        private WeakReference<Activity> weakActivity;
        public loadData(Activity activity) {
            weakActivity = new WeakReference<>(activity);
        }
        @Override
        protected Void doInBackground(Void... params) {
            userList.addAll((ArrayList<User>) AppDatabase.getInstance(getContext()).userDao().getAll());
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return;
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private Context getContext() {
        return  this;
    }
}
