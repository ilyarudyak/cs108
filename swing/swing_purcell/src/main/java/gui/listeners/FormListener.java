package gui.listeners;

import gui.events.FormEvent;

import java.util.EventListener;

/**
 * Created by ilyarudyak on 6/14/16.
 */
public interface FormListener extends EventListener {
    public void formEventOccurred(FormEvent e);
}
