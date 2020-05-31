package com.designwork.cardgame.round;

import com.designwork.cardgame.Deck;

import java.beans.PropertyChangeEvent;

public class RoundPresenter {

    private final RoundModel model;
    private final RoundView view;
    private final RoundValidator validator;

    public RoundPresenter(RoundModel model) {
        this.model = model;
        this.view = new RoundView(this);
        this.validator = new RoundValidator(model);
    }

    protected RoundPresenter(RoundModel model, RoundView roundView, RoundValidator roundValidator) {
        this.model = model;
        this.view = roundView;
        this.validator = roundValidator;
    }

    public void initialize() {
        view.addViewListener("cardPlayed", event -> handleCardPlayed(event));
        view.setCurrentPlayerName(model.getCurrentPlayer().getName());
        view.setHand(model.getCurrentPlayer().getHand());
        view.requestPlay();
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //TODO will need validation to show this play was a valid one. Probably in model?
        Integer chosenNumber = getIntegerValueForEvent(event);
        if(validator.isValidPlay(chosenNumber)) {
            recordPlayedCard(chosenNumber);
            model.advancePlayer();
            setViewForNewPlayer();
        }
        if(roundCompleted()) {
            model.setCurrentRound(model.getCurrentRound() + 1);
            model.assignTrickToWinner();
            model.createNewTrick();
        }
        if(model.getCurrentHand().size() > 0){
            view.displayCurrentTrick();
            view.requestPlay();
        }
        else if(model.hasNextRound()) {
            Deck.Deck().resetDeck();
            Deck.Deck().shuffle();
            Deck.Deck().deal(model.getPlayerModels());
            view.setCurrentPlayerName(model.getCurrentPlayer().getName());
            view.setHand(model.getCurrentPlayer().getHand());
            view.requestPlay();
        }
        else {
            view.gameOver();
        }
    }

    private void setViewForNewPlayer() {
        view.setCurrentPlayerName(model.getCurrentPlayer().getName());
        view.setHand(model.getCurrentHand());
        view.setCurrentTrick(model.getTrick());
    }

    private void recordPlayedCard(Integer chosenNumber) {
        model.recordPlayedCard(model.getCurrentHand().get(chosenNumber));
    }

    private Integer getIntegerValueForEvent(PropertyChangeEvent event) {
        return Integer.parseInt((String) event.getNewValue());
    }

    private boolean roundCompleted() {
        return model.getNumberOfPlayers() == model.getTrickSize();
    }
}
