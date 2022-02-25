package com.wildan.yutisna;

public class KelasMain {
    private double varPanjang, varLebar;

    public KelasMain(double p, double l){
        this.varPanjang = p;
        this.varLebar = l;
    }

    public double hitungLuas(double panjang, double lebar){
        return panjang*lebar;
    }

    public double hitungLuasDua(){
        return this.varLebar*this.varPanjang;
    }
}