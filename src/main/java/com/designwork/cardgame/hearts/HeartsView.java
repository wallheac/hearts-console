package com.designwork.cardgame.hearts;

import com.designwork.cardgame.commons.ui.AbstractView;
import com.designwork.cardgame.commons.util.ConsoleInputUtil;

public class HeartsView extends AbstractView {
    private final ConsoleInputUtil consoleInputUtil;

    HeartsView() {
        consoleInputUtil = new ConsoleInputUtil();
    }

    public void requestPlayerName() {
        System.out.println("Please enter player name: ");
        String name = consoleInputUtil.requestTextInput();
        setValue("playerAdded", null, name);
    }

    public void requestAdditionalPlayers() {
        System.out.println("Would you like to add a player? y/n");
        String another = consoleInputUtil.requestTextInput();
        setValue("another", null, another);
    }
}
