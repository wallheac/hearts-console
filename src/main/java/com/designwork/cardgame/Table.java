package com.designwork.cardgame;

import java.util.Collections;
import java.util.List;
import java.util.Observer;

public class Table {

    private Player activePlayer;
    private Card led;
    private List<Card> currentTrick;
    private Integer round = 0;
    private List<Player> players;

    public Table(List<Player> players) {
        this.players = players;
    }

    public void play() {
        for (int i = 0; i < 13; i++) {
            playRound(i % players.size());
            round++;
        }
    }

    private void playRound(int indexOfStartingPlayer) {
        for (int i = 0; i < players.size(); i++) {
            Card card;
            if (indexOfStartingPlayer == 0) {
                card = players.get(i).requestPlay();
            } else {
                card = players.get(i % indexOfStartingPlayer).requestPlay();
            }
            if(i == 0) {
                this.led = card;
                this.currentTrick.add(card);
            }
            this.currentTrick.add(card);

        }
    }

    //wrote this assuming I'd want it at some point
    public void resetTable() {
        this.round = 0;
        this.players = Collections.emptyList();
    }

    public Integer getRound() {
        return round;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
