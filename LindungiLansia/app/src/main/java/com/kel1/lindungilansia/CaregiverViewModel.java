package com.kel1.lindungilansia;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CaregiverViewModel extends ViewModel {

    public MutableLiveData<String> nama;
    public MutableLiveData<String> telepon;
    public MutableLiveData<String> email;
    public MutableLiveData<String> tanggalLahir;

    public CaregiverViewModel() {
        this.nama = new MutableLiveData<String>();
        this.telepon = new MutableLiveData<String>();
        this.email = new MutableLiveData<String>();
        this.tanggalLahir = new MutableLiveData<String>();
        this.nama.setValue("default");
        this.telepon.setValue("default");
        this.email.setValue("default");
        this.tanggalLahir.setValue("default");
    }

//    public String getNama() {
//        return this.nama;
//    }

    public void setNama(String nama) {
        this.nama.setValue(nama);
    }

//    public String getTelepon() {
//        return this.telepon;
//    }

    public void setTelepon(String telepon) {
        this.telepon.setValue(telepon);
    }

//    public String getEmail() {
//        return this.email;
//    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

//    public String getTanggalLahir() {
//        return this.tanggalLahir;
//    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir.setValue(tanggalLahir);
    }
}
