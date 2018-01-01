/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiassignment.Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Shafayat
 */
public interface Cipher extends Remote{
    public String caesar() throws RemoteException;
    public String ven() throws RemoteException;
}
