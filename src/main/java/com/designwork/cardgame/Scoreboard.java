package com.designwork.cardgame;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.card.Rank;
import com.designwork.cardgame.card.Suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {

//    private Map<Player, Integer> scoreMap = new HashMap<>();
//    private Integer highScore = 0;
//    private Player winningPlayer;
//
//    public Scoreboard(List<Player> players) {
//        if(players != null) {
//            players.stream().forEach(player -> scoreMap.put(player, 0));
//        }
//    }
//
//    public Pair<Player, Card> determineTrickWinner(List<Pair<Player, Card>> currentTrick, Pair<Player, Card> led) {
//        Pair<Player, Card> winner = currentTrick.get(0);
//        for (Pair<Player, Card> pair : currentTrick) {
//            if(suitIsEqual(pair, led) && rankIsHigher(pair, winner)) {
//                winner = pair;
//            }
//        }
//        return winner;
//    }
//
//    public Map<Player, Integer> calculateScoreForRound(List<Player> players) {
//        players.forEach(player -> player.getTricksTaken()
//                .stream()
//                .forEach(playerCardPair -> {
//                    Card card = playerCardPair.getSecond();
//                    Player currentPlayer = playerCardPair.getFirst();
//                    Integer currentScore = scoreMap.get(currentPlayer);
//                    if(card.getSuit().equals(Suit.HEARTS) ) {
//                        scoreMap.put(currentPlayer, (currentScore++));
//                    } else if (isQueenOfSpades(card)) {
//                        scoreMap.put(currentPlayer, currentScore + 13);
//                    }
//                }));
//
//        Player winner = null;
//        Integer highScore = 0;
//        for(Map.Entry<Player, Integer> entry : scoreMap.entrySet()) {
//            if(entry.getValue() > highScore) {
//                highScore = entry.getValue();
//                winner = entry.getKey();
//            }
//        }
//        this.highScore = highScore;
//        if(highScore >= 100) {
//            this.winningPlayer = winner;
//        }
//        System.out.println(winner.getName() + " wins round and has " + highScore + " points");
//        return scoreMap;
//    }
//
//    private boolean isQueenOfSpades(Card card) {
//        return card.getSuit().equals(Suit.SPADES) && card.getRank().equals(Rank.QUEEN);
//    }
//
//    private boolean rankIsHigher(Pair<Player, Card> currentCard, Pair<Player, Card> currentLeader) {
//        return currentCard.getSecond().getRank().getNumber() > currentLeader.getSecond().getRank().getNumber();
//    }
//
//    private boolean suitIsEqual(Pair<Player, Card> pair, Pair<Player, Card> led) {
//        return pair.getSecond().getSuit().equals(led.getSecond().getSuit());
//    }
//
//    public Integer getHighScore() {
//        return highScore;
//    }
//
//    public Player getWinningPlayer() {
//        return winningPlayer;
//    }
}
