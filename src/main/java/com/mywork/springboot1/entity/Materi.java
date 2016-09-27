package com.mywork.springboot1.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by israj on 9/21/2016.
 */
@Entity @Table(name = "Materi")
public class Materi {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(nullable = false, unique = true)
    private String kode;

    @Column(nullable = false)
    private String nama;


    @OneToMany(
            cascade = CascadeType.ALL,orphanRemoval = true,
            mappedBy = "materi"
    )
    private List<Sesi> daftarSesi =  new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Sesi> getDaftarSesi() {
        return daftarSesi;
    }

    public void setDaftarSesi(List<Sesi> daftarSesi) {
        this.daftarSesi = daftarSesi;
    }
}
