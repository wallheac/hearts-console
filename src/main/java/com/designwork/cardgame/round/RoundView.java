package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;
import com.designwork.cardgame.trick.TrickView;

import java.util.List;

public class RoundView extends AbstractView {

    private Integer cardChoice;
    private final TrickView trickView;
    private String currentPlayerName;
    private List<Card> hand;

    public RoundView(RoundPresenter roundPresenter, TrickView trickView) {
//        addViewListener("cardPlayed", roundPresenter::handleCardPlayed);
        this.trickView = new TrickView();
    }

    public void requestPlay() {
        System.out.println("Current Player: " + currentPlayerName);
        System.out.println("Please choose a card to play: ");
        displayHand();
        Integer cardNumber = ConsoleInputUtil.requestNumericInput();
        setValue("cardPlayed", null, cardNumber.toString());
    }

    private void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ". " + hand.get(i).prettyPrint());
        }
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
