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

public class OptionsScreen extends JPanel implements GameObserver, ActionListener {
    private static final Color BACKGROUND_COLOR = Color.darkGray;
    private static final Color FOREGROUND_COLOR = Color.white;
    private static final int TEXT_SIZE = 40;

    private int scale;
    private List<ButtonObserver> buttonClickListeners;

    private JButton mapButton;
    private JButton themeButton;
    private JButton backButton;

    public OptionsScreen(int scale) {
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
        gameOverLabel.setText("Options");

        mapButton = new JButton("Map: ");
        mapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mapButton.addActionListener(this);

        themeButton = new JButton("Theme: ");
        themeButton.setAlignmentX(CENTER_ALIGNMENT);
        themeButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(this);

        innerPanel.add(gameOverLabel);
        innerPanel.add(generatePadding()); // TODO: Replace with proper padding
        innerPanel.add(mapButton);
        innerPanel.add(themeButton);
        innerPanel.add(generatePadding());
        innerPanel.add(backButton);

        add(innerPanel, new GridBagConstraints());

    }

    private JLabel generatePadding() {
        ScaledJLabel label = new ScaledJLabel(scale);
        label.setText(" ");

        return label;
    }

    @Override
    public void onNotify(Game game) {
        setVisible(game.getState() == State.OPTIONS_MENU);
        mapButton.setText("Map: " + game.getOptions().getLayout().getName());
        themeButton.setText("Theme: " + game.getOptions().getTheme().getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(mapButton)) {
            notifyButtonClickListeners(ButtonType.NEXT_MAP);
        } else if (source.equals(themeButton)) {
            notifyButtonClickListeners(ButtonType.NEXT_THEME);
        } else if (source.equals(backButton)) {
            notifyButtonClickListeners(ButtonType.MAIN_MENU);
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