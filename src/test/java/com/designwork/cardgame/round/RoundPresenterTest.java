package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundPresenterTest {
    @Mock
    private RoundModel roundModel;
    @Mock
    private RoundView roundView;

    private RoundPresenter roundPresenter;

    @Before
    public void setup() {
        roundPresenter = new RoundPresenter(roundModel, roundView);
    }

    @Test
    public void handleCardPlayedDelegatesToRoundModel() {
        UUID uuid = UUID.randomUUID();
        PlayerModel playerModel = new PlayerModel("Charlotte", uuid, new ArrayList<>(),
                new ArrayList<>(), Card.TwoClubs, Card.TwoDiamonds, Card.AceClubs, Card.ThreeClubs);
        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        when(event.getNewValue()).thenReturn("3");
        Trick trick = new Trick(Pair.of(uuid, Card.ThreeClubs));

        when(roundModel.getCurrentPlayer()).thenReturn(playerModel);
        when(roundModel.getCurrentHand()).thenReturn(playerModel.getHand());
        when(roundModel.getTrick()).thenReturn(trick);

        roundPresenter.handleCardPlayed(event);

        verify(roundModel).advancePlayer();
        verify(roundModel).recordPlayedCard(Card.ThreeClubs);
        verify(roundView).setCurrentPlayerName("Charlotte");
        verify(roundView).setHand(playerModel.getHand());
        verify(roundView).setCurrentTrick(trick);
        verify(roundView).requestPlay();

    }

    @Test
    public void roundPresenterIncrementsCurrentRoundWhenLastPlayerPlaysCard() {
        UUID uuid1 = UUID.randomUUID();
        PlayerModel playerOne = new PlayerModel("Charlotte", uuid1, new ArrayList<>(),
                new ArrayList<>(), Card.TwoClubs, Card.TwoDiamonds, Card.AceClubs, Card.ThreeClubs);
        UUID uuid2 = UUID.randomUUID();
        PlayerModel playerTwo = new PlayerModel("Ted", uuid2, new ArrayList<>(),
                new ArrayList<>(), Card.FiveClubs, Card.ThreeDiamonds, Card.AceHearts, Card.ThreeHearts);


        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        when(event.getNewValue()).thenReturn("3").thenReturn("2");

        when(roundModel.getCurrentPlayer()).thenReturn(playerOne).thenReturn(playerTwo);
        when(roundModel.getCurrentHand()).thenReturn(playerOne.getHand()).thenReturn(playerTwo.getHand());
        when(roundModel.getNumberOfPlayers()).thenReturn(2);
        when(roundModel.getTrickSize()).thenReturn(1).thenReturn(2);
        when(roundModel.getCurrentRound()).thenReturn(1);

        roundPresenter.handleCardPlayed(event);
        roundPresenter.handleCardPlayed(event);

        verify(roundModel).setCurrentRound(2);
    }

    @Test
    public void roundPresenterStopsGameWhenOnePlayerReaches100Points() {

        UUID uuid1 = UUID.randomUUID();
        Pair<UUID, Card> pairOne = Pair.of(uuid1, Card.QueenSpades);
        Trick trick = new Trick(pairOne);
        List<Trick> tricks = Arrays.asList(trick, trick, trick, trick, trick, trick, trick, trick);
        PlayerModel playerOne = new PlayerModel("Charlotte", uuid1, new ArrayList<>(),
                tricks, Card.FourClubs);
        UUID uuid2 = UUID.randomUUID();
        PlayerModel playerTwo = new PlayerModel("Ted", uuid2, new ArrayList<>(),
                new ArrayList<>());

        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        when(event.getNewValue()).thenReturn("0");

        when(roundModel.getCurrentPlayer()).thenReturn(playerOne);//.thenReturn(playerTwo);
        when(roundModel.getCurrentHand()).thenReturn(playerOne.getHand()).thenReturn(playerTwo.getHand());
        when(roundModel.getNumberOfPlayers()).thenReturn(2);

        roundPresenter.handleCardPlayed(event);

        verify(roundView).gameOver();
    }

}