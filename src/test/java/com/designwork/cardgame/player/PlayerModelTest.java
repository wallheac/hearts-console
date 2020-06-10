package com.designwork.cardgame.player;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerModelTest {

    private final PlayerModel playerModel = new PlayerModel("Amy");

    @Test(expected = UnsupportedOperationException.class)
    public void setStartingHandCreatesUnmodifiableList() throws UnsupportedOperationException {
        playerModel.setStartingHand(Arrays.asList(Card.FiveClubs, Card.TwoClubs));
        List<Card> hand = playerModel.getHand();

        hand.add(Card.ThreeClubs);
    }

    @Test
    public void getHandReturnsStartingHandMinusCardsPlayed() throws UnsupportedOperationException {
        PlayerModel playerModel = new PlayerModel("Ted", UUID.randomUUID(), Arrays.asList(Card.FiveClubs),
                new ArrayList<>(), Card.FiveClubs, Card.ThreeClubs, Card.TwoClubs);

        List<Card> actual = playerModel.getHand();

        assertThat(actual.size(), is(2));
        assert (actual.containsAll(Arrays.asList(Card.ThreeClubs, Card.TwoClubs)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getHandReturnsUnmodifiableList() throws UnsupportedOperationException {
        PlayerModel playerModel = new PlayerModel("Ted", UUID.randomUUID(), Arrays.asList(Card.FiveClubs),
                new ArrayList<>(), Card.FiveClubs, Card.ThreeClubs, Card.TwoClubs);
        List<Card> actual = playerModel.getHand();

        actual.add(Card.FourClubs);
    }

    @Test
    public void recordPlayedCardCausesCardNotToBeReturnedFromGetHand() {
        playerModel.setStartingHand(Arrays.asList(Card.FiveClubs, Card.TwoClubs, Card.FourClubs));
        playerModel.recordPlayedCard(Card.FiveClubs);

        List<Card> actual = playerModel.getHand();

        assertThat(actual.size(), is(2));
        assert (actual.containsAll(Arrays.asList(Card.FourClubs, Card.TwoClubs)));
    }

    @Test
    public void getScoreReturnsTotalOfOnePenaltyPointForEachHeartInPlayersTricks() {
        UUID uuid = UUID.randomUUID();
        Trick trickOne = new Trick(Pair.of(uuid, Card.TwoDiamonds), Pair.of(uuid, Card.ThreeClubs),
                Pair.of(uuid, Card.ThreeHearts), Pair.of(uuid, Card.FourHearts));
        Trick trickTwo = new Trick(Pair.of(uuid, Card.TenClubs), Pair.of(uuid, Card.FiveHearts),
                Pair.of(uuid, Card.TenDiamonds), Pair.of(uuid, Card.FourSpades));
        PlayerModel playerModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Arrays.asList(trickOne, trickTwo));

        Integer result = playerModel.getScore();

        assert (result == 3);
    }

    @Test
    public void getScoreReturnsTotalOfThirteenPenaltyPointsForQueenOfSpades() {
        UUID uuid = UUID.randomUUID();
        Trick trickOne = new Trick(Pair.of(uuid, Card.TwoDiamonds), Pair.of(uuid, Card.ThreeClubs),
                Pair.of(uuid, Card.QueenSpades), Pair.of(uuid, Card.FourClubs));
        Trick trickTwo = new Trick(Pair.of(uuid, Card.TenClubs), Pair.of(uuid, Card.FiveClubs),
                Pair.of(uuid, Card.TenDiamonds), Pair.of(uuid, Card.FourSpades));
        PlayerModel playerModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Arrays.asList(trickOne, trickTwo));

        Integer result = playerModel.getScore();

        assert (result == 13);
    }

    @Test
    public void getScoreCalculatesTotalScoreCorrectlyWhenBothQueenOfSpadesAndHeartsArePresent() {
        UUID uuid = UUID.randomUUID();
        Trick trickOne = new Trick(Pair.of(uuid, Card.TwoHearts), Pair.of(uuid, Card.ThreeClubs),
                Pair.of(uuid, Card.QueenSpades), Pair.of(uuid, Card.FourClubs));
        Trick trickTwo = new Trick(Pair.of(uuid, Card.TenClubs), Pair.of(uuid, Card.FiveHearts),
                Pair.of(uuid, Card.TenDiamonds), Pair.of(uuid, Card.FourSpades));
        PlayerModel playerModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Arrays.asList(trickOne, trickTwo));

        Integer result = playerModel.getScore();

        assert (result == 15);
    }

    @Test
    public void getScoreReturnsNoPenaltyPointsForTrickWithNeitherQueenOfSpadesNorHearts() {
        UUID uuid = UUID.randomUUID();
        Trick trickOne = new Trick(Pair.of(uuid, Card.TwoDiamonds), Pair.of(uuid, Card.ThreeClubs),
                Pair.of(uuid, Card.QueenDiamonds), Pair.of(uuid, Card.FourClubs));
        Trick trickTwo = new Trick(Pair.of(uuid, Card.TenClubs), Pair.of(uuid, Card.FiveClubs),
                Pair.of(uuid, Card.TenDiamonds), Pair.of(uuid, Card.FourSpades));
        PlayerModel playerModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Arrays.asList(trickOne, trickTwo));

        Integer result = playerModel.getScore();

        assert (result == 0);
    }
}