package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoundValidatorTest {

    @Mock
    RoundModel roundModel;

    private RoundValidator roundValidator;

    @Before
    public void setup() {
        roundValidator = new RoundValidator(roundModel);
    }

    @Test
    public void isValidPlayReturnsTrueWhenFirstPlayerPlays2ofC() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoClubs));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TwoClubs, Card.TwoDiamonds, Card.AceClubs, Card.ThreeClubs));
        when(roundModel.getCurrentRound()).thenReturn(1);
        when(roundModel.currentPlayerIsStartingPlayer()).thenReturn(true);
        when(roundModel.getTrick()).thenReturn(trick);
        when(roundModel.heartsBroken()).thenReturn(true);

        boolean result = roundValidator.isValidPlay(0);

        assertTrue(result);
    }

    @Test
    public void isValidPlayReturnsFalseWhenFirstPlayerPlaysNon2ofC() {
        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.ThreeClubs));
        when(roundModel.getCurrentRound()).thenReturn(1);
        when(roundModel.currentPlayerIsStartingPlayer()).thenReturn(true);

        boolean result = roundValidator.isValidPlay(0);

        assertFalse(result);
    }

    @Test
    public void isValidPlayReturnsFalseWhenHeartInFirstRound() {
        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.AceHearts));
        when(roundModel.getCurrentRound()).thenReturn(1);
        when(roundModel.currentPlayerIsStartingPlayer()).thenReturn(false);

        boolean result = roundValidator.isValidPlay(0);

        assertFalse(result);
    }

    @Test
    public void isValidPlayReturnsFalseWhenQofSInFirstRound() {
        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.QueenSpades));
        when(roundModel.getCurrentRound()).thenReturn(1);
        when(roundModel.currentPlayerIsStartingPlayer()).thenReturn(false);

        boolean result = roundValidator.isValidPlay(0);

        assertFalse(result);
    }

    @Test
    public void isValidPlayReturnsFalseWhenPlayerHasSuitInHandButDoesNotFollowSuit() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoClubs));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TwoClubs, Card.TwoDiamonds, Card.AceClubs, Card.ThreeClubs));
        when(roundModel.getCurrentRound()).thenReturn(2);
        when(roundModel.getTrick()).thenReturn(trick);

        boolean result = roundValidator.isValidPlay(1);

        assertFalse(result);
    }

    @Test
    public void isValidPlayReturnsTrueWhenPlayerDoesNotHaveLedSuit() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoClubs));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TwoHearts, Card.TwoDiamonds, Card.AceHearts, Card.ThreeHearts));
        when(roundModel.getCurrentRound()).thenReturn(2);
        when(roundModel.getTrick()).thenReturn(trick);

        boolean result = roundValidator.isValidPlay(1);

        assertTrue(result);
    }

    @Test
    public void isValidPlayReturnsFalseWhenHeartsLedBeforeBreakingHearts() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoHearts));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TwoHearts, Card.TwoDiamonds, Card.AceHearts, Card.ThreeHearts));
        when(roundModel.getCurrentRound()).thenReturn(2);
        when(roundModel.getTrick()).thenReturn(trick);
        when(roundModel.heartsBroken()).thenReturn(false);

        boolean result = roundValidator.isValidPlay(1);

        assertFalse(result);
    }

    @Test
    public void isValidPlayReturnsTrueIfHeartsLedAfterBreakingHearts() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoHearts));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TwoClubs, Card.TwoDiamonds, Card.AceClubs, Card.ThreeClubs));
        when(roundModel.getCurrentRound()).thenReturn(1);
        when(roundModel.currentPlayerIsStartingPlayer()).thenReturn(true);
        when(roundModel.getTrick()).thenReturn(trick);
        when(roundModel.heartsBroken()).thenReturn(true);

        boolean result = roundValidator.isValidPlay(0);

        assertTrue(result);
    }

    @Test
    public void isValidPlayReturnsTrueWhenPlayerHasOnlyHearts() {
        Trick trick = new Trick(Pair.of(UUID.randomUUID(), Card.TwoClubs));

        when(roundModel.getCurrentHand()).thenReturn(Arrays.asList(Card.TenHearts, Card.FourHearts, Card.AceHearts, Card.ThreeHearts));
        when(roundModel.getCurrentRound()).thenReturn(2);
        when(roundModel.getTrick()).thenReturn(trick);
        when(roundModel.heartsBroken()).thenReturn(false);

        boolean result = roundValidator.isValidPlay(1);

        assertTrue(result);
    }

}