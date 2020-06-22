package com.designwork.cardgame.round;

import com.designwork.cardgame.Trick;
import com.designwork.cardgame.card.Card;
import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;
import com.designwork.cardgame.hearts.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class RoundGuiView extends AbstractView implements IRoundView {

    private String currentPlayerName;
    private List<Card> hand;
    private Trick currentTrick;
    private JFrame gameFrame;
    private PlayerPanel playerPanel;

    public RoundGuiView(JFrame frame) {
        super();
        this.gameFrame = frame;
        playerPanel = new PlayerPanel();



        gameFrame.setContentPane(playerPanel);
        gameFrame.setVisible(true);
    }

    @Override
    public void initialize() {
        JPanel content = playerPanel.getMainPanel();


        CardListView handView = new CardListView(hand);
        handView.setPreferredSize(new Dimension(400, 400));
        content.add(handView);

        CardListView trickView = new CardListView(
                currentTrick.getCards().stream()
                .map(uuidCardPair -> uuidCardPair.getSecond())
                .collect(Collectors.toList()));
        trickView.setPreferredSize(new Dimension(400, 400));
        content.add(trickView);
//        gameFrame.revalidate();
//        gameFrame.repaint();


    }

    public void requestPlay() {
        System.out.println("Current Player: " + currentPlayerName);
        System.out.println("Please choose a card to play: ");
        displayHand();
        Integer cardNumber = ConsoleInputUtil.requestNumericInput();
        setValue("cardPlayed", null, cardNumber.toString());
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
//        playerPanel.setHand(hand);
    }

    public void setCurrentTrick(Trick trick) {
        this.currentTrick = trick;
    }

    public void announceTrickWinner(String name) {
        System.out.println("\n" + name + " wins this trick\n\n");
    }

}
