package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScorePanel extends JPanel implements GameObserver {
    private static final String ROUND_FORMAT = "Round: %d";
    private static final String LIFES_FORMAT = "Lifes: %d";
    private static final int BORDER_SIZE = 5;

    private JLabel roundLabel;
    private JLabel lifesLabel;

    public ScorePanel() {
        initLayout();
    }

    private void initLayout() {
        roundLabel = new JLabel();
        lifesLabel = new JLabel();

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        add(roundLabel, BorderLayout.LINE_START);
        add(lifesLabel, BorderLayout.LINE_END);
    }

    @Override
    public void onNotify(Game game) {
        roundLabel.setText(String.format(ROUND_FORMAT, game.getRound()));
        lifesLabel.setText(String.format(LIFES_FORMAT, game.getPlayer().getLives()));
    }
}
