package com.designwork.cardgame.trick;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.card.Card;

import java.util.List;
import java.util.UUID;

public class TrickModel {

    List<List<Pair<UUID, Card>>> tricks;
    List<Pair<UUID, Card>> currentTrick;

    public TrickModel() {
    }

    protected TrickModel(List<List<Pair<UUID, Card>>> tricks, List<Pair<UUID, Card>> currentTrick) {
        this.tricks = tricks;
        this.currentTrick = currentTrick;
    }

    public void addCardToCurrentTrick(Card card) {

    }
}
