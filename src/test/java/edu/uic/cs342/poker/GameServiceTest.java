
package edu.uic.cs342.poker;

import org.junit.jupiter.api.Test;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @Test
    public void testStartRound() {
        GameService game = new GameService();

        game.startRound(10, 5);

        assertTrue(game.isRoundActive());
        assertEquals(3, game.getPlayerHand().size());
    }

    @Test
    public void testFold() {
        GameService game = new GameService();

        game.startRound(10, 0);

        GameService.RoundResult result = game.finishRound(false);

        assertEquals("Player folded", result.outcome());
        assertEquals(-10, result.netProfit());
        assertFalse(game.isRoundActive());
    }

    @Test
    public void testCannotStartTwoRounds() {
        GameService game = new GameService();

        game.startRound(10, 5);

        assertThrows(
                IllegalStateException.class,
                () -> game.startRound(10, 5)
        );
    }

    @Test
    public void testCannotFinishWithoutStarting() {
        GameService game = new GameService();

        assertThrows(
                IllegalStateException.class,
                () -> game.finishRound(true)
        );
    }

    @Test
    public void testInvalidAnteBet() {
        GameService game = new GameService();

        assertThrows(
                IllegalArgumentException.class,
                () -> game.startRound(0, 5)
        );
    }

    @Test
    public void testInvalidPairPlusBet() {
        GameService game = new GameService();

        assertThrows(
                IllegalArgumentException.class,
                () -> game.startRound(10, -5)
        );
    }

    @Test
    public void testPlayRound() {
        GameService game = new GameService();

        game.startRound(10, 5);

        GameService.RoundResult result = game.finishRound(true);

        assertEquals(3, result.dealerHand().size());

        assertEquals(
                result.anteProfit()
                        + result.playProfit()
                        + result.pairPlusProfit()
                        + result.anteBonus(),
                result.netProfit()
        );

        assertEquals(
                result.netProfit(),
                game.getTotalNetProfit()
        );

        assertFalse(game.isRoundActive());
    }

    @Test
    public void testStartAnotherRound() {
        GameService game = new GameService();

        game.startRound(10, 0);
        game.finishRound(false);

        game.startRound(20, 0);

        assertTrue(game.isRoundActive());
        assertEquals(3, game.getPlayerHand().size());
    }

    @Test
    public void testTotalProfitAcrossRounds() {
        GameService game = new GameService();

        game.startRound(10, 0);
        game.finishRound(false);

        game.startRound(20, 0);
        game.finishRound(false);

        assertEquals(-30, game.getTotalNetProfit());
    }

    @Test
    public void testNoDuplicateCardsBetweenHands() {
        GameService game = new GameService();

        game.startRound(10, 0);

        HashSet<Card> cards = new HashSet<>(game.getPlayerHand());

        GameService.RoundResult result = game.finishRound(true);

        cards.addAll(result.dealerHand());

        assertEquals(6, cards.size());
    }
}
