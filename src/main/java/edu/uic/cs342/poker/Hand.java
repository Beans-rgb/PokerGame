
package edu.uic.cs342.poker;

import java.util.ArrayList;

public abstract class Hand {

    private int winnings = 0;
    private final ArrayList<Card> hand = new ArrayList<>();

    public ArrayList<Card> getHands() {
        return new ArrayList<>(hand);
    }

    public ArrayList<Card> getHand() {
        return getHands();
    }

    public int getWinnings() {
        return winnings;
    }

    public void updateWinnings(int amount) {
        winnings += amount;
    }

    protected void clearHand() {
        hand.clear();
    }

    public void receiveCard(Card card) {
        if (card == null || hand.size() >= 3) {
            throw new IllegalArgumentException("Invalid card or full hand");
        }

        hand.add(card);
    }
}
