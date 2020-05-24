package com.designwork.cardgame;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trick {
    private List<Pair<UUID, Card>> cards;

    public Trick() {
        cards = new ArrayList();
    }

    public Trick(Pair<UUID, Card>... cards) {
        this.cards = new ArrayList<>();
        for (Pair<UUID, Card> pair : cards) {
            this.cards.add(pair);
        }
    }

    public void addCardToTrick(UUID playerUuid, Card card) {
        cards.add(Pair.of(playerUuid, card));
    }

    public List<Pair<UUID, Card>> getCards() {
        return cards;
    }

    public Integer getSize() {
        return cards.size();
    }
}
