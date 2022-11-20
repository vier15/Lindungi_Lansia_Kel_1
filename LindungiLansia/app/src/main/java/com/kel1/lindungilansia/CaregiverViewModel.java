package com.kel1.lindungilansia;

import androidx.lifecycle.ViewModel;

public class CaregiverViewModel extends ViewModel {

    private String nama;
    private String telepon;
    private String email;
    private String tanggalLahir;

    public CaregiverViewModel() {
        this.nama = "default";
        this.telepon = "default";
        this.email = "default";
        this.tanggalLahir = "default";
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return this.telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
