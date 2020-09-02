
import java.math.BigInteger;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static char solve(int data){
        BigInteger temp = new BigInteger("765432");
        BigInteger result = temp.pow(data);
        String str = result.toString();
        return str.charAt(str.length()-1);
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int data;
        for(int i=0; i<n; i++){
            data = scn.nextInt();
            System.out.println(String.format("Case #%d = %c", i+1, solve(data)));
        }
    }
    
}
