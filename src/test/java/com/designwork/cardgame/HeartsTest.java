package com.designwork.cardgame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class HeartsTest {

    private Hearts hearts;
    private List<Player> players = new ArrayList<>();

    @Before
    public void setup() {

    }

    @Test
    public void resetTableResetsRoundsToZeroAndPlayersToEmptyList() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred")));
        hearts = new Hearts(players, new Scoreboard());

        hearts.resetGame();

        assert (hearts.getRound().equals(0));
        assertThat(hearts.getPlayers().size(), is(0));
    }

    @Test
    public void removesTwoOfDiamondsWhenThreePlayers() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred")));
        hearts = new Hearts(players, new Scoreboard());

        assertThat(hearts.getDeck().getCards().size(), is(51));
        assertThat(hearts.getDeck().getCards(), not(hasItem(new Card(Suit.DIAMONDS, Rank.TWO))));
    }

    @Test
    public void removesTwoOfDiamondsAndTwoOfClubsWhenFivePlayers() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred"),
                new Player("Amy"), new Player("Jurgen")));
        hearts = new Hearts(players, new Scoreboard());

        assertThat(hearts.getDeck().getCards().size(), is(50));
        assertThat(hearts.getDeck().getCards(), not(hasItem(new Card(Suit.DIAMONDS, Rank.TWO))));
        assertThat(hearts.getDeck().getCards(), not(hasItem(new Card(Suit.CLUBS, Rank.TWO))));
    }

    @Test
    public void dealsTenCardsWhenFivePlayers() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred"),
                new Player("Amy"), new Player("Jurgen")));
        hearts = new Hearts(players, new Scoreboard());

        hearts.deal();

        assertThat(players.get(0).getHand().size(), is(10));
        assertThat(players.get(1).getHand().size(), is(10));
        assertThat(players.get(2).getHand().size(), is(10));
        assertThat(players.get(3).getHand().size(), is(10));
        assertThat(players.get(4).getHand().size(), is(10));
        assertThat(hearts.getRounds(), is(10));
    }

    @Test
    public void dealsThirteenCardsWhenFourPlayers() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred"),
                new Player("Amy")));
        hearts = new Hearts(players, new Scoreboard());

        hearts.deal();

        assertThat(players.get(0).getHand().size(), is(13));
        assertThat(players.get(1).getHand().size(), is(13));
        assertThat(players.get(2).getHand().size(), is(13));
        assertThat(players.get(3).getHand().size(), is(13));
        assertThat(hearts.getRounds(), is(13));
    }

    @Test
    public void dealsSeventeenCardsWhenThreePlayers() {
        players.addAll(Arrays.asList(new Player("Ted"), new Player("Charlotte"), new Player("Fred")));
        hearts = new Hearts(players, new Scoreboard());

        hearts.deal();

        assertThat(players.get(0).getHand().size(), is(17));
        assertThat(players.get(1).getHand().size(), is(17));
        assertThat(players.get(2).getHand().size(), is(17));
        assertThat(hearts.getRounds(), is(17));
    }

    @Test
    public void setsPlayerWithTwoOfClubsAsActivePlayerWhenThereAreThreePlayers() {

    }

    @Test
    public void setsPlayerWithThreeOfClubsAsActivePlayerWhenThereAreFivePlayers(){

    }
}