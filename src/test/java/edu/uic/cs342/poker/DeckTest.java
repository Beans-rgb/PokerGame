
package edu.uic.cs342.poker;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testDealThreeCards() {
        Deck deck = new Deck();

        ArrayList<Card> hand = deck.getCards();

        assertEquals(3, hand.size());
    }

    @Test
    public void testNoDuplicateCards() {
        Deck deck = new Deck();
        HashSet<Card> dealtCards = new HashSet<>();

        for (int i = 0; i < 17; i++) {
            ArrayList<Card> hand = deck.getCards();

            for (Card card : hand) {
                assertTrue(dealtCards.add(card));
            }
        }

        assertEquals(51, dealtCards.size());
    }

    @Test
    public void testEmptyDeck() {
        Deck deck = new Deck();

        for (int i = 0; i < 17; i++) {
            deck.getCards();
        }

        assertThrows(
                IllegalStateException.class,
                () -> deck.getCards()
        );
    }

    @Test
    public void testResetDeck() {
        Deck deck = new Deck();

        deck.getCards();
        deck.getCards();

        deck.resetDeck();

        HashSet<Card> cards = new HashSet<>();

        for (int i = 0; i < 17; i++) {
            cards.addAll(deck.getCards());
        }

        assertEquals(51, cards.size());
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();

        assertDoesNotThrow(() -> deck.shuffleDeck());

        assertEquals(3, deck.getCards().size());
    }
}
