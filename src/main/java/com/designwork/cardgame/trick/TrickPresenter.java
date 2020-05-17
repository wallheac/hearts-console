package com.designwork.cardgame.trick;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.card.Card;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TrickPresenter {
    private final TrickView trickView;
    private final TrickModel trickModel;

    public TrickPresenter(TrickView trickView, TrickModel trickModel) {
        this.trickView = trickView;
        this.trickModel = trickModel;
        trickView.setCurrentTrick(getCards(trickModel.getCurrentTrick()));
    }

    private List<Card> getCards(List<Pair<UUID, Card>> currentTrick) {
        return currentTrick.stream()
                .map(Pair::getSecond)
                .collect(Collectors.toList());
    }

    public void handleTrickDisplay() {
        trickView.displayCurrentTrick();
    }

    public void addPlayedCardToTrick(UUID uuid, Card card) {
        trickModel.addCardToCurrentTrick(uuid, card);
        trickView.setCurrentTrick(getCards(trickModel.getCurrentTrick()));
    }
}
