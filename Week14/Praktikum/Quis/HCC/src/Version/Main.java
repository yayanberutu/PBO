/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Version;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String resourceName = "/Version/login.txt";
        URL res = Main.class.getResource(resourceName);
        System.out.println("resource found at url="+res);
        String username, password, txt;
        FileReader fr = new FileReader("X:\\AKADEMIK\\Perkuliahan\\Semester3\\Pemrograman Berorientasi Objek\\Week14\\Praktikum\\Quis\\HCC\\src\\Version\\login.txt");
        BufferedReader br = new BufferedReader(fr);
        while((txt = br.readLine()) != null){
            username = txt.split(" ")[0].toString();
            password = txt.split(" ")[1].toString();
            System.out.println(username);
            System.out.println(password);
        }
    }
    
}
