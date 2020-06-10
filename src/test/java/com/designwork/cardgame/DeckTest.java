package com.designwork.cardgame;

import com.designwork.cardgame.player.PlayerModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class DeckTest {
    private PlayerModel playerModel;

    @Before
    public void setup() {
        playerModel = new PlayerModel("Me");
    }

    @After
    public void teardown() {
        Deck.Deck().resetDeck();
    }

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

    @Test
    public void dealGivesPlayers10CardsWhen5Players() {
        List<PlayerModel> playerModels = Arrays.asList(playerModel, playerModel, playerModel, playerModel, playerModel);

        Deck.Deck().deal(playerModels);

        assertThat(playerModel.getHand().size(), is(10));
    }

    @Test
    public void dealGivesPlayers17CardsWhen3Players() {
        List<PlayerModel> playerModels = Arrays.asList(playerModel, playerModel, playerModel);

        Deck.Deck().deal(playerModels);

        assertThat(playerModel.getHand().size(), is(17));
    }

}