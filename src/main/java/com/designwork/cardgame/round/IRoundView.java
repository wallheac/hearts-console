package com.designwork.cardgame.round;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.View;

import java.util.List;

public interface IRoundView extends View {

    void requestPlay();

    void gameOver(String name);

    void displayHand();

    void displayCurrentTrick();

    default void respondInvalidChoice() {
        System.out.println("You chose an invalid card. Please try again");
    }

  void setCurrentPlayerName(String currentPlayerName);

    void setHand(List<Card> hand);

    void setCurrentTrick(Trick trick);

    void announceTrickWinner(String name);
}
