package com.designwork.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hearts extends Game {

    private final int rounds;
    private Player activePlayer;
    private Pair<Player, Card> led;
    private List<Pair<Player, Card>> currentTrick = new ArrayList<>();
    private Integer currentRound;
    private Deck deck;

    public Hearts(List<Player> players, Scoreboard scoreboard) {
        super(players, scoreboard);
        this.currentRound = 0;
        this.rounds = 52 / players.size();
        this.deck = prepDeck(new Deck(), players.size());
    }

    protected Hearts(List<Player> players, Scoreboard scoreboard, int rounds) {
        super(players, scoreboard);
        this.rounds = rounds;
        this.currentRound = 0;
        this.in = in;
    }

    private Deck prepDeck(Deck deck, int numberOfPlayers) {
        if (numberOfPlayers == 3 || numberOfPlayers == 5) {
            deck.removeCard(Suit.DIAMONDS, Rank.TWO);
        }
        if (numberOfPlayers == 5) {
            deck.removeCard(Suit.CLUBS, Rank.TWO);
        }
        deck.shuffle();
        return deck;
    }

    public void play() {
        //active player needs to be the person with the 2 of C (or, if 3 person game, the 3 of C)
        activePlayer = findStartingPlayer();
        System.out.println("First lead must be the " + (players.size() == 5 ? "3 of Clubs" : "2 of Clubs"));
        for (int i = 0; i < rounds; i++) {
            playRound();
            currentRound++;
        }
        scoreboard.calculateScoreForRound(players);
    }

    private void playRound() {
        for (int i = 0; i < players.size(); i++) {
            printCurrentRoundState();
            //TODO: for later - validate play (does this abide by the rules of the  game?)
            Card card = requestPlay(activePlayer);
            if (i == 0) {
                led = new Pair(activePlayer, card);
            }
            currentTrick.add(new Pair(activePlayer, card));
            activePlayer = players.get((players.indexOf(activePlayer) + 1) % (players.size()));
        }

        Pair<Player, Card> winner = scoreboard.determineTrickWinner(currentTrick, led);
        winner.getFirst().getTricksTaken().add(winner);
        System.err.println("winner of round is: " + winner.getFirst().getName() + " with the: " + winner.getSecond().prettyPrint());
        activePlayer = winner.getFirst();
        resetTrick();
    }

    private Card requestPlay(Player player) {
        System.out.println(player.getName() + ", what card would you like to play?\n");
        player.printHand();
        Integer input = requestInput();
        Card played = player.getHand().get(input - 1);
        player.getHand().remove(input - 1);
        return played;
    }

    public int requestInput() {
        Integer input = null;
        Scanner sc;
        try {
            sc = new Scanner(System.in);
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println("please enter a number");
        }
        return input;
    }

    private Player findStartingPlayer() {
        int numberOfPlayers = players.size();
        if (numberOfPlayers == 5) {
            return players.stream()
                    .filter(player -> player.getHand()
                            .contains(Card.ThreeClubs))
                    .collect(Collectors.toList()).get(0);
        } else {
            return players.stream()
                    .filter(player -> player.getHand()
                            .contains(Card.TwoClubs))
                    .collect(Collectors.toList()).get(0);
        }
    }
    private void resetTrick() {
        currentTrick = new ArrayList<>();
    }

    private void printCurrentRoundState() {
        System.out.println("Cards On Table: ");
        currentTrick.stream().forEach(playerCardPair -> System.out.println(playerCardPair.getSecond().prettyPrint()));
    }
    public void deal() {
        int numberOfPlayers = players.size();
        int deckSize = deck.getCards().size();
        players.stream().forEach(player -> player.drawHand(deck, deckSize / numberOfPlayers));
    }

    public void resetGame() {
        this.currentRound = 0;
        this.players = Collections.emptyList();
    }

    public Integer getRound() {
        return currentRound;
    }

    public Integer getRounds() {
        return rounds;
    }

    public Pair<Player, Card> getLed () {
        return led;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
