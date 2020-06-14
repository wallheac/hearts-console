package com.designwork.cardgame.hearts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class Dialog extends JDialog {

    public Dialog(JFrame owner) {
        super(owner);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        JPanel mainPanel = new JPanel(layout);
        mainPanel.setName("main content");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(mainPanel, constraints);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(300, 150));
        submitButton.setFont(new Font("SansSerif", Font.PLAIN, 36));

        JPanel buttonPanel = new JPanel(layout);
        buttonPanel.setName("submit button");
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;

        buttonPanel.add(submitButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(buttonPanel, constraints);
    }

    private void closeDialog(ActionEvent e) {
        this.dispose();
    }



    public JPanel getPanelByName(String name) {
        List<Component> widgets = Arrays.asList(getContentPane().getComponents());
        return (JPanel)widgets.stream()
                .filter(widget -> widget.getName().equals(name))
                .findFirst()
                .get();
    }

    public JButton getSubmitButton() {
        JPanel submitPanel = getPanelByName("submit button");
        return (JButton) submitPanel.getComponent(0);
    }

}
