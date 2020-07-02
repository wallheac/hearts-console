package com.designwork.cardgame.hearts;


import com.designwork.cardgame.commons.ui.AbstractSwingView;
import com.designwork.cardgame.commons.ui.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class HeartsGuiDialog extends AbstractSwingView implements View {
    private final List<PlayerEntryPanel> playerEntryPanels;
    private final Dialog dialog;
    private final JFrame frame;

    public HeartsGuiDialog(JFrame frame) {
        this.frame = frame;
        this.playerEntryPanels = Arrays.asList(new PlayerEntryPanel(), new PlayerEntryPanel(),
                new PlayerEntryPanel(), new PlayerEntryPanel(), new PlayerEntryPanel());

        dialog = new Dialog(frame);
        dialog.setSize(1000, 1000);
        dialog.setTitle("Player Entry");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);
        constraints.gridx = 0;

        for (int i = 0; i < playerEntryPanels.size(); i++) {
            constraints.gridy = i;
            panel.add(playerEntryPanels.get(i), constraints);
        }

        dialog.getMainContentPanel().add(panel);
        dialog.addSubmitButtonListener(this::submitClicked);

        dialog.setVisible(true);
    }

    private void submitClicked(ActionEvent e) {
        dialog.close();
        playerEntryPanels.forEach(playerEntryPanel -> {
            if (!playerEntryPanel.getText().isEmpty()) {
                setValue("playerAdded", null, playerEntryPanel.getText());
            }
        });
        setValue("submit", null, null);

    }

    @Override
    public void initialize() {

    }
}

