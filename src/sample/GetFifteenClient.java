package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
THIS IS CLIENT
 */

public class GetFifteenClient extends Application {
    //player token
    private char token;

    //is my turn
    private boolean myTurn = false;

    //game server
    private GetFifteenInterface server;

    //javafx controller
    private Controller controller;

    //local game board - which cell is marked
    private boolean[][] marked;

    //connect controller variable to actual javafx controller so I can call methods from Controller.java from here
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    //update message in UI
    public void setMsg(String msg) {
        Platform.runLater(() -> controller.setLblMsg(msg));
    }

    public boolean getMyTurn() {
        return myTurn;
    }

    public boolean[][] getMarked() {
        return marked;
    }

    public char getToken() {
        return token;
    }

    //connect player to server and setup other important things
    public void initializeRMI() throws Exception {
        String host = "localhost";

        //bind server variable to actual server
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            server = (GetFifteenInterface) registry.lookup("SumOfFifteen");
            System.out.println("Server object " + server + " found");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //setup board
        marked = new boolean[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                marked[i][j] = false;
            }
        }

        CallbackImplementation client = new CallbackImplementation(this);

        //connect to server
        this.token = server.connect(client);

        if (token != ' ') {
            System.out.println("Connected as " + token + " player.");

            //set current player's color in UI
            Platform.runLater(() -> controller.setLblColorBackground(token));

            if (token == 'X') {
                Platform.runLater(() -> controller.setLblColorText("You are color GREEN"));
            } else if (token == 'O') {
                Platform.runLater(() -> controller.setLblColorText("You are color ORANGE"));
            }
        } else {
            System.out.println("Already two players connected.");
            System.exit(0);
        }
    }

    //update UI with the opponent's move
    public void markOpponent(int row, int col, char token) {
        marked[row][col] = true;

        //call controller to update UI
        Platform.runLater(() -> controller.paintOpponent(row, col, token));
    }

    //mark my move
    public void mark(int row, int column, char token) {
        if (myTurn && !marked[row][column]) {
            //mark cell as used
            marked[row][column] = true;

            //call the server
            try {
                server.chooseNumber(row, column, token);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Get 15");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
