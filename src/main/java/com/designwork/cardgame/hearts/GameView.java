package com.designwork.cardgame.hearts;

import com.designwork.cardgame.commons.ui.View;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class GameView implements View {

    public GameView() {
    }

    @Override
    public void initialize() {
        JFrame frame = new JFrame("Game");
        frame.setSize(1400, 1400);

        HeartsGuiView heartsGuiView = new HeartsGuiView();
        heartsGuiView.createDialog(frame);
        frame.setVisible(true);

        HeartsPresenter heartsPresenter = new HeartsPresenter(heartsGuiView);
        heartsPresenter.initializeView();
    }

    @Override
    public void addViewListener(String propertyName, PropertyChangeListener viewListener) {

    }

    @Override
    public void removeViewListener(String propertyName, PropertyChangeListener viewListener) {

    }
}
