package com.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.pojo.Employee;
import com.practicaltest.R;

import java.util.ArrayList;

public abstract class RecyclerAdapterDataBinding extends RecyclerView.Adapter<RecyclerAdapterDataBinding.MyViewHolder> {
    private ArrayList<Employee> listUserData;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(com.practicaltest.BR.obj, obj);
            binding.executePendingBindings();
        }
    }

    public RecyclerAdapterDataBinding() {
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapterDataBinding(ArrayList<Employee> userList) {
        listUserData = userList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapterDataBinding.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.user_list_item_data_binding, parent, false);
        return new MyViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Object obj = getObjForPosition(position);
        holder.bind(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listUserData.size();
    }

}
