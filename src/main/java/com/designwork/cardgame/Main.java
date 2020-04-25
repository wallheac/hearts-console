package com.designwork.cardgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Step one:
 *   -- create deck, create players, deal 13 cards to 4 players, display each player's hand
 *
 * Hearts
 *  -- Players (Human, NetworkHuman, AI)
 *  -- Cards
 *  -- Shuffle the deck
 *  -- Deal cards to players
 *  -- Rules/Settings/something (?)
 */

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.addAll(Arrays.asList(new Player("First"), new Player("Second"), new Player("Third"), new Player("Fourth")));
        Hearts hearts = new Hearts(players, new Scoreboard());
        hearts.deal();
        hearts.play();
    }
}
