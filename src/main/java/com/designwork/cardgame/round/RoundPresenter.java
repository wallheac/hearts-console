package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;

import java.beans.PropertyChangeEvent;

public class RoundPresenter {

    private final RoundModel roundModel;

    public RoundPresenter(RoundModel roundModel) {
        this.roundModel = roundModel;
    }

    public void handleCardPlayed(PropertyChangeEvent event) {
        //will need validation to show this play was a valid one. Probably in model?
        Integer chosenNumber = (Integer) event.getNewValue();
        //look up card

        roundModel.addPlayedCardToTrick(Card.ThreeClubs);
        roundModel.advancePlayer();
    }
}
