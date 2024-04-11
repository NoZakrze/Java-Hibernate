package org.example;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Wieza")
public class Wieza {
    @Id
    private String nazwa;
    @Column
    private int wysokosc;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mag> magowie;
    public Wieza(String nazwa,int wysokosc)
    {
        this.nazwa = nazwa;
        this.wysokosc = wysokosc;
        this.magowie = new ArrayList<Mag>();
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public List<Mag> getMagowie() {
        return magowie;
    }

    @Override
    public String toString()
    {
        String odp = nazwa + " " + wysokosc;
        return odp;
    }
}
