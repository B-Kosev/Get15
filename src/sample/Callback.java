package sample;

import java.awt.*;
import java.rmi.*;

public interface Callback extends Remote {
    //notify the player to make a choice
    void makeChoice(boolean turn) throws RemoteException;

    //server sends a message to be displayed by the client
    void notify(String msg) throws RemoteException;

    //mark opponent's choice
    void markOpponent(int row,int col,char token) throws RemoteException;
}
