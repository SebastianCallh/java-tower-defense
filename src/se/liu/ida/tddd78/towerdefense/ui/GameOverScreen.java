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


public class GameOverScreen extends JPanel implements GameObserver, ActionListener {
    private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 100);
    private static final Color FOREGROUND_COLOR = Color.white;
    private static final int TEXT_SIZE = 40;

    private int scale;
    private List<ButtonObserver> buttonClickListeners;

    public GameOverScreen(int scale) {
        this.scale = scale;
        this.buttonClickListeners = new ArrayList<>();

        initLayout();
    }

    private void initLayout() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setOpaque(false);

        JLabel gameOverLabel = new ScaledJLabel(scale, TEXT_SIZE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(FOREGROUND_COLOR);
        gameOverLabel.setText("Game over");

        JButton newGameButton = new JButton("Play again");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(this);

        innerPanel.add(gameOverLabel);
        innerPanel.add(new JLabel(" ")); // TODO: Replace with proper padding
        innerPanel.add(newGameButton);

        add(innerPanel, new GridBagConstraints());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(OVERLAY_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();

        super.paintComponent(g);
    }

    @Override
    public void onNotify(Game game) {
        setVisible(game.getState() == State.GAME_OVER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyButtonClickListeners(ButtonType.NEW_GAME);
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
