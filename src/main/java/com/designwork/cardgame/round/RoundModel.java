package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoundModel {

    private final List<PlayerModel> playerModels;
    private Trick trick;
    private PlayerModel currentPlayer;
    private Integer currentRound;

    public RoundModel(List<PlayerModel> playerModels) {
        this(playerModels, 1, new Trick());
    }

    public RoundModel(List<PlayerModel> playerModels,
                      Integer currentRound, Trick trick) {
        this.playerModels = playerModels;
        this.trick = trick;
        this.currentPlayer = findStartingPlayer();
        this.currentRound = currentRound;
    }

    public void advancePlayer() {
        Integer indexOfNextPlayerModel = (playerModels.indexOf(currentPlayer) + 1) % playerModels.size();
        currentPlayer = playerModels.get(indexOfNextPlayerModel);
    }

    public void recordPlayedCard(Card card) {
        currentPlayer.recordPlayedCard(card);
        trick.addCardToTrick(currentPlayer.getUuid(), card);
    }

    private PlayerModel findStartingPlayer() {
        int numberOfPlayers = playerModels.size();
        if (numberOfPlayers == 5) {
            return playerModels.stream().filter(player -> player.getHand().contains(Card.ThreeClubs))
                    .collect(Collectors.toList()).get(0);
        } else {
            return playerModels.stream().filter(player ->
                    player.getHand().contains(Card.TwoClubs))
                    .collect(Collectors.toList())
                    .get(0);
        }
    }

    public void assignTrickToWinner() {
        PlayerModel playerModel = calculateTrickWinner();
        playerModel.recordTrickTaken(this.trick);
        this.currentPlayer = playerModel;
    }

    protected PlayerModel calculateTrickWinner() {
        Pair<UUID, Card> led = getLed();
        Pair<UUID, Card> winner = led;
        for (Pair<UUID, Card> pair: this.trick.getCards()) {
            if(pair.getSecond().getSuit().equals(led.getSecond().getSuit())) {
                winner = pair.getSecond().getRank().getNumber() > led.getSecond().getRank().getNumber() ? pair : led;
            }
        }
        return getPlayerModelById(winner.getFirst());
    }

    public PlayerModel getPlayerModelById(UUID uuid) {
        return this.playerModels.stream()
                .filter(playerModel -> playerModel.getUuid().equals(uuid))
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Card> getCurrentHand() {
        return this.currentPlayer.getHand();
    }

    public Integer getNumberOfPlayers () {
        return playerModels.size();
    }

    public Integer getTrickSize() {
        return trick.getCards().size();
    }

    public Pair<UUID, Card> getLed() {
        return this.trick.getCards().get(0);
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

    public Trick getTrick() {
        return this.trick;
    }

    public void createNewTrick() {
        this.trick = new Trick();
    }

    protected List<PlayerModel> getPlayerModels() {
        return Collections.unmodifiableList(this.playerModels);
    }

    public boolean hasNextRound() {
        List<PlayerModel> overOneHundred = this.playerModels.stream()
                .filter(playerModel -> playerModel.getScore() >= 100).collect(Collectors.toList());
        return overOneHundred.size() == 0;
    }
}
