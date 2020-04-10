package com.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.practicaltest.R;

import java.util.ArrayList;

public class VolleyRecyclerAdapter extends RecyclerView.Adapter<VolleyRecyclerAdapter.MyViewHolder> {
    private ArrayList<String> listUserData;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;

        public MyViewHolder(View v) {
            super(v);
            tvName = itemView.findViewById(R.id.tvName);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VolleyRecyclerAdapter(ArrayList<String> userList) {
        listUserData = userList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VolleyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.volly_user_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvName.setText(listUserData.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listUserData.size();
    }

}
