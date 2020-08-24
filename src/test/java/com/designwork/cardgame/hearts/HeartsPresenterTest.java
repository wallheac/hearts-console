package com.designwork.cardgame.hearts;

import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.round.RoundGuiView;
import com.designwork.cardgame.round.RoundModel;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.*;
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
    @Ignore
    public void handlePlayerAddedAddsPlayerModel() {
        List<PlayerModel> playerModels = new ArrayList<>();
        RoundModel roundModel = new RoundModel();
        heartsPresenter = new HeartsPresenter(new HeartsView(), roundModel, new RoundGuiView(new JFrame()));
        PropertyChangeEvent event = new PropertyChangeEvent(heartsView, null, null, "Ted");

        heartsPresenter.handlePlayerAdded(event);

        assertThat(roundModel.getPlayerModels().size(), is(1));
        assertThat(roundModel.getPlayerModels().get(0).getName(), is("Ted"));
    }
}