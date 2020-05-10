package com.designwork.cardgame;

import com.designwork.cardgame.player.PlayerModel;

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
        List<PlayerModel> playerModels = new ArrayList<>();
        playerModels.addAll(Arrays.asList(new PlayerModel("First"), new PlayerModel("Second"), new PlayerModel("Third"), new PlayerModel("Fourth")));
        Deck.Deck().shuffle();
        Deck.Deck().deal(playerModels);
//        players.addAll(Arrays.asList(
//                new Player("Ted", Card.TwoClubs, new Card(Suit.HEARTS, Rank.TEN)),
//                new Player("Fred", Card.ThreeClubs, new Card(Suit.HEARTS, Rank.SIX)),
//                new Player("Amy", Card.FourClubs, new Card(Suit.DIAMONDS, Rank.TEN)),
//                new Player("Charlotte", Card.FiveClubs, new Card(Suit.SPADES, Rank.QUEEN))));

    }
}
