package com.designwork.cardgame.hearts;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.round.CardListView;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PlayerPanel extends JPanel {
    private CardListView handView;
    private CardListView trickView;
    private JPanel mainPanel;
    private List<Card> hand = Arrays.asList(Card.TwoClubs, Card.AceHearts);
    private List<Card> trick = Arrays.asList(Card.FourHearts, Card.QueenSpades);

    public PlayerPanel() {
        mainPanel = new JPanel();
        mainPanel.setName("player panel");

        mainPanel.setPreferredSize(new Dimension(1000, 1000));

        JButton testButton = new JButton("testPlayerPanel");
        testButton.setPreferredSize(new Dimension(300, 300));
        mainPanel.add(testButton);
//        handView = new CardListView(hand);
//        handView.setPreferredSize(new Dimension(400, 400));
//        add(handView);
//
//        trickView = new CardListView(trick);
//        trickView.setPreferredSize(new Dimension(400, 400));
//        add(trickView);
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
        handView.setHand(hand);
    }

    public void setTrick(List<Card> trick) {
        this.trick = trick;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
