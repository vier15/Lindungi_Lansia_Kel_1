package com.kel1.lindungilansia;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    public MutableLiveData<String> nama;
    public MutableLiveData<String> telepon;
    public MutableLiveData<String> email;
    public MutableLiveData<String> tanggalLahir;

    // constructor
    public UserViewModel() {
        this.nama = new MutableLiveData<String>();
        this.telepon = new MutableLiveData<String>();
        this.email = new MutableLiveData<String>();
        this.tanggalLahir = new MutableLiveData<String>();
        this.nama.setValue("default");
        this.telepon.setValue("default");
        this.email.setValue("default");
        this.tanggalLahir.setValue("default");
    }

    public void setNama(String nama) {
        this.nama.setValue(nama);
    }

    public MutableLiveData<String> getNama(){ return this.nama; }

    public void setTelepon(String telepon) {
        this.nama.setValue(telepon);
    }

    public MutableLiveData<String> getTelepon(){ return this.telepon; }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public MutableLiveData<String> getEmail(){ return this.email; }

    public void setTanggallahir(String tanggalLahir) {
        this.nama.setValue(tanggalLahir);
    }

    public MutableLiveData<String> getTanggalLahir(){ return this.tanggalLahir; }

}
