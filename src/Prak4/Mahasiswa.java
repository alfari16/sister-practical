/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak4;

import Prak3.*;
import java.io.Serializable;


public class Mahasiswa implements Serializable {
    private String nim, nama, asal, kelas;
    
    public Mahasiswa(String nim, String nama, String asal, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.asal = asal;
        this.kelas = kelas;
    }
    
    public String getNim() {
        return this.nim;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public String getAsal() {
        return this.asal;
    }
    
   public String getKelas() {
       return this.kelas;
   }
    
    @Override
    public String toString() {
        return nim + " - " + nama + " - " + asal + " - " + kelas;
    }
    
}
