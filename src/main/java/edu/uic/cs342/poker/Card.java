
package edu.uic.cs342.poker;

public record Card(String suit, int value) {

    @Override
    public String toString() {
        String rank = switch (value) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> String.valueOf(value);
        };

        return rank + " of " + suit;
    }
}
