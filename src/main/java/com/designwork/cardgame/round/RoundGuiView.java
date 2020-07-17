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
        handView = new CardListView();
        handView.setBackground(new Color(0, 82, 33));
        handView.setCards(hand);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        JLabel currentTrickLabel = new JLabel("Current Trick:");
        currentTrickLabel.setName("current trick");
        currentTrickLabel.setFont(new Font("SansSerif", Font.PLAIN, 36));

        mainPanel.add(currentTrickLabel, c);
        trickView = new CardListView();
        gameFrame.setVisible(true);
    }

    public void requestPlay() {
        displayHandForPlayer();
    }

    private void cardClicked(ActionEvent event) {
        Integer index = hand.indexOf(Card.getByIconId(event.getActionCommand()));
        setValue("cardPlayed", null, index.toString());
    }

    public void gameOver(String name) {
        System.out.println("\nGame Over\n" + name + " wins!");
    }

    public void displayHandForPlayer() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        JLabel playerName = (JLabel) getJLabelByName("current player");
        playerName.setName("current player");
        playerName.setText("Current Player: " + currentPlayerName);
        playerName.setFont(new Font("SansSerif", Font.PLAIN, 36));
        mainPanel.add(playerName, c);
        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        handView.setCards(hand);
        handView.displayCards();
        handView.addCardClickedListener(this::cardClicked);
        mainPanel.add(handView, c);
        gameFrame.validate();
    }

    public void displayCurrentTrick() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 3;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        trickView.setCards(currentTrick.getCards().stream().map(card -> card.getSecond()).collect(Collectors.toList()));
        trickView.displayCards();
        mainPanel.add(trickView, c);
        gameFrame.validate();

        System.out.println("Current trick: ");
        currentTrick.getCards().forEach(card -> System.out.println(card.getSecond().prettyPrint()));
    }

    public void respondInvalidChoice() {
        JLabel invalidChoice = new JLabel("You chose an invalid card. Please try again");
        gameFrame.add(invalidChoice);
        gameFrame.revalidate();
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
