package com.designwork.cardgame.hearts;

import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.ui.View;
import com.designwork.cardgame.round.RoundGuiView;
import com.designwork.cardgame.round.RoundModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class GameView extends AbstractView implements View {

    public GameView() {
    }

    public void initialize() {
        JFrame frame = new JFrame("Game");
        frame.setSize(1400, 1400);

        HeartsGuiDialog heartsGuiDialog = new HeartsGuiDialog(frame);
        frame.setVisible(true);

        RoundModel roundModel = new RoundModel();
        RoundGuiView roundView = new RoundGuiView(frame);
        HeartsPresenter heartsPresenter = new HeartsPresenter(heartsGuiDialog, roundModel, roundView);

        RoundGuiView roundGuiView = new RoundGuiView(frame);
        heartsPresenter.initializeView();
    }

    @Override
    public void addViewListener(String propertyName, PropertyChangeListener viewListener) {

    }

    @Override
    public void removeViewListener(String propertyName, PropertyChangeListener viewListener) {

    }
}
