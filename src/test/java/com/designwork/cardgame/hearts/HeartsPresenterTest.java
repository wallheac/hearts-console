package com.designwork.cardgame.hearts;

import com.designwork.cardgame.commons.util.ConsoleInputUtil;
import com.designwork.cardgame.player.PlayerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeartsPresenterTest {
    private HeartsPresenter heartsPresenter;

    @Mock
    HeartsView heartsView;

    @Test
    public void handlePlayerAddedAddsPlayerModel () {
        List<PlayerModel> playerModels = new ArrayList<>();
        heartsPresenter = new HeartsPresenter(new HeartsView(), playerModels, "n");
        PropertyChangeEvent event = new PropertyChangeEvent(heartsView, null, null, "Ted");

        heartsPresenter.handlePlayerAdded(event);

        assertThat(playerModels.size(), is(1));
        assertThat(playerModels.get(0).getName(), is("Ted"));
    }

//    @Test
//    public void registerPlayersAddsPlayersToListOfPlayerModels() {
//        List<PlayerModel> playerModels = new ArrayList<>();
//        PropertyChangeEvent event = new PropertyChangeEvent(heartsView, null, null, "Amy");
//        when(consoleInputUtil.requestTextInput()).thenReturn("Amy");
//
//        heartsPresenter = new HeartsPresenter(new HeartsView(consoleInputUtil), playerModels);
//        heartsPresenter.registerPlayers();
//
//        assertThat(playerModels.size(), is(1));
//    }
}