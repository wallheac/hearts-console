package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RoundModelTest {

    private RoundModel roundModel;

    @Before
    public void setup() {
        PlayerModel tedModel = new PlayerModel("Ted", UUID.randomUUID(), new ArrayList<>(),
                new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        PlayerModel amyModel = new PlayerModel("Amy", UUID.randomUUID(), new ArrayList<>(),
                new ArrayList<>(), Card.FourClubs, Card.FiveClubs);

        roundModel = new RoundModel(
                Arrays.asList(tedModel, amyModel),
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
        PlayerModel amyModel = new PlayerModel("Amy", uuid, new ArrayList<>(), new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        PlayerModel tedModel = new PlayerModel("Ted", uuid, new ArrayList<>(), new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);

        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
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
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), new ArrayList<>(), Card.FourHearts, Card.FiveClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourHearts)));

        roundModel.assignTrickToWinner();

        assertThat(roundModel.getPlayerModelById(uuidOne).getScore(), is(0));
        assertThat(roundModel.getPlayerModelById(uuidTwo).getScore(), is(1));
    }

    @Test
    public void calculateTrickWinnerReturnsPlayerModelWithHighestCardOfLedSuit() {
        UUID uuidOne = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourClubs)));

        PlayerModel winner = roundModel.calculateTrickWinner();

        assertThat(winner, is(amyModel));
    }

    @Test
    public void assignTrickToWinnerFindsWinnerAssignsTrickAndSetsWinnerAsCurrentPlayer() {
        UUID uuidOne = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourClubs)));

        PlayerModel winner = roundModel.calculateTrickWinner();
        roundModel.assignTrickToWinner();

        assertThat(winner, is(amyModel));
        assertThat(winner.getScore(), is(0));
        assertThat(roundModel.getCurrentPlayer(), is(winner));
    }

    @Test
    public void hasNextRoundReturnsTrueWhenNoPlayerHasOneHundredPoints() {
        UUID uuidOne = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuidOne, new ArrayList<>(), new ArrayList<>(), Card.FourClubs);
        UUID uuidTwo = UUID.randomUUID();
        PlayerModel tedModel = new PlayerModel("Ted", uuidTwo, new ArrayList<>(), new ArrayList<>(), Card.TwoClubs);
        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                1, new Trick(Pair.of(uuidTwo, Card.TwoClubs), Pair.of(uuidOne, Card.FourClubs)));

        roundModel.assignTrickToWinner();
        boolean result = roundModel.hasNextRound();

        assertThat(result, is(true));
    }
}
