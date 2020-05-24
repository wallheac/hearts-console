package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.Trick;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RoundModelTest {

    private RoundModel roundModel;

    @Before
    public void setup() {
        PlayerModel tedModel = new PlayerModel("Ted", UUID.randomUUID(), new ArrayList<>(),
                Card.TwoClubs, Card.ThreeClubs);
        PlayerModel amyModel = new PlayerModel("Amy", UUID.randomUUID(), new ArrayList<>(),
                Card.FourClubs, Card.FiveClubs);

        roundModel = new RoundModel(
                Arrays.asList(tedModel, amyModel),
                null,
                1, new Trick());
    }

    @Test
    public void advancePlayerSetsCurrentPlayerToNextPlayerInPlayerModelList() {
        assertThat(roundModel.getCurrentPlayer().getName(), is("Ted"));

        roundModel.advancePlayer();

        assertThat(roundModel.getCurrentPlayer().getName(), is("Amy"));
    }

    @Test
    public void roundModelFindsStartingPlayerCorrectly() {
        assertThat(roundModel.getCurrentPlayer().getName(), is("Ted"));
    }

    @Test
    public void recordPlayedCardUpdatesCurrentPlayerHand() {
        UUID uuid = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        PlayerModel tedModel = new PlayerModel("Ted", uuid, new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);

        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                null,
                1, new Trick());

        roundModel.recordPlayedCard(Card.TwoClubs);

        assertThat(tedModel.getHand().size(), is(1));
        assertThat(tedModel.getHand(), is(Collections.singletonList(Card.ThreeClubs)));
    }

    @Test
    public void recordPlayedCardAddsCardToTrick() {
        roundModel.recordPlayedCard(Card.TenClubs);

        assertThat(roundModel.getTrickSize(), is(1));
        assertThat(roundModel.getTrick().getCards().get(0).getSecond(), is(Card.TenClubs));
    }

    @Test
    public void assignTrickToWinnerAssignsTrickToWinningPlayerModel() {
        UUID uuidOne = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                null,
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourClubs)));

        roundModel.assignTrickToWinner();

        assertThat(roundModel.getPlayerModelById(uuidOne).getScore(), is(1));
    }

    @Test
    public void calculateTrickWinnerReturnsPlayerModelWithHighestCardOfLedSuit() {
        UUID uuidOne = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                null,
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourClubs)));

        PlayerModel winner = roundModel.calculateTrickWinner();

        assertThat(winner, is(amyModel));
    }

    //round model chooses correct starting player each round
}
