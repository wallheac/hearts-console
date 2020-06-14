package com.designwork.cardgame.hearts;


import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.ui.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Dialog createDialog() {
         return createDialog(new JFrame());
    }

    public Dialog createDialog(JFrame frame) {
        Dialog dialog =  new Dialog(frame);
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

        JPanel dialogContent = dialog.getPanelByName("main content");
        dialogContent.add(panel);

        JButton submitButton = dialog.getSubmitButton();
        submitButton.addActionListener(e -> submitClicked(e));

        dialog.setVisible(true);
        return dialog;
    }

    private void submitClicked(ActionEvent e) {
        playerEntryPanels.forEach(playerEntryPanel -> {
            if (!playerEntryPanel.getText().isEmpty()) {
                setValue("playerAdded", null, playerEntryPanel.getText());
            }
        });
        setValue("submit", null, null);
    }
}
