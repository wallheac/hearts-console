package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.trick.TrickModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoundModel {

    private List<PlayerModel> playerModels;
    private TrickModel trickModel;
    private Card led;
    private PlayerModel currentPlayer;
    private Integer currentRound;

    public RoundModel() {
        this(new ArrayList<>(), new TrickModel(),
                null, null, 1);
    }

    public RoundModel(List<PlayerModel> playerModels, TrickModel trickModel,
                      Card led, UUID currentPlayer, Integer currentRound) {
        this.playerModels = playerModels;
        this.trickModel = trickModel;
        this.led = led;
        this.currentPlayer = findStartingPlayer();
        this.currentRound = currentRound;
    }

    public void advancePlayer() {
        Integer indexOfNextPlayerModel = (playerModels.indexOf(currentPlayer) + 1) % playerModels.size();
        currentPlayer = playerModels.get(indexOfNextPlayerModel);
    }

    public void addPlayedCardToTrick(Card card) {
        currentPlayer.recordPlayedCard(card);
        trickModel.addCardToCurrentTrick(card);
    }

    private PlayerModel findStartingPlayer() {
        int numberOfPlayers = playerModels.size();
        if (numberOfPlayers == 5) {
            return playerModels.stream().filter(player -> player.getHand().contains(Card.ThreeClubs))
                    .collect(Collectors.toList()).get(0);
        } else {
            return playerModels.stream().filter(player -> player.getHand().contains(Card.TwoClubs))
                    .collect(Collectors.toList()).get(0);
        }
    }


    public Card getLed() {
        return led;
    }

    public void setLed(Card led) {
        this.led = led;
    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Integer getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Integer currentRound) {
        this.currentRound = currentRound;
    }
}
