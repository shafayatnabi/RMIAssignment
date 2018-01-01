/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiassignment;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmiassignment.Server.Cipher;
/**
 *
 * @author Shafayat
 */
public class RMIAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // TODO code application logic here
        Registry reg=LocateRegistry.getRegistry("127.0.0.1",1099);
        Cipher stub=(Cipher)reg.lookup("CIPHER");
        System.out.println(stub.caesar());
    }
    
}
