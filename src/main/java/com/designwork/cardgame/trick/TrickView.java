package com.designwork.cardgame.trick;

import com.designwork.cardgame.card.Card;

import java.util.List;

public class TrickView {

    private TrickPresenter trickPresenter;
    private List<Card> currentTrick;

    public TrickView() {
    }

    public TrickView(TrickPresenter trickPresenter) {
        this.trickPresenter = trickPresenter;
    }

    public void displayCurrentTrick() {
        System.out.println("Current trick: ");
        currentTrick.stream().forEach(card -> System.out.println(card.prettyPrint()));
    }

    public void setCurrentTrick(List<Card> trick) {
        this.currentTrick = trick;
    }

}
