package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.ButtonKind;

/**
 * Recieves notifications upon button presses.
 */
public interface ButtonObserver {
    void onButtonClicked(ButtonKind buttonKind);
}
