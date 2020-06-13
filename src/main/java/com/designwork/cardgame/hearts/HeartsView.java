package com.designwork.cardgame.hearts;

import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;

public class HeartsView extends AbstractView implements IHeartsView {
    private final ConsoleInputUtil consoleInputUtil;

    public HeartsView() {
        consoleInputUtil = new ConsoleInputUtil();
    }

    @Override
    public void initialize() {
        registerPlayers();
    }

    public void requestPlayerName() {
        System.out.println("Please enter player name: ");
        String name = consoleInputUtil.requestTextInput();
        setValue("playerAdded", null, name);
    }

    public String requestAdditionalPlayers() {
        System.out.println("Would you like to add a player? y/n");
        return consoleInputUtil.requestTextInput();
    }

    protected void registerPlayers() {
        String additionalPlayer = "n";
        Integer playerCount = 0;
        do {
            requestPlayerName();
            playerCount++;
            if (playerCount < 5) {
                additionalPlayer = requestAdditionalPlayers();
            }
        } while ("y".equals(additionalPlayer) && playerCount < 5);
        setValue("submit", null, null);
    }
}
