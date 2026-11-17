
package edu.uic.cs342.poker.controller;

import edu.uic.cs342.poker.Card;
import edu.uic.cs342.poker.GameService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class GameController {

    private final GameService game = new GameService();

    @FXML private TextField anteField;
    @FXML private TextField pairPlusField;

    @FXML private HBox playerCardsBox;
    @FXML private HBox dealerCardsBox;

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

        showMessage(playerCardsBox, "No cards dealt");
        showMessage(dealerCardsBox, "Dealer cards hidden");

        resultLabel.setText("Enter your bets to begin");
        profitLabel.setText("");
        totalProfitLabel.setText("Total Profit: 0");
    }

    @FXML
    private void handleStartRound() {
        try {
            int ante = Integer.parseInt(anteField.getText().trim());

            String pairText = pairPlusField.getText().trim();
            int pairPlus = pairText.isEmpty() ? 0 : Integer.parseInt(pairText);

            game.startRound(ante, pairPlus);

            showCards(playerCardsBox, game.getPlayerHand());
            showCardBacks(dealerCardsBox);

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

        showCards(dealerCardsBox, result.dealerHand());

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

    private void showCards(HBox box, ArrayList<Card> cards) {
        box.getChildren().clear();

        for (Card card : cards) {
            String rank = switch (card.value()) {
                case 11 -> "J";
                case 12 -> "Q";
                case 13 -> "K";
                case 14 -> "A";
                default -> String.valueOf(card.value());
            };

            String suit = switch (card.suit()) {
                case "Hearts" -> "♥";
                case "Diamonds" -> "♦";
                case "Clubs" -> "♣";
                case "Spades" -> "♠";
                default -> "?";
            };

            Label cardLabel = new Label(rank + "\n" + suit);

            cardLabel.setAlignment(Pos.CENTER);
            cardLabel.setMinSize(85, 115);
            cardLabel.setPrefSize(85, 115);

            cardLabel.getStyleClass().add("playing-card");

            if (suit.equals("♥") || suit.equals("♦")) {
                cardLabel.getStyleClass().add("red-card");
            } else {
                cardLabel.getStyleClass().add("black-card");
            }

            box.getChildren().add(cardLabel);
        }
    }

    private void showCardBacks(HBox box) {
        box.getChildren().clear();

        for (int i = 0; i < 3; i++) {
            Label cardBack = new Label("♠");

            cardBack.setAlignment(Pos.CENTER);
            cardBack.setMinSize(85, 115);
            cardBack.setPrefSize(85, 115);

            cardBack.getStyleClass().add("card-back");
            box.getChildren().add(cardBack);
        }
    }

    private void showMessage(HBox box, String message) {
        box.getChildren().clear();
        box.getChildren().add(new Label(message));
    }
}
