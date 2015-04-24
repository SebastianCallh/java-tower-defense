package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.Game.State;
import se.liu.ida.tddd78.towerdefense.interfaces.ButtonObserver;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.ButtonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends JPanel implements GameObserver, ActionListener {
    private static final Color BACKGROUND_COLOR = Color.darkGray;
    private static final Color FOREGROUND_COLOR = Color.white;
    private static final int TEXT_SIZE = 40;

    private int scale;
    private List<ButtonObserver> buttonClickListeners;

    private JButton newGameButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MenuScreen(int scale) {
        assert scale > 0;

        this.scale = scale;
        this.buttonClickListeners = new ArrayList<>();

        initLayout();
    }

    private void initLayout() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new GridBagLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setOpaque(false);

        JLabel gameOverLabel = new ScaledJLabel(scale, TEXT_SIZE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(FOREGROUND_COLOR);
        gameOverLabel.setText("Java Tower Defense");

        newGameButton = new JButton("Start game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(this);

        optionsButton = new JButton("Options");
        optionsButton.setAlignmentX(CENTER_ALIGNMENT);
        optionsButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(this);

        innerPanel.add(gameOverLabel);
        innerPanel.add(generatePadding()); // TODO: Replace with proper padding
        innerPanel.add(newGameButton);
        innerPanel.add(optionsButton);
        innerPanel.add(generatePadding());
        innerPanel.add(exitButton);

        add(innerPanel, new GridBagConstraints());

    }

    private JLabel generatePadding() {
        ScaledJLabel label = new ScaledJLabel(scale);
        label.setText(" ");

        return label;
    }

    @Override
    public void onNotify(Game game) {
        setVisible(game.getState() == State.MAIN_MENU);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(newGameButton)) {
            notifyButtonClickListeners(ButtonType.NEW_GAME);
        } else if (source.equals(optionsButton)) {
            notifyButtonClickListeners(ButtonType.OPTIONS);
        } else if (source.equals(exitButton)) {
            notifyButtonClickListeners(ButtonType.EXIT);
        }
    }


    public void addButtonClickListener(ButtonObserver listener) {
        buttonClickListeners.add(listener);
    }

    private void notifyButtonClickListeners(ButtonType buttonType) {
        for (ButtonObserver listener : buttonClickListeners) {
            listener.onButtonClicked(buttonType);
        }
    }
}
