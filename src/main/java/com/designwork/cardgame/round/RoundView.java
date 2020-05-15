package com.designwork.cardgame.round;

import com.designwork.cardgame.commons.ui.AbstractView;

public class RoundView extends AbstractView {

    private final RoundPresenter roundPresenter;
    private RoundModel roundModel;
    private Integer cardChoice;

    public RoundView(RoundModel roundModel) {
        roundPresenter = new RoundPresenter(roundModel);
        addViewListener(event -> roundPresenter.handleCardPlayed(event));
    }


    public void init() {
    }
}
