package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardListView extends JPanel {
    private List<Card> hand;
    private List<JButton> cardButtons = new ArrayList<>();

    public CardListView() {
        this.hand = new ArrayList<>();

    }

    public CardListView(List<Card> hand) {
        this.hand = hand;
        setLayout(new GridBagLayout());
        displayHand();
    }

    void displayHand() {
        resetCards();
        for (Card card : hand) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(150, 250));
            button.setActionCommand(card.getIconId());
            URL imageUrl = getClass().getResource(card.getIconId());
            if (imageUrl != null) {
                button.setIcon(
                        new ImageIcon(
                                new ImageIcon(imageUrl)
                                        .getImage()
                                        .getScaledInstance(150, 250, Image.SCALE_SMOOTH)));
            }
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            add(button);
            this.cardButtons.add(button);
        }
        revalidate();
    }

    private void resetCards() {
        this.cardButtons = new ArrayList<>();
        removeAll();
        revalidate();
    }

    public void addCardClickedListener(ActionListener listener) {
        this.cardButtons.stream().forEach(button -> button.addActionListener(listener));
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

}
