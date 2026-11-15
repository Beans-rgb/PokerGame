
package edu.uic.cs342.poker;

import java.util.ArrayList;

public class Dealer extends Hand {

    private final ArrayList<Card> hand = new ArrayList<>();

    public Dealer() {
    }

    public void startRound() {
        hand.clear();
    }

    public void receiveCard(Card card) {
        if (card == null || hand.size() >= 3) {
            throw new IllegalArgumentException("Invalid card or full hand");
        }

        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public boolean qualifies() {

        if (hand.size() != 3) {
            throw new IllegalStateException("Dealer must have exactly 3 cards");
        }

        if (ThreeCardLogic.evalHand(hand) > 0) {
            return true;
        }

        for (Card card : hand) {
            if (card.value() >= 12) {
                return true;
            }
        }

        return false;
    }
}
