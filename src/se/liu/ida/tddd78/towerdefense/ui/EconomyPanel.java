package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EconomyPanel extends JPanel implements GameObserver {
    private static final String MONEY_FORMAT = "Money:\n%d";
    private static final int BORDER_SIZE = 5;
    private static final int RIGHT_BORDER_SIZE = 20;

    private JLabel moneyLabel;
    private TowerIndicator[] towerIndicators;
    private int scale;

    public EconomyPanel(int scale) {
        this.scale = scale;

        initLayout();
    }

    private void initLayout() {
        moneyLabel = new ScaledJLabel(scale);

        JPanel towerContainer = new JPanel();
        towerContainer.setLayout(new FlowLayout());
        towerIndicators = new TowerIndicator[] {
                new TowerIndicator(scale, "Small", "1", DefenseType.SMALL),
                new TowerIndicator(scale, "Big", "2", DefenseType.BIG),
                new TowerIndicator(scale, "Fast", "3", DefenseType.FAST)
        };

        for (TowerIndicator towerIndicator : towerIndicators) {
            towerContainer.add(towerIndicator);
        }

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(BORDER_SIZE * scale, BORDER_SIZE * scale, BORDER_SIZE * scale, RIGHT_BORDER_SIZE * scale));

        add(moneyLabel, BorderLayout.LINE_END);
        add(towerContainer, BorderLayout.LINE_START);
    }

    @Override
    public void onNotify(Game game) {
        moneyLabel.setText(String.format(MONEY_FORMAT, game.getPlayer().getMoney()));
        for (TowerIndicator towerIndicator : towerIndicators) {
            towerIndicator.onNotify(game);
        }
    }
}
