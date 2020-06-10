package com.designwork.cardgame.hearts;

import com.designwork.cardgame.Deck;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.round.RoundModel;
import com.designwork.cardgame.round.RoundPresenter;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class HeartsPresenter {
    private final HeartsView heartsView;
    private RoundModel roundModel;
    private final List<PlayerModel> playerModels;
    private String additionalPlayer;

    public HeartsPresenter() {
        this(new HeartsView(), new ArrayList<>(), "n");
    }

    protected HeartsPresenter(HeartsView heartsView, List<PlayerModel> playerModels, String additionalPlayer) {
        this.heartsView = heartsView;
        this.playerModels = playerModels;
        this.additionalPlayer = additionalPlayer;
    }

    public void initialize() {
        heartsView.addViewListener("playerAdded", this::handlePlayerAdded);
        heartsView.addViewListener("another", this::handleAddingAnotherPlayer);
        registerPlayers();
        Deck.Deck().shuffle();
        Deck.Deck().deal(playerModels);
        roundModel = new RoundModel(playerModels);
        new RoundPresenter(roundModel).initialize();
    }

    public void handlePlayerAdded(PropertyChangeEvent event) {
        addPlayerModel((String) event.getNewValue());
    }

    public void handleAddingAnotherPlayer(PropertyChangeEvent event) {
        additionalPlayer = (String) event.getNewValue();
    }

    private void addPlayerModel(String name) {
        playerModels.add(new PlayerModel(name));
    }

    protected void registerPlayers() {
        do {
            heartsView.requestPlayerName();
            if (playerModels.size() < 5) {
                heartsView.requestAdditionalPlayers();
            }
        } while ("y".equals(additionalPlayer) && playerModels.size() < 5);
    }
}
