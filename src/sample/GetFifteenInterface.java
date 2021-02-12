package sample;

import java.rmi.*;

public interface GetFifteenInterface extends Remote {
    //connect to server and return token
    char connect(Callback client) throws RemoteException;

    //called from client to notify about a move
    void chooseNumber(int row,int col, char token) throws RemoteException;
}
