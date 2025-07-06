/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class MatOp implements Serializable{
    private int a;
    private int b;
    private String op;
    private double rez;

    public MatOp() {
    }

    public MatOp(int a, int b, String op, double rez) {
        this.a = a;
        this.b = b;
        this.op = op;
        this.rez = rez;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public double getRez() {
        return rez;
    }

    public void setRez(double rez) {
        this.rez = rez;
    }

    
    
    
    
    
    
}
