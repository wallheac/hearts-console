package com.designwork.cardgame.hearts;

import com.designwork.cardgame.Deck;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.round.RoundModel;
import com.designwork.cardgame.round.RoundPresenter;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class HeartsPresenter {
    private final IHeartsView heartsView;
    private RoundModel roundModel;
    private final List<PlayerModel> playerModels;

    public HeartsPresenter(IHeartsView heartsView) {
        this(heartsView, new ArrayList<>());
    }

    protected HeartsPresenter(IHeartsView heartsView, List<PlayerModel> playerModels) {
        this.heartsView = heartsView;
        this.playerModels = playerModels;
    }

    public void initialize() {
        heartsView.addViewListener("playerAdded", this::handlePlayerAdded);
        heartsView.addViewListener("submit", this::handleSubmit);
        heartsView.initialize();
    }

    public void handlePlayerAdded(PropertyChangeEvent event) {
        addPlayerModel((String) event.getNewValue());
    }

    public void handleSubmit(PropertyChangeEvent event) {
        Deck.Deck().shuffle();
        Deck.Deck().deal(playerModels);
        roundModel = new RoundModel(playerModels);
        new RoundPresenter(roundModel).initialize();
    }

    private void addPlayerModel(String name) {
        playerModels.add(new PlayerModel(name));
    }
}
