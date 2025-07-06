/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author User
 */
public class Controller {
    
    private static Controller instance;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private int limit = 0;
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
    }
    
    

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void obrisiPoslednjegKlijenta() {
        klijenti.remove(klijenti.size() - 1);
        for (ObradaKlijentskihZahteva k : klijenti) {
            System.out.println(k.getKorIme());
        }
    }

    public double izvrsiOp(int a, int b, String op) {
        
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return (double) a / b;
            case "stepen":
                return Math.pow(a, b);
            case "koren":
                return Math.pow(a, 1.0/b);
            default:
                throw new AssertionError();
        }
        
    }

    public void obrisiKlijenta(String ki) {
        for (int i = 0; i < klijenti.size(); i++) {
            if (klijenti.get(i).getKorIme().equals(ki)){
                klijenti.remove(i);
                return;
            }
        }
    }
    
    
    
    
    
}
