package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.card.Suit;

import java.util.List;
import java.util.stream.Collectors;

public class RoundValidator {

    private final RoundModel model;

    public RoundValidator(RoundModel model) {
        this.model = model;
    }

    public boolean isValidPlay(Integer chosenNumber) {
        Card card = model.getCurrentHand().get(chosenNumber);
        if (model.getCurrentRound() == 1) {
            if (model.getTrickSize() == 0 && !Card.TwoClubs.equals(card)) {
                return false;
            }
            if (Suit.HEARTS.equals(card.getSuit()) || Card.QueenSpades.equals(card)) {
                return false;
            }
        }
        //can only lead hearts after a heart has been played (unless player has nothing but hearts)
        if (heartsLed(card) && !(model.heartsBroken() || hasOnlyHearts())) {
            return false;
        }
        return model.getTrickSize() <= 0 || !sameSuitInHand(model.getTrick().getLed()) || followsSuit(card);
    }

    private boolean heartsLed(Card card) {
        return model.getTrickSize() == 0 && card.getSuit().equals(Suit.HEARTS);
    }

    private boolean hasOnlyHearts() {
        List<Card> nonHearts = model.getCurrentHand().stream()
                .filter(card -> !card.getSuit().equals(Suit.HEARTS))
                .collect(Collectors.toList());
        return nonHearts.isEmpty();
    }

    private boolean followsSuit(Card card) {
        Card led = model.getTrick().getLed();
        return led.getSuit().equals(card.getSuit());
    }

    private boolean sameSuitInHand(Card card) {
        List<Card> sameSuitInHand = model.getCurrentHand().stream()
                .filter(card1 -> card.getSuit().equals(card1.getSuit()))
                .collect(Collectors.toList());

        return sameSuitInHand.size() > 0;
    }
}
