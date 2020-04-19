package com.designwork.cardgame;

import java.util.List;
import java.util.Scanner;

public class Player {
    private List<Card> hand;

    public void drawHand(Deck deck) {
        hand = deck.drawHand();
    }

    public Card requestPlay() {
        System.out.println("What card would you like to play?\n");
        printHand();
        Integer input = requestInput();
        Card played = hand.get(input - 1);
        hand.remove(input - 1);
        return played;
    }

    private int requestInput() {
        Integer input = null;
        try {
            Scanner in = new Scanner(System.in);
            input = in.nextInt();
        } catch (Exception e) {
            System.out.println("please enter a number");
        }
        return input;
    }

    private boolean printHand() {
        for(int i = 0; i < hand.size() ; i++) {
            System.out.println((i+1) + ": " + hand.get(i).prettyPrint());
        }
        return true;
    }
}
