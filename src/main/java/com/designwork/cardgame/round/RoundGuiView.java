package com.designwork.cardgame.round;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractSwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoundGuiView extends AbstractSwingView implements IRoundView {

    private final JPanel mainPanel;
    private String currentPlayerName;
    private List<Card> hand;
    private Trick currentTrick;
    private final JFrame gameFrame;
    private CardListView handView;
    private CardListView trickView;
    private JLabel invalidChoice;
    private JLabel playerName;


    public RoundGuiView(JFrame frame) {
        super();
        this.gameFrame = frame;
        mainPanel = new JPanel();
        mainPanel.setName("round main content");
        mainPanel.setLayout(new GridBagLayout());
        gameFrame.add(mainPanel);
    }

    @Override
    public void initialize() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        playerName = (JLabel) getJLabelByName("current player");
        playerName.setName("current player");
        playerName.setFont(new Font("SansSerif", Font.PLAIN, 36));
        mainPanel.add(playerName, c);

        invalidChoice = new JLabel("You chose an invalid card. Please try again");
        invalidChoice.setFont(new Font("SansSerif", Font.PLAIN, 36));
        invalidChoice.setForeground(Color.RED);
        invalidChoice.setName("invalid choice");
        invalidChoice.setVisible(false);
        c.gridy = 1;
        mainPanel.add(invalidChoice, c);

        handView = new CardListView();
        handView.setBackground(new Color(0, 82, 33));
        handView.setCards(hand);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 3;
        JLabel currentTrickLabel = new JLabel("Current Trick:");
        currentTrickLabel.setName("current trick");
        currentTrickLabel.setFont(new Font("SansSerif", Font.PLAIN, 36));
        mainPanel.add(currentTrickLabel, c);

        trickView = new CardListView();
        c.gridy = 4;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(trickView, c);

        gameFrame.setVisible(true);
    }

    public void requestPlay() {
        displayHandForPlayer();
    }

    private void cardClicked(ActionEvent event) {
        invalidChoice.setVisible(false);
        Integer index = hand.indexOf(Card.getByIconId(event.getActionCommand()));
        setValue("cardPlayed", null, index.toString());
    }

    public void gameOver(String name) {
        System.out.println("\nGame Over\n" + name + " wins!");
    }

    public void displayHandForPlayer() {
        playerName.setText("Current Player: " + currentPlayerName);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        handView.setCards(hand);
        handView.displayCards();
        handView.addCardClickedListener(this::cardClicked);
        mainPanel.add(handView, c);
        gameFrame.validate();
    }

    public void displayCurrentTrick() {
        trickView.setCards(currentTrick.getCards().stream().map(card -> card.getSecond()).collect(Collectors.toList()));
        trickView.displayCards();
        gameFrame.validate();
    }

    public void respondInvalidChoice() {
        invalidChoice.setVisible(true);
        gameFrame.validate();
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setCurrentTrick(Trick trick) {
        this.currentTrick = trick;
    }

    public void announceTrickWinner(String name) {
        System.out.println("\n" + name + " wins this trick\n\n");
    }

    public Component getJLabelByName(String name) {
        List<Component> labels = Arrays.asList(mainPanel.getComponents())
                .stream()
                .filter(widget -> widget instanceof JLabel)
                .collect(Collectors.toList());
        if(labels.isEmpty()) {
            return new JLabel();
        }
        return labels.stream()
                .filter(widget -> widget.getName().equals(name))
                .findFirst()
                .orElse(new JLabel());
    }
}
