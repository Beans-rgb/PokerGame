
package edu.uic.cs342.poker;

import java.util.ArrayList;

public class Dealer extends Hand {

    public Dealer() {
    }

    public void startRound() {
        clearHand();
    }

    public boolean qualifies() {

        ArrayList<Card> cards = getHand();

        if (cards.size() != 3) {
            throw new IllegalStateException(
                    "Dealer must have exactly 3 cards"
            );
        }

        if (ThreeCardLogic.evalHand(cards) > 0) {
            return true;
        }

        for (Card card : cards) {
            if (card.value() >= 12) {
                return true;
            }
        }

        return false;
    }
}
