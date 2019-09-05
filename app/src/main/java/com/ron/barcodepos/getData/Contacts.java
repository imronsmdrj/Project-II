package com.ron.barcodepos.getData;

import com.google.gson.annotations.SerializedName;

public class Contacts {

    String value;
    String message;

    @SerializedName("bid") private String Bid;
    @SerializedName("bnama") private String Bnama;
    @SerializedName("bsatuan") private String Bsatuan;
    @SerializedName("bharga_pokok") private String Bharga_pokok;
    @SerializedName("bharga_jual") private String Bharga_jual;
    @SerializedName("bharga_jual_grosir") private String Bharga_jual_grosir;
    @SerializedName("bstok") private String Bstok;
    @SerializedName("btgl_masuk") private String Btgl_masuk;
    @SerializedName("bkategori_id") private String Bkategori_id;

    public String getBid() {
        return Bid;
    }

    public String getBnama() {
        return Bnama;
    }

    public String getBsatuan() {
        return Bsatuan;
    }

    public String getBharga_pokok() {
        return Bharga_pokok;
    }

    public String getBharga_jual() {
        return Bharga_jual;
    }

    public String getBharga_jual_grosir() {
        return Bharga_jual_grosir;
    }

    public String getBstok() {
        return Bstok;
    }

    public String getBtgl_masuk() {
        return Btgl_masuk;
    }

    public String getBkategori_id() {
        return Bkategori_id;
    }

    public String getValue(){
        return value;
    }

    public String getMessage(){
        return message;
    }
}
