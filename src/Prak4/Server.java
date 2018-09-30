/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak4;

import Prak4.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;


public class Server {
    
    public static void serialize(String pList, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(convert(pList));
            
            System.out.println(convert(pList));
            System.out.println("Package Saved");
        } catch (IOException ex) {
            System.out.println("a problem occured during serialization.\n" + ex.getMessage());
        }
    }
    
    public static List<Mahasiswa> convert(String obj){
        List<Mahasiswa> pList = new ArrayList<Mahasiswa>();
        String temp = "";
        for (int i = 0; i < obj.length(); i++) {
                if(obj.charAt(i) == '%'){
                    String[] builder = temp.split(" - ");
                    pList.add(new Mahasiswa(builder[0],builder[1],builder[2],builder[3]));
                }else temp+=obj.charAt(i);
            }
        return pList;
    }
    
    public static List<Mahasiswa> deserialize(String fileName) {
        
        List<Mahasiswa> pList = new ArrayList<Mahasiswa>();
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            pList = (List<Mahasiswa>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("a problem occured during deserialize %s%n" + fileName);
            System.out.println(e.getMessage());
        }
        
        return pList;
    }
    
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket socket = new DatagramSocket(2000);
        System.out.println("Server activated on "+socket.getLocalPort());
        
        while(true){
            final int BUFFSIZE = 4096;
            DatagramPacket packet = new DatagramPacket(new byte[BUFFSIZE], BUFFSIZE);
            socket.receive(packet);
            System.out.println("Package received");
            ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData());
            BufferedReader br = new BufferedReader(new InputStreamReader(bin));
            String msg = br.readLine();
            
            serialize(msg, "./mahasiswa.ser");
            socket.send(packet);
        }
    }
}
