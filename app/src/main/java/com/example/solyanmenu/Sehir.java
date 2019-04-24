package com.example.solyanmenu;

public class Sehir {

    private int resimId;
    private String sehirAdi;
    private String sehirAciklama;

    public Sehir(int resimId, String sehirAdi, String sehirAciklama) {
        this.resimId = resimId;
        this.sehirAdi = sehirAdi;
        this.sehirAciklama = sehirAciklama;
    }

    public int getResimId() {
        return resimId;
    }

    public void setResimId(int resimId) {
        this.resimId = resimId;
    }

    public String getSehirAdi() {
        return sehirAdi;
    }

    public void setSehirAdi(String sehirAdi) {
        this.sehirAdi = sehirAdi;
    }

    public String getSehirAciklama() {
        return sehirAciklama;
    }

    public void setSehirAciklama(String sehirAciklama) {
        this.sehirAciklama = sehirAciklama;
    }
}
