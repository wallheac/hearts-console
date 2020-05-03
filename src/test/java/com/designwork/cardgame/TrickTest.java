package com.designwork.cardgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrickTest {

    private Hearts hearts;
    private List<Player> players = new ArrayList<>();
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before

    public void setup() {

        players.addAll(Arrays.asList(new Player("Ted", Card.TwoClubs), new Player("Fred", Card.ThreeClubs),
                new Player("Amy", Card.FourClubs), new Player("Charlotte", Card.FiveClubs)));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void twoOfClubsLeads() {
        setup();
        hearts = new Hearts(players, new Scoreboard(players), 1);
        String input ="1";
        provideInput(input);

        hearts.play();

        // 2C led
        // Result: Charlotte wins the trick (eventually scores no points)
        assertThat(hearts.getLed().getFirst().getName(), is("Ted"));
        assertThat(hearts.getPlayers().get(3).getTricksTaken().size(), is(1));
    }

//    private final static Supplier<ByteArrayInputStream> testIn(final String data) {
//        return () -> (ByteArrayInputStream)Function.identity().apply(data);
//    };

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}