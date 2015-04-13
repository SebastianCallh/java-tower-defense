package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.ButtonType;

/**
 * Recieves notifications upon button presses.
 */
public interface ButtonObserver {
    void onButtonClicked(ButtonType buttonType);
}
