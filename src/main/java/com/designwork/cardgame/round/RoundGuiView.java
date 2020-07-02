package com.designwork.cardgame.round;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractSwingView;

import javax.swing.*;
import java.awt.*;
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
        gameFrame.setVisible(true);
    }

    public void requestPlay() {
//        System.out.println("Current Player: " + currentPlayerName);
//        System.out.println("Please choose a card to play: ");
//        displayHand();
//        Integer cardNumber = ConsoleInputUtil.requestNumericInput();
//        setValue("cardPlayed", null, cardNumber.toString());
        handView = new CardListView(hand);
        handView.setPreferredSize(new Dimension(400, 400));
        gameFrame.add(handView);
    }

    public void gameOver(String name) {
        System.out.println("\nGame Over\n" + name + " wins!");
    }

    public void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ". " + hand.get(i).prettyPrint());
        }
    }

    public void displayCurrentTrick() {
        System.out.println("Current trick: ");
        currentTrick.getCards().stream().forEach(card -> System.out.println(card.getSecond().prettyPrint()));
    }

    public void respondInvalidChoice() {
        System.out.println("You chose an invalid card. Please try again");
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
