/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author User
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;
    private String korIme;

    public ObradaKlijentskihZahteva() {
    }

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    korIme = ((Korisnik) kz.getParam()).getKorIme();
                    int limit = Controller.getInstance().getLimit();
                    int brTrPrijavljenih = Controller.getInstance().getKlijenti().size();
                    if (brTrPrijavljenih >= limit) {
                        so.setOdgovor(false);
                        System.out.println("GRESKA LIMIT");
                    } else {
                        int brojac = 0;
                        List<ObradaKlijentskihZahteva> klijenti = Controller.getInstance().getKlijenti();
                        for (ObradaKlijentskihZahteva k : klijenti) {
                            if (k.getKorIme().equals(((Korisnik) kz.getParam()).getKorIme())) {
                                brojac++;
                            }
                        }
                        if (brojac == 2) {
                            so.setOdgovor(false);
                            Controller.getInstance().obrisiPoslednjegKlijenta();
                            System.out.println("VEC POSTOJI KLIJENT");
                        } else {
                            so.setOdgovor(true);
                        }
                    }

                    break;
                case Operacije.IZVRSI_IZRAZ:
                    String izraz = (String) kz.getParam();

                    String[] niz = izraz.split(";");

                    if (niz.length != 3) {
                        System.out.println("nema 3 el");
                        return;
                    }
                    int operand1 = 0;
                    int operand2 = 0;
                    try {
                        operand1 = Integer.parseInt(niz[0]);
                        operand2 = Integer.parseInt(niz[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("nisu brojevi ova prva 2 el");
                        return;
                    }

                    String op = niz[2];
                    if (!op.equals("+") && !op.equals("-") && !op.equals("*")
                            && !op.equals("/") && !op.equals("stepen") && !op.equals("koren")) {
                        System.out.println("nije operacija validna ova 3.");
                        return;
                    }

                    double rez = Controller.getInstance().izvrsiOp(operand1, operand2, op);
                    so.setOdgovor(rez);
                    break;
                case Operacije.LOGOUT:
                    String ki = ((Korisnik) kz.getParam()).getKorIme();
                    Controller.getInstance().obrisiKlijenta(ki);
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

}
