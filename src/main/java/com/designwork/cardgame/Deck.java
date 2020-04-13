package com.designwork.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        constructDeck();
    }

    private void constructDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        return cards.stream().map(card -> card.rank.toString() + " of " + card.suit.toString().charAt(0))
                .collect(Collectors.toList()).toString();
    }
}
