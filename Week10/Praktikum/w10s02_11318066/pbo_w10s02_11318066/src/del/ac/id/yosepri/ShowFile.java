/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ShowFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream in;
        
        try{
            in = new FileInputStream(args[0]);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: Showfile file");
            return;
         }
        do {
            i = in.read();
            if(i != -1) System.out.println((char) i);
        }
        while (i != -1);
        in.close();
    }
}
