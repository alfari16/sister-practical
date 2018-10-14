/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfari
 */
public class Client {
    public static void main(String[] args) {
        final String HOSTNAME = "localhost";
        final int PORT = 2000;
        
        try {
            Socket client = new Socket(HOSTNAME, PORT);
            client.setSoTimeout(2000);
            
            Scanner scn = new Scanner(System.in);
            String nama, nim, asal, kelas;
            
            System.out.print("NIM: ");
            nim = scn.nextLine();
            
            System.out.print("Nama: ");
            nama = scn.nextLine();
            
            System.out.print("Kelas: ");
            kelas = scn.nextLine();
            
            System.out.print("Asal: ");
            asal = scn.nextLine();
            
            
            
            
            OutputStream out = client.getOutputStream();
            PrintStream pout = new PrintStream(out);
            String result = nim+" - "+nama+" - "+kelas+" - "+asal;
            pout.print(result);
            System.out.println("Data sent");
            
            out.flush();
            out.close();
            
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
