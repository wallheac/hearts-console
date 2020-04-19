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
        return cards.stream().map(card -> card.toString())
                .collect(Collectors.toList()).toString();
    }

    public List<Card> drawHand() {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            hand.add(cards.get(i));
        }
        System.err.println(this.toString());
        cards.subList(0,13).clear();

        System.err.println(hand.stream().map(card -> card.toString())
                .collect(Collectors.toList()).toString());
        return hand;
    }
}
