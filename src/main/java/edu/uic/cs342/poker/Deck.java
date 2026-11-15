
package edu.uic.cs342.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};

        for (String aSuit: suits) {
            for (int val = 2; val <= 14; val++) {
                cards.add(new Card(aSuit, val));
            }
        }
    }


    // this will shuffle the deck
    public void shuffleDeck() {

        //TODO: insert shuffleDeck() logic here
        Collections.shuffle(cards);
    }

    // this will pull out 3 random cards from the deck.
    // a new Card array "hand" will be made and it would be returned to the Player/Dealer
    public ArrayList<Card> getCards() {

        ArrayList<Card> hand = new ArrayList<Card>();

        //TODO: insert getCards() logic here
        if (cards.size() < 3) {
            throw new IllegalStateException("Not enough cards in the deck");
        }

        shuffleDeck();

        for (int i = 0; i < 3; i++) {
            hand.add(cards.remove(0));
        }

        return hand;
    }

    // this will reset the deck
    public void resetDeck() {

        //TODO: insert resetDeck() logic here
        cards.clear();

        String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};

        for (String aSuit: suits) {
            for (int val = 2; val <= 14; val++) {
                cards.add(new Card(aSuit, val));
            }
        }
    }
}
