package com.designwork.cardgame.trick;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrickModel {

    List<List<Pair<UUID, Card>>> tricks;
    List<Pair<UUID, Card>> currentTrick;

    public TrickModel() {
        this.tricks = new ArrayList<>();
        this.currentTrick = new ArrayList<>();
    }

    protected TrickModel(List<List<Pair<UUID, Card>>> tricks, List<Pair<UUID, Card>> currentTrick) {
        this.tricks = tricks;
        this.currentTrick = currentTrick;
    }

    public void addCardToCurrentTrick(UUID playerUuid, Card card) {
        currentTrick.add(Pair.of(playerUuid, card));
    }

    public List<Pair<UUID, Card>> getCurrentTrick() {
        return currentTrick;
    }
}
