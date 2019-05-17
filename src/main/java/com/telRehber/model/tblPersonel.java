package com.telRehber.model;



import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="tblPersonel")
public class tblPersonel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id", nullable = false)
    private int id;

    @Column(name="adi", nullable = true)
    private String adi;

    @Column(name="soyadi", nullable = true)
    private String soyadi;

    @ManyToOne
    @JoinColumn(name="unvani",referencedColumnName = "id", nullable = true)
    private tblUnvan unvani;

    @ManyToOne
    @JoinColumn(name="birimi",referencedColumnName = "id", nullable = true)
    private tblBirim birimi;

    @Column(name="telefonNo", nullable = true)
    private String telefonNo;

    @ManyToOne
    @JoinColumn(name="kaydedenId",referencedColumnName = "id", nullable = true)
    private tblLogin kaydedenId;


    public tblPersonel()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public tblUnvan getUnvani() {
        return unvani;
    }

    public void setUnvani(tblUnvan unvani) {
        this.unvani = unvani;
    }

    public tblBirim getBirimi() {
        return birimi;
    }

    public void setBirimi(tblBirim birimi) {
        this.birimi = birimi;
    }

    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }

    public tblLogin getKaydedenId() {
        return kaydedenId;
    }

    public void setKaydedenId(tblLogin kaydedenId) {
        this.kaydedenId = kaydedenId;
    }

}
