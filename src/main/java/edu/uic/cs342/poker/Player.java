
package edu.uic.cs342.poker;

public class Player extends Hand {

    private int anteBet;
    private int pairPlusBet;
    private int playBet;

    public Player() {
        anteBet = 0;
        pairPlusBet = 0;
        playBet = 0;
    }

    public void startRound(int anteBet, int pairPlusBet) {
        if (anteBet <= 0 || pairPlusBet < 0) {
            throw new IllegalArgumentException("Invalid bet amount");
        }

        clearHand();
        this.anteBet = anteBet;
        this.pairPlusBet = pairPlusBet;
        this.playBet = 0;
    }

    public int getAnteBet() {
        return anteBet;
    }

    public int getPairPlusBet() {
        return pairPlusBet;
    }

    public int getPlayBet() {
        return playBet;
    }

    public int getTotalNetProfit() {
        return getWinnings();
    }

    public void placePlayBet() {
        playBet = anteBet;
    }

    public void recordNetProfit(int amount) {
        updateWinnings(amount);
    }
}
