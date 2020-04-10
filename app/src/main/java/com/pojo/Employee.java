package com.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Employee  extends BaseObservable {
    String name;
    String email;
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.practicaltest.BR.name);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(com.practicaltest.BR.email);
    }
}
