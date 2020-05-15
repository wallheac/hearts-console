package com.designwork.cardgame.hearts;

import com.designwork.cardgame.Deck;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;
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
    private String additionalPlayer = "n";

    public HeartsPresenter() {
        this(new HeartsView());
    }

    protected HeartsPresenter(HeartsView heartsView) {
        this.heartsView = heartsView;
        this.playerModels = new ArrayList<>();
    }

    public void initialize() {
        heartsView.addViewListener(this::handlePlayerAdded);
        heartsView.addViewListener(this::handleAddingAnotherPlayer);
        registerPlayers();
        Deck.Deck().shuffle();
        Deck.Deck().deal(playerModels);
        roundModel = new RoundModel(playerModels);
        RoundPresenter roundPresenter = new RoundPresenter(roundModel);
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

    private void registerPlayers() {
        do {
            heartsView.requestPlayerName();
            if (playerModels.size() < 4) {
                heartsView.requestAdditionalPlayers();
            }
        } while ("y".equals(additionalPlayer) && playerModels.size() < 4);
    }
}
