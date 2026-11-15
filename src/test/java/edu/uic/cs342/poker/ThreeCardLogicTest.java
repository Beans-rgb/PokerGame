
package edu.uic.cs342.poker;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ThreeCardLogicTest {

    private ArrayList<Card> hand(Card a, Card b, Card c) {
        return new ArrayList<>(Arrays.asList(a, b, c));
    }

    @Test
    public void testStraightFlush() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 12),
                new Card("Hearts", 13),
                new Card("Hearts", 14)
        );

        assertEquals(5, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testThreeOfAKind() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 9),
                new Card("Spades", 9),
                new Card("Clubs", 9)
        );

        assertEquals(4, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testStraight() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 5),
                new Card("Spades", 6),
                new Card("Clubs", 7)
        );

        assertEquals(3, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testAceLowStraight() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 14),
                new Card("Spades", 2),
                new Card("Clubs", 3)
        );

        assertEquals(3, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testFlush() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 2),
                new Card("Hearts", 7),
                new Card("Hearts", 11)
        );

        assertEquals(2, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testPair() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 8),
                new Card("Spades", 8),
                new Card("Clubs", 3)
        );

        assertEquals(1, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testHighCard() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 2),
                new Card("Spades", 7),
                new Card("Clubs", 12)
        );

        assertEquals(0, ThreeCardLogic.evalHand(cards));
    }

    @Test
    public void testPlayerWins() {
        ArrayList<Card> dealer = hand(
                new Card("Hearts", 2),
                new Card("Spades", 7),
                new Card("Clubs", 12)
        );

        ArrayList<Card> player = hand(
                new Card("Hearts", 9),
                new Card("Spades", 9),
                new Card("Clubs", 4)
        );

        assertEquals(1,
                ThreeCardLogic.compareHands(dealer, player));
    }

    @Test
    public void testDealerWins() {
        ArrayList<Card> dealer = hand(
                new Card("Hearts", 10),
                new Card("Spades", 10),
                new Card("Clubs", 5)
        );

        ArrayList<Card> player = hand(
                new Card("Hearts", 2),
                new Card("Spades", 7),
                new Card("Clubs", 12)
        );

        assertEquals(-1,
                ThreeCardLogic.compareHands(dealer, player));
    }

    @Test
    public void testTie() {
        ArrayList<Card> dealer = hand(
                new Card("Hearts", 2),
                new Card("Spades", 7),
                new Card("Clubs", 12)
        );

        ArrayList<Card> player = hand(
                new Card("Diamonds", 2),
                new Card("Clubs", 7),
                new Card("Spades", 12)
        );

        assertEquals(0,
                ThreeCardLogic.compareHands(dealer, player));
    }

    @Test
    public void testPairTieBreaker() {
        ArrayList<Card> dealer = hand(
                new Card("Hearts", 8),
                new Card("Spades", 8),
                new Card("Clubs", 5)
        );

        ArrayList<Card> player = hand(
                new Card("Diamonds", 9),
                new Card("Clubs", 9),
                new Card("Spades", 2)
        );

        assertEquals(1,
                ThreeCardLogic.compareHands(dealer, player));
    }

    @Test
    public void testPairPlusWinnings() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 9),
                new Card("Spades", 9),
                new Card("Clubs", 9)
        );

        assertEquals(300,
                ThreeCardLogic.evalPPWinnings(cards, 10));
    }

    @Test
    public void testPairPlusLoss() {
        ArrayList<Card> cards = hand(
                new Card("Hearts", 2),
                new Card("Spades", 7),
                new Card("Clubs", 12)
        );

        assertEquals(-10,
                ThreeCardLogic.evalPPWinnings(cards, 10));
    }

    @Test
    public void testInvalidHand() {
        ArrayList<Card> cards = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> ThreeCardLogic.evalHand(cards));
    }
}
