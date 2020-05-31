package com.designwork.cardgame;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void shuffleCardsShufflesCards() {
        String unshuffled = Deck.Deck().toString();
        Deck.Deck().shuffle();
        String shuffled = Deck.Deck().toString();

        assertFalse(shuffled.equals(unshuffled));
        assertThat(Deck.Deck().toString().split(",").length, is(52));
    }

    @Test
    public void resetDeckReturns52Cards() {
        String unshuffled = Deck.Deck().toString();
        Deck.Deck().resetDeck();
        String reset = Deck.Deck().toString();

        assertTrue(unshuffled.equals(reset));
        assertThat(Deck.Deck().toString().split(",").length, is(52));
    }

}