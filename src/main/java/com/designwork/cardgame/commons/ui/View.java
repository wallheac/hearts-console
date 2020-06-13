package com.designwork.cardgame.commons.ui;

import java.beans.PropertyChangeListener;

public interface View {
    void initialize();

    void addViewListener(String propertyName, PropertyChangeListener viewListener);

    void removeViewListener(String propertyName, PropertyChangeListener viewListener);
}
