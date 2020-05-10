package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;


public class RoundPresenterTest {

    private RoundPresenter roundPresenter;
    private RoundModel roundModel;

    @Before
    public void setup() {
        roundModel = mock(RoundModel.class);
        roundPresenter = new RoundPresenter(roundModel, new RoundView(roundModel));
    }

    @Test
    public void handleCardPlayedDelegatesToRoundModel() {
        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        when(event.getNewValue()).thenReturn(3);

        roundPresenter.handleCardPlayed(event);

        verify(roundModel).advancePlayer();
        verify(roundModel).addPlayedCardToTrick(Card.ThreeClubs);
    }

}