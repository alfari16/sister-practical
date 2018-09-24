/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Prak3.Mahasiswa;
import Prak3.Serialization;

/**
 *
 * @author biobii
 */
public class Main {

    final String fileName = "./mahasiswa.ser";
    List<Mahasiswa> mahasiswa;
    String nim, nama, asal, kelas, isExit;

    void update() {
        Scanner scan = new Scanner(System.in);
        System.out.print("NIM yang akan diupdate:\t");
        String updateTarget = scan.nextLine();

        for (int i = 0; i < mahasiswa.size(); i++) {
            if (mahasiswa.get(i).getNim().equalsIgnoreCase(updateTarget)) {
                System.out.println("==== MASUKKAN DATA BARU ===");
                System.out.print("NIM\t: ");
                nim = scan.nextLine();

                System.out.print("NAMA\t: ");
                nama = scan.nextLine();

                System.out.print("ASAL\t: ");
                asal = scan.nextLine();

                System.out.print("KELAS\t: ");
                kelas = scan.nextLine();

                mahasiswa.set(i, new Mahasiswa(nim, nama, asal, kelas));
                System.out.println("Data baru disimpan.");
                break;
            }
        }
    }

    void insert() {
        Scanner scan = new Scanner(System.in);
        System.out.println("INSERT DATA");
        System.out.print("NIM\t: ");
        nim = scan.nextLine();

        System.out.print("NAMA\t: ");
        nama = scan.nextLine();

        System.out.print("ASAL\t: ");
        asal = scan.nextLine();

        System.out.print("KELAS\t: ");
        kelas = scan.nextLine();

        mahasiswa.add(new Mahasiswa(nim, nama, asal, kelas));
    }

    void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.print("NIM yang akan dihapus:\t");
        String deleteTarget = scan.nextLine();

        for (int i = 0; i < mahasiswa.size(); i++) {
            if (mahasiswa.get(i).getNim().equalsIgnoreCase(deleteTarget)) {
                mahasiswa.remove(i);
                System.out.println("Berhasil menghapus data.");
                break;
            }
        }
    }

    void print() {
        System.out.println("Mahasiswa: " + mahasiswa);
    }

    void save() {
        Serialization.serialize(mahasiswa, fileName);
        System.out.println("Serialization is done!");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Main main = new Main();
        String choice;

        boolean exit = false;

        try {
            //check saved serialized file
            main.mahasiswa = Serialization.deserialize(main.fileName);
        } catch (Exception err) {
            main.mahasiswa = new ArrayList<Mahasiswa>();
        }

        while (!exit) {
            System.out.println("\n==== PILIH AKSI ===");
            System.out.println("1 Insert");
            System.out.println("2 Update");
            System.out.println("3 Delete");
            System.out.println("4 Print");
            System.out.println("5 Save");
            System.out.println("6 Exit");

            Scanner scan = new Scanner(System.in);
            System.out.print("\nPilih (angka) --> ");
            choice = scan.nextLine();
            System.out.println("");

            switch (choice) {
                case "1":
                    main.insert();
                    break;
                case "2":
                    main.update();
                    break;

                case "3":
                    main.delete();
                    break;

                case "4":
                    main.print();
                    break;

                case "5":
                    main.save();
                    break;

                case "6":
                    System.out.print("\nExit program (Y/N) ? ");
                    String isExit = scn.nextLine();
                    System.out.println("");

                    if (isExit.equalsIgnoreCase("Y")) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

}
