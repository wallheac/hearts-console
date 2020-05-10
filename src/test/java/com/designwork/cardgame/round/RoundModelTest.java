package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.trick.TrickModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoundModelTest {

    @Mock
    private TrickModel trickModel;

    private RoundModel roundModel;

    @Before
    public void setup() {
    }

    @Test
    public void advancePlayerSetsCurrentPlayerToNextPlayerInPlayerModelList() {
        PlayerModel tedModel = new PlayerModel("Ted", Collections.emptyList(), Card.TwoClubs, Card.ThreeClubs);
        PlayerModel amyModel = new PlayerModel("Amy", Collections.emptyList(), Card.FourClubs, Card.FiveClubs);

        roundModel = new RoundModel(
                Arrays.asList(tedModel, amyModel),
                trickModel,
                Card.TwoClubs,
                null,
                1);

        assertThat(roundModel.getCurrentPlayer(), is(tedModel));

        roundModel.advancePlayer();

        assertThat(roundModel.getCurrentPlayer(), is(amyModel));
    }

    @Test
    public void roundModelFindsStartingPlayerCorrectly() {
        PlayerModel amyModel = new PlayerModel("Amy", Collections.emptyList(), Card.FourClubs, Card.FiveClubs);
        PlayerModel tedModel = new PlayerModel("Ted", Collections.emptyList(), Card.TwoClubs, Card.ThreeClubs);

        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                trickModel,
                null,
                null,
                1);

        assertThat(roundModel.getCurrentPlayer(), is(tedModel));
    }
}