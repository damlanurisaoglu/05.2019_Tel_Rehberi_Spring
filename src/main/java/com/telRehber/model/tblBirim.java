package com.telRehber.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tblBirim")
public class tblBirim implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "birimAdi",nullable = true)
    private String birimAdi;

   @OneToMany(mappedBy = "birimi", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<tblPersonel> personelList;

    public tblBirim()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

}
