/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PokreniServer extends Thread {

    private Socket s;
    private ServerSocket serverskiSoket;
    private boolean kraj = false;
    private static int broj = 0;

    @Override
    public void run() {
        try {
            serverskiSoket = new ServerSocket(9000);
            System.out.println("soket otvoren");

            while (!kraj) {
                Socket s = serverskiSoket.accept();

                broj++;

                if (broj > Controller.getInstance().getLimit()) {
                    System.out.println("Ne moze vise");
                } else {
                    System.out.println("Klijent " + broj + " povezan\n");
                    ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                    Controller.getInstance().getKlijenti().add(nit);
                    nit.start();
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
