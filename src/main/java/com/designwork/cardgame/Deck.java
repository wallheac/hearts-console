package com.designwork.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> cards = new ArrayList<>();

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

    public List<Card> drawHand(int numberOfCards) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            hand.add(cards.get(i));
        }
        System.err.println(this.toString());
        cards.subList(0,numberOfCards).clear();

        System.err.println(hand.stream().map(card -> card.toString())
                .collect(Collectors.toList()).toString());
        return hand;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void removeCard(Suit suit, Rank rank) {
        this.cards = cards.stream()
                .filter(card -> !(card.getSuit().equals(suit) && card.getRank().equals(rank)))
                .collect(Collectors.toList());
    }
}
