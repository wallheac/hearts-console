package com.designwork.cardgame;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.card.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Trick {
    private final List<Pair<UUID, Card>> cards;

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

    public boolean containsHeart() {
        List<Pair<UUID, Card>> penalties = this.getCards().stream()
                .filter(pair -> pair.getSecond().getSuit().equals(Suit.HEARTS)).collect(Collectors.toList());
        return penalties.size() > 0;
    }

    public boolean containsQueenOfSpades() {
        List<Pair<UUID, Card>> penalties = this.getCards().stream()
                .filter(pair -> pair.getSecond().equals(Card.QueenSpades)).collect(Collectors.toList());
        return penalties.size() > 0;
    }

    public Integer getSize() {
        return cards.size();
    }

    public Card getLed() {
        return cards.get(0).getSecond();
    }
}
