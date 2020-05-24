package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;
import com.designwork.cardgame.Trick;

import java.util.List;

public class RoundView extends AbstractView {

    private String currentPlayerName;
    private List<Card> hand;
    private Trick currentTrick;

    public RoundView(RoundPresenter roundPresenter) {
//        addViewListener("cardPlayed", roundPresenter::handleCardPlayed);
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

    public void displayCurrentTrick() {
        System.out.println("Current trick: ");
        currentTrick.getCards().stream().forEach(card -> System.out.println(card.getSecond().prettyPrint()));
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setCurrentTrick(Trick trick) {
        this.currentTrick = trick;
    }
}
