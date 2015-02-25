package se.liu.ida.tddd78.towerdefense;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Seba on 2015-02-25.
 */
public class Input {
    private JComponent component;
    private Queue<Action> actions;

    private enum Action {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Input(JComponent frame) {
        this.component = frame;
        this.actions = new PriorityQueue<Action>();
        this.component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Action.UP);
        this.component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Action.DOWN);
        this.component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Action.LEFT);
        this.component.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Action.RIGHT);

        final Input that = this;
        this.component.getActionMap().put(Action.UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.addAction(Action.UP);
            }
        });
        this.component.getActionMap().put(Action.DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.addAction(Action.DOWN);
            }
        });
        this.component.getActionMap().put(Action.LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.addAction(Action.LEFT);
            }
        });
        this.component.getActionMap().put(Action.RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.addAction(Action.RIGHT);
            }
        });
    }

    public boolean actionsPerformed() {
        return !this.actions.isEmpty();
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public Action getAction() {
        return this.actions.remove();
    }
}
