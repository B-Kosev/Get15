package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl5;

    @FXML
    private Label lbl7;

    @FXML
    private Label lbl8;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl6;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl9;

    @FXML
    private Label lbl2;

    @FXML
    private Label lblColor;

    @FXML
    private TextArea txtaMsg;

    @FXML
    private Button btnRules;

    @FXML
    private Label lblMsg;

    private GetFifteenClient client;
    private char token;
    private boolean myTurn;
    private int[][] gameBoard;

    //easier check for numbers
    private void setGameBoard() {
        gameBoard = new int[][]{{4, 9, 2},
                {3, 5, 7},
                {8, 1, 6}};
    }

    void setLblColorText(String content) {
        lblColor.setText(content);
    }

    void setLblColorBackground(char token){
        if (token == 'X') {
            lblColor.setStyle("-fx-background-color: green;");
        } else {
            lblColor.setStyle("-fx-background-color: orange;");
        }
    }

    void setLblMsg(String content) {
        lblMsg.setText(content);
    }

    public void paintOpponent(int row, int col, char token) {
        int number = gameBoard[row][col];

        switch (number) {
            case 1:
                if (token == 'X') {
                    lbl1.setStyle("-fx-background-color: green;");
                } else {
                    lbl1.setStyle("-fx-background-color: orange;");
                }
                break;
            case 2:
                if (token == 'X') {
                    lbl2.setStyle("-fx-background-color: green;");
                } else {
                    lbl2.setStyle("-fx-background-color: orange;");
                }
                break;
            case 3:
                if (token == 'X') {
                    lbl3.setStyle("-fx-background-color: green;");
                } else {
                    lbl3.setStyle("-fx-background-color: orange;");
                }
                break;
            case 4:
                if (token == 'X') {
                    lbl4.setStyle("-fx-background-color: green;");
                } else {
                    lbl4.setStyle("-fx-background-color: orange;");
                }
                break;
            case 5:
                if (token == 'X') {
                    lbl5.setStyle("-fx-background-color: green;");
                } else {
                    lbl5.setStyle("-fx-background-color: orange;");
                }
                break;
            case 6:
                if (token == 'X') {
                    lbl6.setStyle("-fx-background-color: green;");
                } else {
                    lbl6.setStyle("-fx-background-color: orange;");
                }
                break;
            case 7:
                if (token == 'X') {
                    lbl7.setStyle("-fx-background-color: green;");
                } else {
                    lbl7.setStyle("-fx-background-color: orange;");
                }
                break;
            case 8:
                if (token == 'X') {
                    lbl8.setStyle("-fx-background-color: green;");
                } else {
                    lbl8.setStyle("-fx-background-color: orange;");
                }
                break;
            case 9:
                if (token == 'X') {
                    lbl9.setStyle("-fx-background-color: green;");
                } else {
                    lbl9.setStyle("-fx-background-color: orange;");
                }
                break;
        }
    }

    private boolean checkMarked(int row, int col) {
        return client.getMarked()[row][col];
    }

    @FXML
    void btnRulesOnAction(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules");
        alert.setHeaderText("Rules of the game Get 15");
        alert.setContentText("You and your opponent take alternate turns, each taking a number." +
                " Each number can be taken only once. If you opponent has selected a number, you can no longer take it.\n\n" +
                "Winning criteria:\n" +
                "The first person to have any three numbers that total 15 wins the game.");
        alert.setWidth(300);
        alert.setHeight(300);
        alert.showAndWait();
    }

    @FXML
    void lbl1OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(2, 1)) {
            if (token == 'X') {
                lbl1.setStyle("-fx-background-color: green;");
            } else {
                lbl1.setStyle("-fx-background-color: orange;");
            }

            client.mark(2, 1, token);
        }
    }

    @FXML
    void lbl2OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(0, 2)) {
            if (token == 'X') {
                lbl2.setStyle("-fx-background-color: green;");
            } else {
                lbl2.setStyle("-fx-background-color: orange;");
            }

            client.mark(0, 2, token);
        }
    }

    @FXML
    void lbl3OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(1, 0)) {
            if (token == 'X') {
                lbl3.setStyle("-fx-background-color: green;");
            } else {
                lbl3.setStyle("-fx-background-color: orange;");
            }

            client.mark(1, 0, token);
        }
    }

    @FXML
    void lbl4OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(0, 0)) {
            if (token == 'X') {
                lbl4.setStyle("-fx-background-color: green;");
            } else {
                lbl4.setStyle("-fx-background-color: orange;");
            }

            client.mark(0, 0, token);
        }
    }

    @FXML
    void lbl5OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(1, 1)) {
            if (token == 'X') {
                lbl5.setStyle("-fx-background-color: green;");
            } else {
                lbl5.setStyle("-fx-background-color: orange;");
            }

            client.mark(1, 1, token);
        }
    }

    @FXML
    void lbl6OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(2, 2)) {
            if (token == 'X') {
                lbl6.setStyle("-fx-background-color: green;");
            } else {
                lbl6.setStyle("-fx-background-color: orange;");
            }

            client.mark(2, 2, token);
        }
    }

    @FXML
    void lbl7OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(1, 2)) {
            if (token == 'X') {
                lbl7.setStyle("-fx-background-color: green;");
            } else {
                lbl7.setStyle("-fx-background-color: orange;");
            }

            client.mark(1, 2, token);
        }
    }

    @FXML
    void lbl8OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(2, 0)) {
            if (token == 'X') {
                lbl8.setStyle("-fx-background-color: green;");
            } else {
                lbl8.setStyle("-fx-background-color: orange;");
            }

            client.mark(2, 0, token);
        }
    }

    @FXML
    void lbl9OnClick(MouseEvent event) {
        myTurn = client.getMyTurn();
        if (myTurn && !checkMarked(0, 1)) {
            if (token == 'X') {
                lbl9.setStyle("-fx-background-color: green;");
            } else {
                lbl9.setStyle("-fx-background-color: orange;");
            }

            client.mark(0, 1, token);
        }
    }

    @FXML
    void initialize() {
        client = new GetFifteenClient();
        client.setController(this);
        try {
            client.initializeRMI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setGameBoard();
        token = client.getToken();
    }
}
