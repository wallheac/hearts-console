package com.designwork.cardgame.hearts;

import javax.swing.*;
import java.awt.*;

public class PlayerEntryPanel extends JPanel {
    JTextField playerName;

    public PlayerEntryPanel() {
        super(new GridBagLayout());

        JLabel label = new JLabel("Please Enter Player Name:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 30));

        playerName = new JTextField();
        playerName.setPreferredSize(new Dimension(700, 150));
        playerName.setFont(new Font("SansSerif", Font.PLAIN, 36));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);
        constraints.gridy = 1;
        add(playerName, constraints);
    }

    public String getText() {
        return playerName.getText();
    }
}
