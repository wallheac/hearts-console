package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.trick.TrickModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundPresenterTest {
    @Mock
    private RoundModel roundModel;

    private RoundPresenter roundPresenter;

    @Before
    public void setup() {
        roundPresenter = new RoundPresenter(roundModel);
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