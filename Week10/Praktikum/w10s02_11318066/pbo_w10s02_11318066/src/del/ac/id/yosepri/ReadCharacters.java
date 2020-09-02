/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.io.BufferedReader;

/**
 *
 * @author ASUS
 */
public class ReadCharacters {
    public static void main(String[] args) {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter character, 'q' to quit." );
        do {
            c = (char) br.read();
            System.out.println(c);
        }
        while (c != 'q');
                
    }
}
