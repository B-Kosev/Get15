package sample;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
THIS IS SERVER
 */

public class GetFifteenImplServer extends UnicastRemoteObject implements GetFifteenInterface {
    //players
    private Callback player1;
    private Callback player2;

    //main game board
    private char[][] board = new char[3][3];

    GetFifteenImplServer() throws RemoteException {
        super();
    }

    //connect players to server
    @Override
    public char connect(Callback client) throws RemoteException {
        if (player1 == null) { //register player 1
            player1 = client;
            player1.notify("Waiting for second player.");
            return 'X';
        } else if (player2 == null) { //register player 2
            player2 = client;
            player2.notify("Waiting for the first player to make a choice.");
            player1.notify("Make a choice.");
            player2.makeChoice(false);
            player1.makeChoice(true);
            return 'O';
        } else { //edge case if 3rd player is connecting
            client.notify("Game is full.");
            return ' ';
        }
    }

    //called from client to notify about a move
    @Override
    public void chooseNumber(int row, int col, char token) throws RemoteException {
        board[row][col] = token;

        if (token == 'X') { //player 1 is calling
            player2.markOpponent(row,col,token);
        } else { //player 2 is calling
            player1.markOpponent(row,col,token);
        }

        //check for winning
        if (doIWin(token)) {
            if (token == 'X') {
                player1.notify("You won!");
                player2.notify("You lost!");
                player1.makeChoice(false);
            } else {
                player1.notify("You lost!");
                player2.notify("You won!");
                player2.makeChoice(false);
            }
        }
        //check if board is full
        else if (isBoardFull()) {
            player1.notify("Draw!");
            player2.notify("Draw!");
            player1.makeChoice(false);
            player2.makeChoice(false);
        }
        //switch player's turn
        else if (token == 'X') {
            player1.notify("Wait for the choice of the other player.");
            player1.makeChoice(false);
            player2.notify("Make a choice.");
            player2.makeChoice(true);
        } else if (token == 'O') {
            player2.notify("Wait for the choice of the other player.");
            player2.makeChoice(false);
            player1.notify("Make a choice.");
            player1.makeChoice(true);
        }
    }

    private boolean doIWin(char token) {
        //check for rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == token && board[i][1] == token && board[i][2] == token) {
                return true;
            }
        }

        //check for cols
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == token && board[1][i] == token && board[2][i] == token) {
                return true;
            }
        }

        //check diagonals
        if (board[0][0] == token && board[1][1] == token && board[2][2] == token) {
            return true;
        }

        if (board[0][2] == token && board[1][1] == token && board[2][0] == token) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            GetFifteenInterface obj = new GetFifteenImplServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SumOfFifteen", obj);
            System.out.println("Server " + obj + " registered");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
