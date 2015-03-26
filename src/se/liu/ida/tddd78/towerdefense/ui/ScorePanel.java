package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScorePanel extends JPanel implements GameObserver {
    private static final String ROUND_FORMAT = "Round: %d";
    private static final String LIVES_FORMAT = "Lives: %d";
    private static final int BORDER_SIZE = 5;

    private JLabel roundLabel;
    private JLabel livesLabel;

    private int scale;

    public ScorePanel(int scale) {
        this.scale = scale;
        initLayout();
    }

    private void initLayout() {
        roundLabel = new ScaledJLabel(this.scale);
        livesLabel = new ScaledJLabel(this.scale);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        add(roundLabel, BorderLayout.LINE_START);
        add(livesLabel, BorderLayout.LINE_END);
    }

    @Override
    public void onNotify(Game game) {
        roundLabel.setText(String.format(ROUND_FORMAT, game.getRound()));
        livesLabel.setText(String.format(LIVES_FORMAT, game.getPlayer().getLives()));
    }
}
