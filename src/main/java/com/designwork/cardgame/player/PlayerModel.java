package com.designwork.cardgame.player;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerModel {
    private static final Integer QUEEN_OF_SPADES_PENALTY_AMOUNT = 13;

    private String name;
    private final UUID uuid;
    private List<Card> startingHand = new ArrayList<>();
    private List<Card> cardsPlayed = new ArrayList<>();
    private List<Trick> tricksTaken = new ArrayList<>();

    public PlayerModel(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public PlayerModel(String name, UUID uuid, List<Card> cardsPlayed, List<Trick> tricksTaken, Card... cards) {
        this.name = name;
        this.uuid = uuid;
        this.cardsPlayed = cardsPlayed;
        for (Card card : cards) {
            this.startingHand.add(card);
        }
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(
                startingHand.stream().filter(card -> !cardsPlayed.contains(card)).collect(Collectors.toList()));
    }

    public void recordPlayedCard(Card card) {
        cardsPlayed.add(card);
    }

    public void setStartingHand(List<Card> startingHand) {
        this.startingHand = Collections.unmodifiableList(startingHand);
    }

    public void recordTrickTaken(Trick trick) {
        this.tricksTaken.add(trick);
    }

    public Integer getScore() {
        Integer score = 0;
        for (Trick trick: this.tricksTaken) {
            if(trick.containsHeart()) {
                score++;
            }
            if(trick.containsQueenOfSpades()) {
                score += QUEEN_OF_SPADES_PENALTY_AMOUNT;
            }
        }
        return score;
    }

    public boolean printHand() {
        List<Card> hand = this.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i).prettyPrint());
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    // TODO - consider this implementation
    /*
     * public Iterator<Card> getHand() { return
     * Collections.unmodifiableList(hand).iterator(); } public Card playCard(int
     * indexIntoHand) { return hand.remove(indexIntoHand);
     *
     *
     */
}
