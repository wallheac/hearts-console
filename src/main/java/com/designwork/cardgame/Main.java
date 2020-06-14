package com.designwork.cardgame;

import com.designwork.cardgame.hearts.GameView;
import com.designwork.cardgame.hearts.HeartsGuiView;
import com.designwork.cardgame.hearts.HeartsPresenter;

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
//        HeartsPresenter heartsPresenter = new HeartsPresenter(new HeartsGuiView());
//        heartsPresenter.initializeView();
        new GameView().initialize();
    }
}
