package com.example.islam360;

public class SuratView {
    private int id;
    private String surat;
    private String numayat;

    public SuratView(int id, String surat, String numayat) {
        this.id = id;
        this.surat = surat;
        this.numayat = numayat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
    }

    public String getNumayat() {
        return numayat;
    }

    public void setNumayat(String numayat) {
        this.numayat = numayat;
    }
}