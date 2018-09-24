/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak1;

import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author alfari
 */
public class IP {

    public static void main(String[] args) throws IOException {
        String input = JOptionPane.showInputDialog("Masukkan Host");
        for (int i = 1; i < 255; i++) {
            String host = input + "" + i;
            if (InetAddress.getByName(host).isReachable(200)) {
                System.out.println(host + " is Reachable");
            } else {
                System.out.println(host + " is unReachable");
            }
        }
        System.out.println("not found");
    }

}
