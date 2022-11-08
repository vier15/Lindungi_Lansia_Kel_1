package com.kel1.lindungilansia;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> email;
    private MutableLiveData<String> nama;

    // constructor
    public UserViewModel() {
        this.email = new MutableLiveData<String>();
        this.nama = new MutableLiveData<String>();
        this.email.setValue("default");
        this.nama.setValue("default");
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setNama(String nama) {
        this.nama.setValue(nama);
    }
}
