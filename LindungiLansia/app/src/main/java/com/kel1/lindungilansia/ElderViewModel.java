package com.kel1.lindungilansia;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ElderViewModel extends ViewModel {

    public MutableLiveData<String> nama;
    public MutableLiveData<String> telepon;
    public MutableLiveData<String> email;
    public MutableLiveData<String> tanggalLahir;
    public MutableLiveData<String> alamat;
    public MutableLiveData<String> gol_darah;
    public MutableLiveData<String> berat_badan;
    public MutableLiveData<String> alergi;
    public MutableLiveData<Double> currentLatitude;
    public MutableLiveData<Double> currentLongitude;

    public ElderViewModel() {
        this.nama = new MutableLiveData<String>();
        this.telepon = new MutableLiveData<String>();
        this.email = new MutableLiveData<String>();
        this.tanggalLahir = new MutableLiveData<String>();
        this.currentLatitude = new MutableLiveData<Double>();
        this.currentLongitude = new MutableLiveData<Double>();
        this.alamat = new MutableLiveData<String>();
        this.gol_darah = new MutableLiveData<String>();
        this.berat_badan = new MutableLiveData<String>();
        this.alergi = new MutableLiveData<String>();
        this.nama.setValue("default");
        this.telepon.setValue("default");
        this.email.setValue("default");
        this.tanggalLahir.setValue("default");
    }



    public void setNama(String nama) {
        this.nama.setValue(nama);
    }


    public void setTelepon(String telepon) {
        this.telepon.setValue(telepon);
    }


    public void setEmail(String email) {
        this.email.setValue(email);
    }


    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir.setValue(tanggalLahir);
    }

    public void setCurrentLatitude(Double currentLatitude) {this.currentLatitude.setValue(currentLatitude);}
    public MutableLiveData<Double> getCurrentLatitude() { return this.currentLatitude;}

    public void setCurrentLongitude(Double currentLongitude) {this.currentLongitude.setValue(currentLongitude);}
    public MutableLiveData<Double> getCurrentLongitude() { return this.currentLongitude;}

    public void setAlamat(String alamat) {this.alamat.setValue(alamat);}
    public void setGol_darah(String gol_darah) {this.gol_darah.setValue(gol_darah);}
    public void setBerat_badan(String berat_badan) {this.berat_badan.setValue(berat_badan);}
    public void setAlergi(String alergi) {this.alergi.setValue(alergi);}

}

