package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.ButtonType;

public interface ButtonObserver {
    void onButtonClicked(ButtonType buttonType);
}
