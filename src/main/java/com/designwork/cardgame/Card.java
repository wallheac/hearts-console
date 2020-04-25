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
        if (this.rank.getNumber() == 1 || this.rank.getNumber() == 11 || this.rank.getNumber() == 12
                || this.rank.getNumber() == 13) {
            rank = this.rank.toString();
        } else {
            rank = valueOf(this.rank.getNumber());
        }
        return rank + " of " + suit.toString();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object other) {
        // Do the usual preamble

        Card rhs = (Card) other;
        return (this.suit.equals(rhs.suit) && this.rank.equals(rhs.rank));
    }

    @Override
    public String toString() {
        return rank.getNumber() + " of " + suit.toString().charAt(0);
    }

    public static final Card TwoClubs = new Card(Suit.CLUBS, Rank.TWO);
    public static final Card ThreeClubs = new Card(Suit.CLUBS, Rank.THREE);
    public static final Card FourClubs = new Card(Suit.CLUBS, Rank.FOUR);
    public static final Card FiveClubs = new Card(Suit.CLUBS, Rank.FIVE);
}
