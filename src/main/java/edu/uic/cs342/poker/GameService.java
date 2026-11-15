
package edu.uic.cs342.poker;

import java.util.ArrayList;

public class GameService {

    private final Player player;
    private final Dealer dealer;
    private final Deck deck;

    private boolean roundActive;

    public GameService() {
        player = new Player();
        dealer = new Dealer();
        deck = new Deck();
        roundActive = false;
    } //I think this covers everything lowkey

    public void startRound(int anteBet, int pairPlusBet) {

        if (roundActive) {
            throw new IllegalStateException("Round already active");
        }

        if (anteBet <= 0 || pairPlusBet < 0) {
            throw new IllegalArgumentException("Invalid bet amount");
        } //kindof like what we spoke about when we met on tuesday about the bet amounts

        deck.resetDeck();
        deck.shuffleDeck();

        player.startRound(anteBet, pairPlusBet);
        dealer.startRound();

        ArrayList<Card> playerCards = deck.getCards();
        ArrayList<Card> dealerCards = deck.getCards();

        for (Card card : playerCards) {
            player.receiveCard(card);
        }

        for (Card card : dealerCards) {
            dealer.receiveCard(card);
        }

        roundActive = true;
    }

    public ArrayList<Card> getPlayerHand() {

        if (!roundActive) {
            throw new IllegalStateException("No active round");
        }

        return player.getHand();
    }

    public RoundResult finishRound(boolean play) {

        if (!roundActive) {
            throw new IllegalStateException("No active round");
        }

        int ante = player.getAnteBet();

        int pairPlusProfit = ThreeCardLogic.evalPPWinnings(
                player.getHand(),
                player.getPairPlusBet()
        ); //confusing but i think its fixed now

        int anteProfit = 0;
        int playProfit = 0;
        int anteBonus = 0;

        String outcome;

        if (!play) {

            anteProfit = -ante;
            outcome = "Player folded";

        } else {

            player.placePlayBet();

            int handRank = ThreeCardLogic.evalHand(
                    player.getHand()
            );

            anteBonus = switch (handRank) {
                case 3 -> ante;
                case 4 -> ante * 4;
                case 5 -> ante * 5;
                default -> 0;
            };

            if (!dealer.qualifies()) {

                anteProfit = ante;
                outcome = "Dealer does not qualify";

            } else {

                int comparison = ThreeCardLogic.compareHands(
                        dealer.getHand(),
                        player.getHand()
                );

                if (comparison > 0) {

                    anteProfit = ante;
                    playProfit = player.getPlayBet();
                    outcome = "Player wins";

                } else if (comparison < 0) {

                    anteProfit = -ante;
                    playProfit = -player.getPlayBet();
                    outcome = "Dealer wins";

                } else {

                    outcome = "Tie";
                }
            }
        }

        int netProfit = anteProfit
                + playProfit
                + pairPlusProfit
                + anteBonus;

        player.recordNetProfit(netProfit);

        roundActive = false;

        return new RoundResult(
                outcome,
                dealer.getHand(),
                anteProfit,
                playProfit,
                pairPlusProfit,
                anteBonus,
                netProfit
        );
    }

    public int getTotalNetProfit() {
        return player.getTotalNetProfit();
    }

    public boolean isRoundActive() {
        return roundActive;
    }

    public record RoundResult(
            String outcome,
            ArrayList<Card> dealerHand,
            int anteProfit,
            int playProfit,
            int pairPlusProfit,
            int anteBonus,
            int netProfit
    ) {
    }
}
