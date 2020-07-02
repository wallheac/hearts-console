package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardListView extends JPanel implements ChangeListener {
    private List<Card> hand;

    public CardListView() {
        this.hand = new ArrayList<>();

    }

    public CardListView(List<Card> hand) {
        this.hand = hand;
        for (Card card : hand) {
            JLabel label = new JLabel();
            URL imageUrl = getClass().getResource(card.getIconId());
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(imageUrl));
            }
            add(label);
        }

    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    // All this will do is notify the component that state has changed
    @Override
    public void stateChanged(ChangeEvent e) {
        JButton testState = new JButton("test card list state change");
        testState.setPreferredSize(new Dimension(300, 300));
        add(testState);

        // for(Card card : hand) {
        // add(new JLabel(card.prettyPrint()));
        // }

        revalidate();
        repaint();
    }
}
