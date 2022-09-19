package com.example.demo.Jezik;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Jezik {
    @Id
    @SequenceGenerator(
    name = "jezik_sequence",
    sequenceName = "jezik_sequence",
    allocationSize = 1
    )
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "jezik_sequence" )
    private long id;
    private String ime;
    private String sporocilo;

    public Jezik() {
    }
    public Jezik(String ime, String sporocilo) {
        this.ime = ime;
        this.sporocilo = sporocilo;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getSporocilo() {
        return sporocilo;
    }
    public void setSporocilo(String sporocilo) {
        this.sporocilo = sporocilo;
    }
    public Jezik(long id, String ime, String sporocilo) {
        this.id = id;
        this.ime = ime;
        this.sporocilo = sporocilo;
    }
    @Override
    public String toString() {
        return "Jezik [id=" + id + ", ime=" + ime + ", sporocilo=" + sporocilo + "]";
    }
    
}
