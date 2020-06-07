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
        Integer chosenNumber = getIntegerValueForEvent(event);
        while(!validator.isValidPlay(chosenNumber)) {
            view.respondInvalidChoice();
            view.displayCurrentTrick();
            view.requestPlay();
        }
        recordPlayedCard(chosenNumber);
        model.advancePlayer();
        setViewForNewPlayer();
        if(roundCompleted()) {
            model.setCurrentRound(model.getCurrentRound() + 1);
            model.assignTrickToWinner();
            view.announceTrickWinner(model.getCurrentPlayer().getName());
            model.createNewTrick();
        }
        if(model.getCurrentHand().size() > 0){
            view.setCurrentPlayerName(model.getCurrentPlayer().getName());
            view.setHand(model.getCurrentPlayer().getHand());
            view.setCurrentTrick(model.getTrick());
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
            view.gameOver(model.getGameWinner().getName());
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
