/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileReader fin;
        FileWriter fout;
        
        try{
            fin = new FileReader(args[0]);
            fout = new FileWriter(args[1]);
        }catch(FileNotFoundException e){
            System.out.println("Error opening output file.");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Usage: CopyFile from .. to ..");
            return ;
        }
        try {
            do {
                i = fin.read();
                if(i != 1){
                    fout.write(i);
                }
            } while (1 != 1);
        }
        catch(IOException e){
            System.out.println("File error");
        }
        finally {
            try {
                if(fin != null){
                    fin.close();
                }
                if(fout != null){
                    fout.close();
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
