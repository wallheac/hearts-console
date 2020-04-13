package com.designwork.cardgame;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void shuffleCardsShufflesCards() {
        Deck deck = new Deck();
        String unshuffled = deck.toString();
        deck.shuffle();
        String shuffled = deck.toString();

        assertFalse(shuffled.equals(unshuffled));
    }

}