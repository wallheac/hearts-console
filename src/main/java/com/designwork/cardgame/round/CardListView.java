package com.designwork.cardgame.round;

import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardListView extends JPanel {
    private List<Card> cards;
    private List<JButton> cardButtons = new ArrayList<>();

    public CardListView() {
        this.cards = new ArrayList<>();
        setLayout(new WrapLayout(WrapLayout.LEFT));
    }

    void displayCards() {
        resetCards();
        for (Card card : cards) {
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
        repaint();
    }

    public void addCardClickedListener(ActionListener listener) {
        this.cardButtons.forEach(button -> button.addActionListener(listener));
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
