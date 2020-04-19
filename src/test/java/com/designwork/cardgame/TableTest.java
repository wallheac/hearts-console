package com.designwork.cardgame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TableTest {

    private Table table;
    List<Player> players = new ArrayList<>();


    @Before
    public void setup () {

        players.addAll(Arrays.asList(new Player(), new Player(), new Player(), new Player()));
        table = new Table(players);
    }

    @Test
    public void resetTableResetsRoundsToZeroAndPlayersToEmptyList() {
        table.resetTable();

        assert(table.getRound().equals(0));
        assertThat(table.getPlayers().size(), is(0));
    }

    @Test
    public void playCompletesThirteenRounds() {
        table.play();

        assert(table.getRound().equals(13));
    }
}