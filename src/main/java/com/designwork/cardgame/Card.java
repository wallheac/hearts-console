package com.designwork.cardgame;

import static java.lang.String.valueOf;

public class Card {
    public final Suit suit;
    public final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String prettyPrint() {
        String rank;
        if (this.rank.getNumber() == 1 || this.rank.getNumber() == 11
                || this.rank.getNumber() == 12 || this.rank.getNumber() == 13) {
            rank = this.rank.toString();
        } else {
            rank = valueOf(this.rank.getNumber());
        }
        return rank + " of " + suit.toString();
    }

    @Override
    public String toString() {
        return rank.getNumber() + " of " + suit.toString().charAt(0);
    }
}
