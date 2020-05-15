package com.designwork.cardgame.trick;

import com.designwork.cardgame.card.Card;
import org.junit.Test;

import java.util.UUID;

public class TrickModelTest {
    @Test
    public void addCardToCurrentTrickAddsCardAndPlayerUuid() {
        TrickModel trickModel = new TrickModel();
        UUID playerUuid = UUID.randomUUID();

        trickModel.addCardToCurrentTrick(playerUuid, Card.ThreeClubs);

        assert (trickModel.getCurrentTrick().get(0).getFirst()).equals(playerUuid);
        assert (trickModel.getCurrentTrick().get(0).getSecond()).equals(Card.ThreeClubs);
    }
}