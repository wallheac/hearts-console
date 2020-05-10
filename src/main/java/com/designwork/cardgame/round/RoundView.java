package com.designwork.cardgame.round;

import com.designwork.cardgame.commons.ui.AbstractView;

public class RoundView extends AbstractView {

    private RoundPresenter roundPresenter;
    private RoundModel roundModel;
    private Integer cardChoice;

    RoundView(RoundModel roundModel) {
        roundPresenter = new RoundPresenter(roundModel, this);
        this.roundModel = roundModel;
        addViewListener(event -> roundPresenter.handleCardPlayed(event));

    }
}
