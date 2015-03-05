package se.liu.ida.tddd78.towerdefense;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-02-25.
 */
public class Input {
    private JComponent component;
    private Map<Action, Boolean> keysPressedMap;

    public enum Action {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Input(JComponent frame) {
        this.component = frame;
        this.keysPressedMap = new HashMap<Action, Boolean>();

        mapKey(KeyEvent.VK_UP, Action.UP);
        mapKey(KeyEvent.VK_DOWN, Action.DOWN);
        mapKey(KeyEvent.VK_LEFT, Action.LEFT);
        mapKey(KeyEvent.VK_RIGHT, Action.RIGHT);
    }

    private void mapKey(int key, final Action action) {
        String pressKey = String.format("PRESSED%d", key);
        String releaseKey = String.format("RELEASED%d", key);

        // Keep a mapping of the key even before the key is pressed so an exception can be thrown on an unmapped action,
        // instead of silently returning false.
        this.keysPressedMap.put(action, false);

        this.component.getInputMap().put(KeyStroke.getKeyStroke(key, 0, false), pressKey);
        this.component.getInputMap().put(KeyStroke.getKeyStroke(key, 0, true), releaseKey);

        this.component.getActionMap().put(pressKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setKeyPressed(action, true);
            }
        });
        this.component.getActionMap().put(releaseKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setKeyPressed(action, false);
            }
        });
    }

    private void setKeyPressed(Action action, boolean pressed) {
        this.keysPressedMap.put(action, pressed);
    }

    public boolean isKeyPressed(Action action) {
        if (!this.keysPressedMap.containsKey(action)) {
            throw new RuntimeException(String.format("Action %s has not been mapped", action));
        }

        return this.keysPressedMap.get(action);
    }
}