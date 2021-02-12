package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallbackImplementation extends UnicastRemoteObject implements Callback {
    //client variable
    private GetFifteenClient thisClient;

    CallbackImplementation(Object client) throws RemoteException {
        thisClient = (GetFifteenClient) client;
    }

    //call from server
    @Override
    public void makeChoice(boolean turn) throws RemoteException {
        thisClient.setMyTurn(turn);
    }

    //the server sends msg
    @Override
    public void notify(String msg) throws RemoteException {
        thisClient.setMsg(msg);
    }

    //call from server to mark opponent's move
    @Override
    public void markOpponent(int row, int col, char token) throws RemoteException {
        thisClient.markOpponent(row,col,token);
    }
}
