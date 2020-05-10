package com.designwork.cardgame.commons.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AbstractView implements View {
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addViewListener(PropertyChangeListener viewListener) {
        support.addPropertyChangeListener(viewListener);
    }

    @Override
    public void removeViewListener(PropertyChangeListener viewListener) {
        support.removePropertyChangeListener(viewListener);
    }

    public void setValue(String propertyName, String oldValue, String newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }


}
