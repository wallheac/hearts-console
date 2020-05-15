package com.designwork.cardgame.player;

import com.designwork.cardgame.card.Card;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerModelTest {

    private PlayerModel playerModel = new PlayerModel("Amy");

    @Test(expected = UnsupportedOperationException.class)
    public void setStartingHandCreatesUnmodifiableList() throws UnsupportedOperationException {
       playerModel.setStartingHand(Arrays.asList(Card.FiveClubs, Card.TwoClubs));
       List<Card> hand = playerModel.getHand();

       hand.add(Card.ThreeClubs);
    }

    @Test
    public void getHandReturnsStartingHandMinusCardsPlayed() throws UnsupportedOperationException {
        PlayerModel playerModel = new PlayerModel("Ted", UUID.randomUUID(), Arrays.asList(Card.FiveClubs),
                Card.FiveClubs, Card.ThreeClubs, Card.TwoClubs);

        List<Card> actual = playerModel.getHand();

        assertThat(actual.size(), is(2));
        assert(actual.containsAll(Arrays.asList(Card.ThreeClubs, Card.TwoClubs)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getHandReturnsUnmodifiableList() throws UnsupportedOperationException {
        PlayerModel playerModel = new PlayerModel("Ted", UUID.randomUUID(), Arrays.asList(Card.FiveClubs),
                Card.FiveClubs, Card.ThreeClubs, Card.TwoClubs);
        List<Card> actual = playerModel.getHand();

        actual.add(Card.FourClubs);
    }

    @Test
    public void recordPlayedCardCausesCardNotToBeReturnedFromGetHand () {
        playerModel.setStartingHand(Arrays.asList(Card.FiveClubs, Card.TwoClubs, Card.FourClubs));
        playerModel.recordPlayedCard(Card.FiveClubs);

        List<Card> actual = playerModel.getHand();

        assertThat(actual.size(), is(2));
        assert(actual.containsAll(Arrays.asList(Card.FourClubs, Card.TwoClubs)));
    }
}