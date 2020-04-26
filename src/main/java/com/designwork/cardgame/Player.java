package com.designwork.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private String name;
    private List<Pair<Player, Card>> tricksTaken = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    protected Player(String name, Card... cards) {

        for (Card card : cards) {
            this.hand.add(card);
        }
        this.name = name;
    }

    public void drawHand(Deck deck, int numberOfCards) {
        hand = deck.drawHand(numberOfCards);
    }

    public boolean printHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i).prettyPrint());
        }
        return true;
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public List<Pair<Player, Card>> getTricksTaken() {
        return tricksTaken;
    }
}
