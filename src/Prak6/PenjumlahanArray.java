/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak6;

import java.util.ArrayList;

/**
 *
 * @author alfari
 */
public class PenjumlahanArray extends Thread {
    static int[] array1 = {6, 9, 1, 2, 3, 5};
    static int[] array2 = {7, 11, 6, 4, 3, 1};
    static int[] array3 = {5, 4, 3, 2, 1, 12};
    
    int index;
    static int thread = 1;
    static int total = 0;
    
    PenjumlahanArray(int index){
        this.index = index;
    }
    
    @Override
    public void run(){
        int total = array1[index] + array2[index] + array3[index];
        this.total += total;
        System.out.printf("Thread %s: %s + %s + %s = %s\n", thread++, array1[index], array2[index], array3[index], total);
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < array1.length; i++) {
            Thread temp = new PenjumlahanArray(i);
            temp.start();
            temp.join();
        }
        
        System.out.println("Total : "+total);
    }
}
