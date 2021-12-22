package com.example.SimOnline;

import android.os.Parcel;
import android.os.Parcelable;

public class MhsModel implements Parcelable {

    //    int id ;
    String key;
    String nama ;
    String alamat ;
    String nohp ;
    String tgllahir ;

    public MhsModel(String key, String nama, String alamat, String nohp, String tgllahir) {
//        this.id = id;
        this.key = key;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.tgllahir = tgllahir;
    }
    public MhsModel(String nama, String alamat, String nohp, String tgllahir) {
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.tgllahir = tgllahir;
    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() { return alamat;    }

    public void setAlamat(String kodebarang) {
        this.alamat = kodebarang;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String stokbarang) {this.nohp = stokbarang;    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String stokbarang) {this.tgllahir = stokbarang;    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.nohp);
        dest.writeString(this.tgllahir);
    }

    public void readFromParcel(Parcel source) {
        this.key = source.readString();
        this.nama = source.readString();
        this.alamat = source.readString();
        this.nohp = source.readString();
        this.tgllahir = source.readString();
    }

    protected MhsModel(Parcel in) {
        this.key = in.readString();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.nohp = in.readString();
        this.tgllahir = in.readString();
    }

    public static final Creator<MhsModel> CREATOR = new Creator<MhsModel>() {
        @Override
        public MhsModel createFromParcel(Parcel source) {
            return new MhsModel(source);
        }

        @Override
        public MhsModel[] newArray(int size) {
            return new MhsModel[size];
        }
    };
}
