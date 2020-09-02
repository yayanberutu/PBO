/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ASUS
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int size;
        InputStream f = new FileInputStream("FileInputStreamDemo.java");
        System.out.println("Total Available Bytes: " + (size = f.available()));
        int n = size/40;
        System.out.println("First " + n + "bytes of the file one read() at a time");
        for(int i=0; i<n; ++i){
            System.out.println((char) f.read());
        }
        System.out.println("\nStill Available:" + f.available());
        System.out.println("Reading the next " + n + "with one read(b[])");
        byte b[] = new byte[n];
        if(f.read(b) != n){
            System.err.println("couldn't read" + n + "bytes.");
        }
        
        System.out.println(new String(b,0, n));
        System.out.println("\nStill Available: " + (size = f.available()));
        System.out.println("Skipping half of remaining bytes with skip()");
        f.skip(size/2);
        System.out.println("Still Available "+ f.available());
        System.out.println("Reading " + n/2 + "into the end of array");
        if(f.read(b, n/2, n/2) != n/2){
            System.err.println("Couldn't read" + n/2 + "bytes.");
        }
        System.out.println(new String(b,0, b.length));
        System.out.println("\nStill Available" + f.available());
        
    }
}
