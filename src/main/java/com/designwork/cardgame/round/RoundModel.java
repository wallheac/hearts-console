package com.designwork.cardgame.round;

import com.designwork.cardgame.Pair;
import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.card.Suit;
import com.designwork.cardgame.player.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoundModel {

    private List<PlayerModel> playerModels;
    private Trick trick;
    private PlayerModel currentPlayer;
    private Integer currentRound;

    public RoundModel() {
        this(new ArrayList<>(), 1, new Trick());
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

    public PlayerModel findStartingPlayer() {
        if (playerModels.size() > 0) {
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
        return null;
    }

    public void assignTrickToWinner() {
        PlayerModel playerModel = calculateTrickWinner();
        playerModel.recordTrickTaken(this.trick);
        this.currentPlayer = playerModel;
    }

    protected PlayerModel calculateTrickWinner() {
        Pair<UUID, Card> led = getLed();
        Pair<UUID, Card> winner = led;
        for (Pair<UUID, Card> pair : this.trick.getCards()) {
            if (pair.getSecond().getSuit().equals(led.getSecond().getSuit())) {
                winner = pair.getSecond().getRank().getNumber() > winner.getSecond().getRank().getNumber() ? pair : winner;
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

    public boolean currentPlayerIsStartingPlayer() {
        return this.currentPlayer.equals(findStartingPlayer());
    }

    public boolean hasNextRound() {
        PlayerModel overOneHundred = getGameWinner();
        return overOneHundred == null;
    }

    public PlayerModel getGameWinner() {
        return this.playerModels.stream()
                .filter(playerModel -> playerModel.getScore() >= 100)
                .findFirst()
                .orElse(null);
    }

    public Integer getTrickSize() {
        return trick.getCards().size();
    }

    public boolean heartsBroken() {
        List<PlayerModel> playedHearts = this.playerModels.stream()
                .filter(playerModel -> playerModel.getCardsPlayed().stream()
                        .anyMatch(card -> card.getSuit().equals(Suit.HEARTS))).collect(Collectors.toList());
        return !playedHearts.isEmpty();
    }

    public void createNewTrick() {
        this.trick = new Trick();
    }

    public List<Card> getCurrentHand() {
        return this.currentPlayer.getHand();
    }

    public Integer getNumberOfPlayers() {
        return playerModels.size();
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

    public List<PlayerModel> getPlayerModels() {
        return this.playerModels;
    }

    public void setPlayerModels(List<PlayerModel> playerModels) {
        this.playerModels = playerModels;
    }
}
