package com.designwork.cardgame;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.card.Rank;
import com.designwork.cardgame.card.Suit;
import com.designwork.cardgame.player.PlayerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static Deck deck = null;
    private List<Card> cards;

    private Deck() {
        cards = new ArrayList(Arrays.asList(Card.values()));
    }

    public static Deck Deck() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void deal(List<PlayerModel> players) {
        int numberOfPlayers = 4;
        int deckSize = cards.size();
        players.stream().forEach(player -> player.setStartingHand(drawHand(deckSize / numberOfPlayers)));
    }

    @Override
    public String toString() {
        return cards.stream().map(Card::toString)
                .collect(Collectors.toList()).toString();
    }

    public List<Card> drawHand(int numberOfCards) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            hand.add(cards.get(i));
        }
        removeHandFromDeck(numberOfCards);
        printHand(hand);
        return hand;
    }

    private void removeHandFromDeck(int numberOfCards) {
        System.err.println(this.toString());
        cards.subList(0, numberOfCards).clear();
    }

    private void printHand(List<Card> hand) {
        System.err.println(hand.stream().map(Card::toString)
                .collect(Collectors.toList()).toString());
    }

    //TODO this was here to deal with 3 and 5 player games. May need to remove
    public void removeCard(Suit suit, Rank rank) {
        this.cards = cards.stream()
                .filter(card -> !(card.getSuit().equals(suit) && card.getRank().equals(rank)))
                .collect(Collectors.toList());
    }

    public void resetDeck() {
        this.cards = new ArrayList<>(Arrays.asList(Card.values()));
    }
}
