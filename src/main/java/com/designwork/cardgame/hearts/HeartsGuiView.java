package com.designwork.cardgame.hearts;


import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.ui.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class HeartsGuiView extends AbstractView implements View {
    private List<PlayerEntryPanel> playerEntryPanels;

    public HeartsGuiView() {
        this.playerEntryPanels = Arrays.asList(new PlayerEntryPanel(), new PlayerEntryPanel(),
                new PlayerEntryPanel(), new PlayerEntryPanel(), new PlayerEntryPanel());
    }

    @Override
    public void initialize() {
        createDialog();
    }

    private void createDialog() {
        JFrame frame = new JFrame("Test");
        frame.setSize(1400, 1400);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 150));
        submitButton.setFont(new Font("SansSerif", Font.PLAIN, 36));
        submitButton.addActionListener(e -> okClicked(e));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);
        constraints.gridx = 0;

        for (int i = 0; i < playerEntryPanels.size(); i++) {
            constraints.gridy = i;
            panel.add(playerEntryPanels.get(i), constraints);
        }

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = 6;
        panel.add(submitButton, constraints);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void okClicked(ActionEvent e) {
        playerEntryPanels.forEach(playerEntryPanel -> {
            if (!playerEntryPanel.getText().isEmpty()) {
                setValue("playerAdded", null, playerEntryPanel.getText());
            }
        });
        setValue("submit", null, null);
    }
}
