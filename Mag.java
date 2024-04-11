package org.example;

import javax.persistence.*;


@Entity
@Table(name = "Magowie")
public class Mag {
    @Id
    private String imie;
    @Column
    private int poziom;
    @ManyToOne
    private Wieza wieza;
    public Mag(String imie, int poziom, Wieza wieza)
    {
        this.imie = imie;
        this.poziom = poziom;
        this.wieza = wieza;
    }

    public int getPoziom() {
        return poziom;
    }

    public String getImie() {
        return imie;
    }

    public Wieza getWieza() {
        return wieza;
    }

    @Override
    public String toString()
    {
        String odp = imie + " " + poziom + " " + wieza;
        return odp;
    }
}