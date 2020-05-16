package com.designwork.cardgame.round;

import java.beans.PropertyChangeEvent;

public class RoundPresenter {

    private final RoundModel roundModel;
    private final RoundView view;

    public RoundPresenter(RoundModel roundModel) {
        this.roundModel = roundModel;
        this.view = new RoundView(this);
    }

    public RoundPresenter(RoundModel roundModel, RoundView roundView) {
        this.roundModel = roundModel;
        this.view = roundView;
    }

    public void initialize() {
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        view.setHand(roundModel.getCurrentPlayer().getHand());
        view.requestPlay();
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //TODO will need validation to show this play was a valid one. Probably in model?
        Integer chosenNumber = Integer.parseInt((String) event.getNewValue());
        roundModel.addPlayedCardToTrick(roundModel.getCurrentPlayer().getHand().get(chosenNumber));
        roundModel.advancePlayer();
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        //TODO - stop the endless requests
        view.requestPlay();
    }
}
