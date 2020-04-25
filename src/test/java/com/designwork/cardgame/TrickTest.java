package com.designwork.cardgame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrickTest {

    private Hearts hearts;
    private List<Player> players = new ArrayList<>();

    @Before
    public void setup() {

        players.addAll(Arrays.asList(new Player("Ted", Card.TwoClubs), new Player("Fred", Card.ThreeClubs),
                new Player("Amy", Card.FourClubs), new Player("Charlotte", Card.FiveClubs)));
        hearts = new Hearts(players, new Scoreboard());
    }

//    @Test
//    public void testOneTrick() {
//        // 2C led
//        // Result: Charlotte wins the trick (eventually scores no points)
//    }

}