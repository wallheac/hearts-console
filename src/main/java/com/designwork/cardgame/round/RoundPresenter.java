package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.trick.TrickPresenter;
import com.designwork.cardgame.trick.TrickView;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class RoundPresenter {

    private final RoundModel roundModel;
    private final RoundView view;
    private final TrickPresenter trickPresenter;

    public RoundPresenter(RoundModel roundModel) {
        this.roundModel = roundModel;
        TrickView trickView = new TrickView();
        this.view = new RoundView(this, trickView);
        this.trickPresenter = new TrickPresenter(trickView, roundModel.getTrickModel());
    }

    protected RoundPresenter(RoundModel roundModel, RoundView roundView, TrickView trickView, TrickPresenter trickPresenter) {
        this.roundModel = roundModel;
        this.view = roundView;
        this.trickPresenter = new TrickPresenter(trickView, roundModel.getTrickModel());
    }

    public void initialize() {
        view.addViewListener("cardPlayed", event -> handleCardPlayed(event));
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        view.setHand(roundModel.getCurrentPlayer().getHand());
        view.requestPlay();
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //TODO - this has become a god-method. STOP IT
        //TODO will need validation to show this play was a valid one. Probably in model?
        Integer chosenNumber = Integer.parseInt((String) event.getNewValue());
        List<Card> hand = roundModel.getCurrentPlayer().getHand();
        trickPresenter.addPlayedCardToTrick(roundModel.getCurrentPlayer().getUuid(), hand.get(chosenNumber));
        roundModel.recordPlayedCard(hand.get(chosenNumber));
        //TODO - this is a new player. Different thing. Probably different method
        roundModel.advancePlayer();
        view.setCurrentPlayerName(roundModel.getCurrentPlayer().getName());
        view.setHand(roundModel.getCurrentPlayer().getHand());

        if(roundCompleted()) {
            roundModel.setCurrentRound(roundModel.getCurrentRound() + 1);
        }
        if(roundModel.getCurrentPlayer().getHand().size() > 0){
            trickPresenter.handleTrickDisplay();
            view.requestPlay();
        }
    }

    private boolean roundCompleted() {
        return roundModel.getNumberOfPlayers() == roundModel.getTrickSize();
    }
}
