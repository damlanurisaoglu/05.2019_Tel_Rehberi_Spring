package com.telRehber.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tblLogin")
public class tblLogin implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name="kullaniciAdi", nullable = true)
    private String kullaniciAdi;


    @Column(name="sifre", nullable = true)
    private String sifre;


    public tblLogin()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

}
