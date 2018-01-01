/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiassignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Shafayat
 */
public class Test {
    //Key Generator
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String key="GORDIAN";
        StringBuilder str=new StringBuilder();
        int[] val=new int[28];
        int i=0;
        for(i=0;i<key.length();i++){
            val[key.charAt(i)-'A']=1;
            str.append(key.charAt(i));
        }
        for(i=0;i<26;i++){
            if(val[i]==0){
                str.append((char)(i+'A'));
                
            }
            
        }
        System.out.println(str.toString());
    }
}
