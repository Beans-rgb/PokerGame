
package edu.uic.cs342.poker.controller;

import edu.uic.cs342.poker.GameService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameController {

    private final GameService game = new GameService();

    @FXML private TextField anteField;
    @FXML private TextField pairPlusField;

    @FXML private Label playerCardsLabel;
    @FXML private Label dealerCardsLabel;
    @FXML private Label resultLabel;
    @FXML private Label profitLabel;
    @FXML private Label totalProfitLabel;

    @FXML private Button startRoundButton;
    @FXML private Button playButton;
    @FXML private Button foldButton;

    @FXML
    private void initialize() {
        startRoundButton.setDisable(false);
        playButton.setDisable(true);
        foldButton.setDisable(true);

        playerCardsLabel.setText("No cards dealt");
        dealerCardsLabel.setText("Dealer cards hidden");
        resultLabel.setText("Enter your bets to begin");
        profitLabel.setText("");
        totalProfitLabel.setText("Total Profit: 0");
    }

    @FXML
    private void handleStartRound() {
        try {
            int ante = Integer.parseInt(anteField.getText().trim());
            int pairPlus = Integer.parseInt(pairPlusField.getText().trim());

            game.startRound(ante, pairPlus);

            playerCardsLabel.setText(
                    "Your Cards: " + game.getPlayerHand()
            );

            dealerCardsLabel.setText("Dealer cards hidden");
            resultLabel.setText("Choose Play or Fold");
            profitLabel.setText("");

            startRoundButton.setDisable(true);
            playButton.setDisable(false);
            foldButton.setDisable(false);

        } catch (IllegalArgumentException | IllegalStateException e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handlePlay() {
        finishRound(true);
    }

    @FXML
    private void handleFold() {
        finishRound(false);
    }

    private void finishRound(boolean play) {
        GameService.RoundResult result = game.finishRound(play);

        dealerCardsLabel.setText(
                "Dealer Cards: " + result.dealerHand()
        );

        resultLabel.setText(result.outcome());

        profitLabel.setText(
                "Round Profit/Loss: " + result.netProfit()
        );

        totalProfitLabel.setText(
                "Total Profit/Loss: " + game.getTotalNetProfit()
        );

        startRoundButton.setDisable(false);
        playButton.setDisable(true);
        foldButton.setDisable(true);
    }
}
