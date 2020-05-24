package com.designwork.cardgame.round;

import java.beans.PropertyChangeEvent;

public class RoundPresenter {

    private final RoundModel roundModel;
    private final RoundView view;

    public RoundPresenter(RoundModel roundModel) {
        this.roundModel = roundModel;
        this.view = new RoundView(this);
    }

    protected RoundPresenter(RoundModel roundModel, RoundView roundView) {
        this.roundModel = roundModel;
        this.view = roundView;
    }

    public void initialize() {
        view.addViewListener("cardPlayed", event -> handleCardPlayed(event));
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        view.setHand(roundModel.getCurrentPlayer().getHand());
        view.requestPlay();
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //TODO will need validation to show this play was a valid one. Probably in model?
        recordPlayedCard(event);
        roundModel.advancePlayer();
        setViewForNewPlayer();

        if(roundCompleted()) {
            roundModel.setCurrentRound(roundModel.getCurrentRound() + 1);
            roundModel.assignTrickToWinner();
            roundModel.createNewTrick();
        }
        if(roundModel.getCurrentHand().size() > 0){
            view.displayCurrentTrick();
            view.requestPlay();
        }
    }

    private void setViewForNewPlayer() {
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        view.setHand(roundModel.getCurrentHand());
        view.setCurrentTrick(roundModel.getTrick());
    }

    private void recordPlayedCard(PropertyChangeEvent event) {
        Integer chosenNumber = Integer.parseInt((String) event.getNewValue());
        roundModel.recordPlayedCard(roundModel.getCurrentHand().get(chosenNumber));
    }

    private boolean roundCompleted() {
        return roundModel.getNumberOfPlayers() == roundModel.getTrickSize();
    }
}
