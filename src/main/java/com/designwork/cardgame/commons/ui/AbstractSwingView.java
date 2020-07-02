package com.designwork.cardgame.commons.ui;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class AbstractSwingView {
    protected final SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(this, true);

    public void addViewListener(String propertyName, PropertyChangeListener viewListener) {
        support.addPropertyChangeListener(propertyName, viewListener);
    }

    public void removeViewListener(String propertyName, PropertyChangeListener viewListener) {
        support.removePropertyChangeListener(propertyName, viewListener);
    }

    public void setValue(String propertyName, String oldValue, String newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    public <T> void setValue(String propertyName, T oldValue, T newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
