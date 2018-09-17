    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author alfari
 */
public class Copy {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream input = new FileInputStream("/home/alfari/NetBeansProjects/MyApplication/src/assets/source.txt");
        OutputStream output = new FileOutputStream("/home/alfari/NetBeansProjects/MyApplication/src/assets/dest.txt");
        
        int data = input.read();
        while(data != -1){
            output.write((byte) data);
            System.out.println((byte) data);
            data=input.read();
        }
        output.close();
    }
}
