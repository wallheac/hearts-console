package com.designwork.cardgame.player;

import com.designwork.cardgame.hand.HandView;

public class PlayerView {
    private final HandView handView;
    //HandView - specific to a player

    //MyTricksView - specific to a player
    public PlayerView() {
        this(new HandView());
    }

    protected PlayerView(HandView handView) {
        this.handView = handView;
    }

    public void init() {

    }
}
