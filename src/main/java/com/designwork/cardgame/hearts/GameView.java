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
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game");
            frame.setSize(2000, 1400);

            HeartsGuiDialog heartsGuiDialog = new HeartsGuiDialog(frame);
            RoundModel roundModel = new RoundModel();
            RoundGuiView roundView = new RoundGuiView(frame);
            HeartsPresenter heartsPresenter = new HeartsPresenter(heartsGuiDialog, roundModel, roundView);
            heartsPresenter.initializeView();
        });

    }

    @Override
    public void addViewListener(String propertyName, PropertyChangeListener viewListener) {

    }

    @Override
    public void removeViewListener(String propertyName, PropertyChangeListener viewListener) {

    }
}
