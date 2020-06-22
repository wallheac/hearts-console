package com.designwork.cardgame.hearts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Dialog extends JDialog {
    JButton submitButton;
    JPanel mainPanel;

    public Dialog(JFrame owner) {
        super(owner);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        mainPanel = new JPanel(layout);
        mainPanel.setName("main content");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(mainPanel, constraints);

        submitButton = new JButton("Submit");
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

    public void close() {
        dispose();
    }

    public JPanel getMainContentPanel() {
        return mainPanel;
    }

    public JPanel getPanelByName(String name) {
        List<Component> widgets = Arrays.asList(getContentPane().getComponents());
        return (JPanel) widgets.stream()
                .filter(widget -> widget.getName().equals(name))
                .findFirst()
                .get();
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
