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
}