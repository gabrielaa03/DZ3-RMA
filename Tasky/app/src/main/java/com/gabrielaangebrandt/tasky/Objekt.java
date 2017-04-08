package com.gabrielaangebrandt.tasky;

/**
 * Created by Gabriela on 8.4.2017..
 */
public class Objekt {
    String naslov, opis, prioritet;

    public Objekt(String naslov, String opis, String prioritet) {
        this.naslov = naslov;
        this.opis = opis;
        this.prioritet = prioritet;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }
}
