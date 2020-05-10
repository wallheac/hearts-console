package com.designwork.cardgame;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void shuffleCardsShufflesCards() {
        String unshuffled = Deck.Deck().toString();
        Deck.Deck().shuffle();
        String shuffled = Deck.Deck().toString();

        assertFalse(shuffled.equals(unshuffled));
    }

}