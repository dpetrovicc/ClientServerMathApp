/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.Controller;
import java.util.Scanner;
import server.PokreniServer;

/**
 *
 * @author User
 */
public class main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi max broj klijenata: ");
        int limit = scanner.nextInt();
        Controller.getInstance().setLimit(limit);
        
        PokreniServer ps = new PokreniServer();
        ps.start();
        
        
    }
}
