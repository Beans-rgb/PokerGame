package edu.uic.cs342.poker;

import java.util.ArrayList;

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
    }

    // this will pull out 3 random cards from the deck.
    // a new Card array "hand" will be made and it would be returned to the Player/Dealer
    public ArrayList<Card> getCards() {

        ArrayList<Card> hand = new ArrayList<Card>();

        //TODO: insert getCards() logic here

        return hand;
    }

    // this will reset the deck
    public void resetDeck() {

        //TODO: insert resetDeck() logic here
    }
}
