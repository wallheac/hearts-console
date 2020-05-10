package com.designwork.cardgame.commons.ui;

import java.beans.PropertyChangeListener;

public interface View {
    void addViewListener(PropertyChangeListener viewListener);
    void removeViewListener(PropertyChangeListener viewListener);
}
