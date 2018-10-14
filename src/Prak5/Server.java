/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak5;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfari
 */
public class Server{
    public static void serialize(String pList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./mahasiswa2.ser"))) {
            out.writeObject(pList);
        } catch (IOException ex) {
            System.out.println("a problem occured during serialization.\n" + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            final int PORT = 2000;
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");
            
            Socket client = server.accept();
            System.out.printf("Request came from %s:%s\n", client.getInetAddress(), client.getPort());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String data = br.readLine();
            serialize(data);
            
            System.out.println("Data saved: "+data);
            
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
