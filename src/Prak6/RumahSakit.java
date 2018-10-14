/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak6;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfari
 */
public class RumahSakit {

    static ArrayList<Pasien> pasien = new ArrayList<Pasien>();

    static void startHospital() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            Pasien temp = new Pasien("Pasien " + i);
            pasien.add(temp);
            temp.start();
            sleep(500);
            temp.suspend();
            if (pasien.size() == 10) {
//                System.out.println("executed");
                nextHandler();
            }
        }
    }

    static void nextHandler() {
        int counter = 1;
        for (Pasien singlePasien : pasien) {
            if (counter <= 5) {
                singlePasien.setPerawat("Perawat 1");
            } else {
                singlePasien.setPerawat("Perawat 2");
            }
//            System.out.println(counter);
            singlePasien.resume();
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        startHospital();
    }

}

class Pasien extends Thread {

    private String nama;
    private String perawat;

    void setPerawat(String perawat) {
        this.perawat = perawat;
    }

    Pasien(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return this.nama;
    }

    @Override
    public void run() {
        System.out.printf("%s ditangani dokter.\n", nama);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
//            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.printf("%s dirawat oleh perawat %s.\n", nama, perawat);
        System.out.printf("%s membayar ke kasir.\n", nama);

    }
}
