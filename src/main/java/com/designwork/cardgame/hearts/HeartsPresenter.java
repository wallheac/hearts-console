package com.designwork.cardgame.hearts;

import com.designwork.cardgame.Deck;
import com.designwork.cardgame.commons.ui.View;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.round.IRoundView;
import com.designwork.cardgame.round.RoundModel;
import com.designwork.cardgame.round.RoundPresenter;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class HeartsPresenter {

    private final View heartsView;
    private final IRoundView roundView;
    private final RoundModel roundModel;
    private final List<PlayerModel> playerModels;

    public HeartsPresenter(View heartsView) {

        this(heartsView, new RoundModel(), null);
    }

    protected HeartsPresenter(View heartsView, RoundModel roundModel, IRoundView roundView) {
        this.heartsView = heartsView;
        this.roundModel = roundModel;
        this.playerModels = roundModel.getPlayerModels();
        this.roundView = roundView;
    }

    public void initializeView() {
        heartsView.addViewListener("playerAdded", this::handlePlayerAdded);
        heartsView.addViewListener("submit", this::handleSubmit);
    }

    public void handlePlayerAdded(PropertyChangeEvent event) {
        addPlayerModel((String) event.getNewValue());
    }

    public void handleSubmit(PropertyChangeEvent event) {
        Deck.Deck().shuffle();
        Deck.Deck().deal(playerModels);
        roundModel.setPlayerModels(playerModels);
        roundModel.setCurrentPlayer(roundModel.findStartingPlayer());
        new RoundPresenter(roundModel, roundView).initialize();
    }

    private void addPlayerModel(String name) {
        playerModels.add(new PlayerModel(name));
    }
}
