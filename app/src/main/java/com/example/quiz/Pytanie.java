package com.example.quiz;

public class Pytanie {
    private String tersc;
    private String podpowiedz;
    private boolean odpPoprawna;
    private boolean czyUdzielonoPoprawnejOdpowiedzi;
    private int idZdjecia;

    public Pytanie(String tersc, String podpowiedz, boolean odpPoprawna) {
        this.tersc = tersc;
        this.podpowiedz = podpowiedz;
        this.odpPoprawna = odpPoprawna;
        czyUdzielonoPoprawnejOdpowiedzi = false;
    }

    public Pytanie(String tersc, String podpowiedz, boolean odpPoprawna, int idZdjecia) {
        this.tersc = tersc;
        this.podpowiedz = podpowiedz;
        this.odpPoprawna = odpPoprawna;
        this.idZdjecia = idZdjecia;
        czyUdzielonoPoprawnejOdpowiedzi = false;
    }

    public String getTersc() {
        return tersc;
    }

    public String getPodpowiedz() {
        return podpowiedz;
    }

    public boolean isOdpPoprawna() {
        return odpPoprawna;
    }

    public boolean isCzyUdzielonoPoprawnejOdpowiedzi() {
        return czyUdzielonoPoprawnejOdpowiedzi;
    }

    public void setCzyUdzielonoPoprawnejOdpowiedzi() {
        this.czyUdzielonoPoprawnejOdpowiedzi = true;
    }

    public int getIdZdjecia() {
        return idZdjecia;
    }
}
