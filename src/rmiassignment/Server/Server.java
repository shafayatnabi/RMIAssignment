/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiassignment.Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shafayat
 */
class Addme implements Cipher{

    @Override
    // Decipher 
    public String caesar() throws RemoteException {
        String init_key="TACTICS";
        String key=keyGen(init_key);
        System.out.println(key);
       int move[]=new int[128];
       for(int i=0;i<key.length();i++){
           //System.out.println(key.charAt(i));
           move[key.charAt(i)]=i;
       }
       StringBuilder str=new StringBuilder();
        try {
            FileReader fp=new FileReader(".\\src\\rmiassignment\\Server\\Cipher.txt");
            int c=0;
            while(c!=-1){
                
                c=fp.read();
                if(c==-1)
                    break;
                //System.out.println(move[c]+'A');
                str.append((char)(move[c]+'A'));
            }
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str.toString();
    }

    @Override
    public String ven() throws RemoteException {
      char[][] a=new char[28][28];
        String key="DEFATT";
        //String key=duplicate_erase(init_key);
        FileReader fp,fd;
        int c=0;
        int i,j;
        i=0;j=0;
        StringBuilder str=new StringBuilder();
        try {
            fd=new FileReader(".\\src\\rmiassignment\\Server\\Cipher.txt");
            fp = new FileReader(".\\src\\rmiassignment\\Server\\TabulaRecta.txt");
             while((c=fp.read())!=-1){
                char b=(char)c;
                //System.out.print(b);
                if(b!='\n'){
                    
                    a[i][j]=b;
                    j++;
                }
                else
                {
                    i=i+1;
                    j=0;
                }
                
            }
             i=0;
             while((c=fd.read())!=-1){
                 int row=key.charAt(i%key.length())-'A';
                 i++;
                 char b=(char)c;
                 for(j=0;j<26;j++){
                     if(a[row][j]==b)
                         str.append((char)(j+'A'));
                 }
                
             }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
           
        
        return str.toString();
    }
    public String keyGen(String key){
        StringBuilder str=new StringBuilder();
        int[] val=new int[28];
        int i=0;
        for(i=0;i<key.length();i++){
            if(val[key.charAt(i)-'A']==0){
            val[key.charAt(i)-'A']=1;
            str.append(key.charAt(i));
            }
        }
        for(i=0;i<26;i++){
            if(val[i]==0){
                str.append((char)(i+'A'));
                
            }
            
        }
        return str.toString();
    }
    public String duplicate_erase(String key){
        StringBuilder str=new StringBuilder();
        int[] val=new int[28];
        int i=0;
        for(i=0;i<key.length();i++){
            if(val[key.charAt(i)-'A']==0){
            val[key.charAt(i)-'A']=1;
            str.append(key.charAt(i));
            }
        }
        return str.toString();
    }
    /*
    @Override
    ########## CIPHER
    public String caesar() throws RemoteException {
       String key="GORDIANBCEFHJKLMPQSTUVWXYZ";
       int move[]=new int[28];
       for(int i=0;i<key.length();i++){
           System.out.println(key.charAt(i));
           move[i]=(int)key.charAt(i);
       }
       StringBuilder str=new StringBuilder();
        try {
            FileReader fp=new FileReader(".\\src\\rmiassignment\\Server\\Cipher.txt");
            int c=0;
            while(c!=-1){
                
                c=fp.read();
                if(c==-1)
                    break;
                System.out.println(move[c-'A']);
                str.append((char)move[c-'A']);
            }
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str.toString();
    }

    @Override
    public String ven() throws RemoteException {
        char[][] a=new char[28][28];
        String key="GORDIAN";
        FileReader fp,fd;
        int c=0;
        int i,j;
        i=0;j=0;
        StringBuilder str=new StringBuilder();
        try {
            fd=new FileReader(".\\src\\rmiassignment\\Server\\Cipher.txt");
            fp = new FileReader(".\\src\\rmiassignment\\Server\\TabulaRecta.txt");
             while((c=fp.read())!=-1){
                char b=(char)c;
                //System.out.print(b);
                if(b!='\n'){
                    
                    a[i][j]=b;
                    j++;
                }
                else
                {
                    i=i+1;
                    j=0;
                }
                
            }
             i=0;
             while((c=fd.read())!=-1){
                 char row=(char)c;
                 char col=key.charAt(i%key.length());
                 i++;
                 char en=a[row-'A'][col-'A'];
                 str.append(en);
             }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Addme.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
           
        
        return str.toString();
    }
    */
}
public class Server extends Addme{

    
   public void task() throws RemoteException, AlreadyBoundException{
       Addme obj=new Addme();
       Cipher stub=(Cipher)UnicastRemoteObject.exportObject(obj, 0);
       Registry reg=LocateRegistry.getRegistry(1099);
       reg.bind("CIPHER", stub);
       System.out.println("Server ready");
   }

    public Server() throws RemoteException, AlreadyBoundException {
        LocateRegistry.createRegistry(1099);
        task();
    }
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        new Server();
        
    }
}
