package com.telRehber.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tblUnvan")
public class tblUnvan implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "unvanAdi",nullable = true)
    private String unvanAdi;


    @OneToMany(mappedBy = "unvani", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<tblPersonel> personelList;

    public tblUnvan()
    {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnvanAdi() {
        return unvanAdi;
    }

    public void setUnvanAdi(String unvanAdi) {
        this.unvanAdi = unvanAdi;
    }

}
