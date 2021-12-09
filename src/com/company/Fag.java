package com.company;

public class Fag {

    private int fagnr;
    private String fagnavn;

    public Fag(){
    }

    public Fag(int fagnr, String fagnavn) {
        this.fagnr = fagnr;
        this.fagnavn = fagnavn;
    }

    public Fag(String fagnavn){
        this.fagnavn = fagnavn;
    }

    @Override
    public String toString() {
        return "Fag{" +
                "fagnr=" + fagnr +
                ", fagnavn='" + fagnavn + '\'' +
                '}';
    }

    //Getter og setters
    public int getFagnr() {
        return fagnr;
    }

    public void setFagnr(int fagnr) {
        this.fagnr = fagnr;
    }

    public String getFagnavn() {
        return fagnavn;
    }

    public void setFagnavn(String fagnavn) {
        this.fagnavn = fagnavn;
    }
}
