package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EconomyPanel extends JPanel implements GameObserver {
    private static final String MONEY_FORMAT = "Money:\n%d";
    private static final int BORDER_SIZE = 5;

    private JLabel moneyLabel;
    private int scale;

    public EconomyPanel(int scale) {
        this.scale = scale;

        initLayout();
    }

    private void initLayout() {
        moneyLabel = new ScaledJLabel(scale);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        add(moneyLabel, BorderLayout.LINE_END);
    }

    @Override
    public void onNotify(Game game) {
        moneyLabel.setText(String.format(MONEY_FORMAT, game.getPlayer().getMoney()));
    }
}
