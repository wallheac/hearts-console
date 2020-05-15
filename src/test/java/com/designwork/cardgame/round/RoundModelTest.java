package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.player.PlayerModel;
import com.designwork.cardgame.trick.TrickModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoundModelTest {

    @Mock
    private TrickModel trickModel;

    private RoundModel roundModel;

    @Before
    public void setup() {

    }

    @Test
    public void advancePlayerSetsCurrentPlayerToNextPlayerInPlayerModelList() {
        PlayerModel tedModel = new PlayerModel("Ted", UUID.randomUUID(), Collections.emptyList(),
                Card.TwoClubs, Card.ThreeClubs);
        PlayerModel amyModel = new PlayerModel("Amy", UUID.randomUUID(), Collections.emptyList(),
                Card.FourClubs, Card.FiveClubs);

        roundModel = new RoundModel(
                Arrays.asList(tedModel, amyModel),
                trickModel,
                Card.TwoClubs,
                1);

        assertThat(roundModel.getCurrentPlayer(), is(tedModel));

        roundModel.advancePlayer();

        assertThat(roundModel.getCurrentPlayer(), is(amyModel));
    }

    @Test
    public void roundModelFindsStartingPlayerCorrectly() {
        PlayerModel amyModel = new PlayerModel("Amy", UUID.randomUUID(), Collections.emptyList(), Card.FourClubs, Card.FiveClubs);
        PlayerModel tedModel = new PlayerModel("Ted", UUID.randomUUID(), Collections.emptyList(), Card.TwoClubs, Card.ThreeClubs);

        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                trickModel,
                null,
                1);

        assertThat(roundModel.getCurrentPlayer(), is(tedModel));
    }

    @Test
    public void addPlayedCardToTrickUpdatedCurrentPlayerHand() {
        UUID uuid = UUID.randomUUID();
        PlayerModel amyModel = new PlayerModel("Amy", uuid, new ArrayList<>(), Card.FourClubs, Card.FiveClubs);
        PlayerModel tedModel = new PlayerModel("Ted", uuid, new ArrayList<>(), Card.TwoClubs, Card.ThreeClubs);

        roundModel = new RoundModel(
                Arrays.asList(amyModel, tedModel),
                trickModel,
                null,
                1);

        roundModel.addPlayedCardToTrick(Card.TwoClubs);

        assertThat(tedModel.getHand().size(), is(1));
        assertThat(tedModel.getHand(), is(Collections.singletonList(Card.ThreeClubs)));
        verify(trickModel).addCardToCurrentTrick(uuid, Card.TwoClubs);
    }

    @Test
    public void addPlayedCardToTrickDelegatesToTrickModel() {}
}
