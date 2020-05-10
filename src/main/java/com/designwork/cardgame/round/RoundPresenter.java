package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;

import java.beans.PropertyChangeEvent;

public class RoundPresenter {

    private final RoundModel roundModel;
    private final RoundView roundView;

    public RoundPresenter(RoundModel roundModel, RoundView roundView) {
        this.roundModel = roundModel;
        this.roundView = roundView;
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //will need validation to show this play was a valid one. Probably in model?
        roundModel.advancePlayer();
        Integer chosenNumber = (Integer) event.getNewValue();
        //look up card

        roundModel.addPlayedCardToTrick(Card.ThreeClubs);
    }
}
