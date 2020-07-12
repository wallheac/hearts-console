package com.designwork.cardgame.round;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractSwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RoundGuiView extends AbstractSwingView implements IRoundView {

    private String currentPlayerName;
    private List<Card> hand;
    private Trick currentTrick;
    private final JFrame gameFrame;
    private CardListView handView;


    public RoundGuiView(JFrame frame) {
        super();
        this.gameFrame = frame;
    }

    @Override
    public void initialize() {
        handView = new CardListView(hand);
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
        JLabel playerName = new JLabel(currentPlayerName);
        playerName.setFont(new Font("SansSerif", Font.PLAIN, 36));
        gameFrame.add(playerName);
        handView.setHand(hand);
        handView.displayHand();
        handView.setPreferredSize(new Dimension(400, 400));
        handView.addCardClickedListener(this::cardClicked);
        gameFrame.add(handView);
        gameFrame.validate();
    }

    public void displayCurrentTrick() {
        System.out.println("Current trick: ");
        currentTrick.getCards().stream().forEach(card -> System.out.println(card.getSecond().prettyPrint()));
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

}
