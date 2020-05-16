package com.designwork.cardgame.commons.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AbstractView implements View {
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addViewListener(String propertyName, PropertyChangeListener viewListener) {
        support.addPropertyChangeListener(propertyName, viewListener);
    }

    @Override
    public void removeViewListener(String propertyName, PropertyChangeListener viewListener) {
        support.removePropertyChangeListener(propertyName, viewListener);
    }

    public void setValue(String propertyName, String oldValue, String newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }


}
