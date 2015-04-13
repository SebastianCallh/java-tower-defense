package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.basic.Direction;
import se.liu.ida.tddd78.towerdefense.objects.commands.BuyCommand;
import se.liu.ida.tddd78.towerdefense.objects.commands.MoveCommand;
import se.liu.ida.tddd78.towerdefense.objects.commands.SelectCommand;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Keeps track on all pressed down keys.
 */
public class InputHandler {
    private JComponent component;
    private Map<Input, Boolean> keysPressedMap;
    private static final Map<Input, Command> COMMAND_MAP = new EnumMap<>(Input.class);

    static {
        COMMAND_MAP.put(Input.UP, new MoveCommand(Direction.NORTH));
        COMMAND_MAP.put(Input.DOWN, new MoveCommand(Direction.SOUTH));
        COMMAND_MAP.put(Input.LEFT, new MoveCommand(Direction.WEST));
        COMMAND_MAP.put(Input.RIGHT, new MoveCommand(Direction.EAST));
        COMMAND_MAP.put(Input.ONE, new SelectCommand(DefenseType.SMALL));
        COMMAND_MAP.put(Input.TWO, new SelectCommand(DefenseType.BIG));
        COMMAND_MAP.put(Input.THREE, new SelectCommand(DefenseType.FAST));
        COMMAND_MAP.put(Input.SPACE, new BuyCommand());
    }

    public enum Input {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        SPACE,
        ONE,
        TWO,
        THREE
    }

    public InputHandler(JComponent component) {
        this.component = component;
        this.keysPressedMap = new EnumMap<>(Input.class);

        mapKey(KeyEvent.VK_UP, Input.UP);
        mapKey(KeyEvent.VK_DOWN, Input.DOWN);
        mapKey(KeyEvent.VK_LEFT, Input.LEFT);
        mapKey(KeyEvent.VK_RIGHT, Input.RIGHT);
        mapKey(KeyEvent.VK_SPACE, Input.SPACE);
        mapKey(KeyEvent.VK_1, Input.ONE);
        mapKey(KeyEvent.VK_2, Input.TWO);
        mapKey(KeyEvent.VK_3, Input.THREE);
    }

    private void mapKey(int key, final Input input) {
        String pressKey = String.format("PRESSED%d", key);
        String releaseKey = String.format("RELEASED%d", key);

        // Keep a mapping of the key even before the key is pressed so an exception can be thrown on an unmapped action,
        // instead of silently returning false.
        this.keysPressedMap.put(input, false);

        this.component.getInputMap().put(KeyStroke.getKeyStroke(key, 0, false), pressKey);
        this.component.getInputMap().put(KeyStroke.getKeyStroke(key, 0, true), releaseKey);

        this.component.getActionMap().put(pressKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setKeyPressed(input, true);
            }
        });
        this.component.getActionMap().put(releaseKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setKeyPressed(input, false);
            }
        });
    }

    private void setKeyPressed(Input input, boolean pressed) {
        this.keysPressedMap.put(input, pressed);
    }

    public Queue<Command> getCommands() {
        return this.keysPressedMap.keySet().stream()
                .filter(this.keysPressedMap::get)
                .map(COMMAND_MAP::get)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }
}