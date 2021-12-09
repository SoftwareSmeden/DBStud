package com.company;

import java.util.ArrayList;

public class Studerende {

    private int stdnr;
    private String fnavn;
    private String enavn;
    private String adresse;
    private String postnr;
    private String mobil;
    private String klasse;
    private String brugernavn;
    private String password;
    private String stilling;
    private ArrayList<Fag> fagliste = new ArrayList<>();

    public Studerende(){
    }

    public Studerende(int stdnr, String fnavn, String enavn, String adresse, String postnr, String mobil, String klasse, String brugernavn, String password, String stilling) {
        this.stdnr = stdnr;
        this.fnavn = fnavn;
        this.enavn = enavn;
        this.adresse = adresse;
        this.postnr = postnr;
        this.mobil = mobil;
        this.klasse = klasse;
        this.brugernavn = brugernavn;
        this.password = password;
        this.stilling = stilling;
    }

    public Studerende(String brugernavn,String password, String stilling){
        this.brugernavn = brugernavn;
        this.password = password;
        this.stilling = stilling;
    }

    @Override
    public String toString() {
        return "Studerende{" +
                "stdnr=" + stdnr +
                ", fnavn='" + fnavn + '\'' +
                ", enavn='" + enavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postnr='" + postnr + '\'' +
                ", mobil='" + mobil + '\'' +
                ", klasse='" + klasse + '\'' +
                ", brugernavn='" + brugernavn + '\'' +
                ", password=" + password +
                '}';
    }

    //Getter and Setters
    public int getStdnr() {
        return stdnr;
    }

    public void setStdnr(int stdnr) {
        this.stdnr = stdnr;
    }

    public String getFnavn() {
        return fnavn;
    }

    public void setFnavn(String fnavn) {
        this.fnavn = fnavn;
    }

    public String getEnavn() {
        return enavn;
    }

    public void setEnavn(String enavn) {
        this.enavn = enavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public ArrayList<Fag> getFagliste() {
        return fagliste;
    }
}
