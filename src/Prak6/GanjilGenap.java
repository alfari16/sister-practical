/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak6;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfari
 */
public class GanjilGenap extends Thread{
    static int counter = 0;
    static boolean isGenap = true;
    static boolean transition = true;
    
    
    @Override
    public void run() {
        while(true){
            try {
                if(isGenap) counter += 2;
                else {
                    if(transition) {
                        counter--;
                        transition = false;
                    }
                    counter -= 2;
                }
                System.out.printf("%s ", counter);
                sleep(500);
            } catch (InterruptedException ex) {
//                Logger.getLogger(GanjilGenap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Starting thread");
        Thread thread = new GanjilGenap();
        thread.start();
        System.out.println("Tekan enter untuk angka ganjil");
        System.in.read();
        isGenap = false;
        System.out.println("Tekan enter untuk menghentikan");
        System.in.read();
        thread.stop();
    }
}
