
package edu.uic.cs342.poker;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeCardLogic {

    public static int evalHand(ArrayList<Card> hand) {
        return handStrength(hand)[0];
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int[] dealerStrength = handStrength(dealer);
        int[] playerStrength = handStrength(player);

        for (int i = 0; i < playerStrength.length; i++) {
            int comparison = Integer.compare(playerStrength[i], dealerStrength[i]);

            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException("Bet cannot be negative");
        }

        return switch (evalHand(hand)) {
            case 5 -> bet * 40;
            case 4 -> bet * 30;
            case 3 -> bet * 6;
            case 2 -> bet * 3;
            case 1 -> bet;
            default -> -bet;
        };
    }

    private static int[] handStrength(ArrayList<Card> hand) {
        if (hand == null || hand.size() != 3) {
            throw new IllegalArgumentException("Hand must contain exactly three cards");
        }

        int[] values = {
                hand.get(0).value(),
                hand.get(1).value(),
                hand.get(2).value()
        };

        Arrays.sort(values);

        boolean flush = hand.get(0).suit().equals(hand.get(1).suit())
                && hand.get(1).suit().equals(hand.get(2).suit());

        boolean aceLow = values[0] == 2 && values[1] == 3 && values[2] == 14;

        boolean straight = aceLow ||
                (values[0] + 1 == values[1] && values[1] + 1 == values[2]);

        int straightHigh = aceLow ? 3 : values[2];

        if (straight && flush) {
            return new int[]{5, straightHigh};
        }

        if (values[0] == values[2]) {
            return new int[]{4, values[2]};
        }

        if (straight) {
            return new int[]{3, straightHigh};
        }

        if (flush) {
            return new int[]{2, values[2], values[1], values[0]};
        }

        if (values[0] == values[1]) {
            return new int[]{1, values[0], values[2]};
        }

        if (values[1] == values[2]) {
            return new int[]{1, values[1], values[0]};
        }

        return new int[]{0, values[2], values[1], values[0]};
    }
}
