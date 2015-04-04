package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.ButtonKind;

public interface ButtonObserver {
    void onButtonClicked(ButtonKind buttonKind);
}
