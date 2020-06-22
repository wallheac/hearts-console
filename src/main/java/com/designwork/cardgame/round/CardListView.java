package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardListView extends JPanel{
    private final List<Card> hand;

    public CardListView(List<Card> hand)  {
        this.hand = hand;

        for(Card card : hand) {
            add(new JLabel(card.prettyPrint()));
        }

        JButton testThree = new JButton("testThree");
        testThree.setPreferredSize(new Dimension(300, 300));
        add(testThree);
    }

    public void setHand(List<Card> hand) {
        for(Card card : hand) {
            add(new JLabel(card.prettyPrint()));
        }
    }
}
