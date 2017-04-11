package com.gabrielaangebrandt.tasky;

/**
 * Created by Gabriela on 8.4.2017..
 */
public class Task {
    String naslov, opis;
    int prioritet;

    public Task(String naslov, String opis, int prioritet) {
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

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }
}
