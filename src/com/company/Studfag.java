package com.company;

public class Studfag {

    private int stdnr;
    private int fagnr;
    private double kar;
    private Fag fag = new Fag();
    private Studerende stud = new Studerende();

    public Studfag(){
    }

    public Studfag(int stdnr, int fagnr, int kar) {
        this.stdnr = stdnr;
        this.fagnr = fagnr;
        this.kar = kar;
    }

    public Studfag(int stdnr, int fagnr) {
        this.stdnr = stdnr;
        this.fagnr = fagnr;
    }

    @Override
    public String toString() {
        return "Studfag{" +
                "stdnr=" + stdnr +
                ", fagnr=" + fagnr +
                ", f=" + fag +
                ", s=" + stud +
                '}';
    }

    //Getter and setters
    public Fag getFag() {
        return fag;
    }

    public Studerende getStud() {
        return stud;
    }

    public double getKar() {
        return kar;
    }

    public void setKar(double kar) {
        this.kar = kar;
    }
}
