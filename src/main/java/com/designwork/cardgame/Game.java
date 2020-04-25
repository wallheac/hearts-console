package com.designwork.cardgame;

import java.util.List;

public class Game {
    List<Player> players;
    Scoreboard scoreboard;

    public Game(List<Player> players, Scoreboard scoreboard) {
        this.players = players;
        this.scoreboard = scoreboard;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
