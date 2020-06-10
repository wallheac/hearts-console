package com.designwork.cardgame;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

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
        Deck.Deck().resetDeck();

        String reset = Deck.Deck().toString();

        assertThat(reset.split(",").length, is(52));
    }

}