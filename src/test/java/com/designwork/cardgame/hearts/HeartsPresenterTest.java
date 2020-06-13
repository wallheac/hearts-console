package com.designwork.cardgame.hearts;

import com.designwork.cardgame.player.PlayerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HeartsPresenterTest {
    private HeartsPresenter heartsPresenter;

    @Mock
    HeartsView heartsView;

    @Test
    public void handlePlayerAddedAddsPlayerModel() {
        List<PlayerModel> playerModels = new ArrayList<>();
        heartsPresenter = new HeartsPresenter(new HeartsView(), playerModels);
        PropertyChangeEvent event = new PropertyChangeEvent(heartsView, null, null, "Ted");

        heartsPresenter.handlePlayerAdded(event);

        assertThat(playerModels.size(), is(1));
        assertThat(playerModels.get(0).getName(), is("Ted"));
    }
}