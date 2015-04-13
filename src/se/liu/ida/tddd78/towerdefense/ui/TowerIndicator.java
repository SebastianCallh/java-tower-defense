package se.liu.ida.tddd78.towerdefense.ui;

import se.liu.ida.tddd78.towerdefense.Game;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TowerIndicator extends JPanel implements GameObserver {
    private static final Logger LOG = Logger.getLogger(TowerIndicator.class.getName());

    private static final int TEXT_PADDING = 3;
    private static final int BORDER_THICKNESS = 2;
    private static final int SIZE = 65;
    private static final Color BORDER_COLOR_INACTIVE = Color.gray;
    private static final Color BORDER_COLOR_ACTIVE = Color.black;
    private static final Color BORDER_COLOR_INSUFFICIENT_MONEY = new Color(0xB30000);
    private static final Color BACKGROUND_NOT_IMPLEMENTED = Color.lightGray;
    private static final Color BACKGROUND_INSUFFICIENT_MONEY = Color.red;

    private int scale;
    private String name;
    private String mappedKey;
    private DefenseType type;
    private boolean constructable;
    private int cost;

    private Border inactiveBorder;
    private Border activeBorder;
    private Border inactiveBorderInsufficientMoney;

    public TowerIndicator(int scale, String name, String mappedKey, DefenseType type) {
        this.scale = scale;
        this.name = name;
        this.mappedKey = mappedKey;
        this.type = type;

        retrieveCost();
        initLayout();
    }

    private void initLayout() {
        JLabel nameLabel = new ScaledJLabel(scale);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new EmptyBorder(TEXT_PADDING * scale, TEXT_PADDING * scale, TEXT_PADDING * scale, TEXT_PADDING * scale));
        nameLabel.setText(name);

        JPanel lowerPanel = createSubPanel();
        JPanel upperPanel = createSubPanel();

        JLabel costLabel = new ScaledJLabel(scale, 10);
        costLabel.setText(getDefenseCost());

        JLabel mappedKeyLabel = new ScaledJLabel(scale, 10);
        mappedKeyLabel.setText(mappedKey);

        inactiveBorder = new LineBorder(BORDER_COLOR_INACTIVE, BORDER_THICKNESS * scale);
        activeBorder = new LineBorder(BORDER_COLOR_ACTIVE, BORDER_THICKNESS * scale);
        inactiveBorderInsufficientMoney = new LineBorder(BORDER_COLOR_INSUFFICIENT_MONEY, BORDER_THICKNESS * scale);

        setLayout(new BorderLayout());
        setBorder(inactiveBorder);

        if (!constructable) {
            setBackground(BACKGROUND_NOT_IMPLEMENTED);
        }

        upperPanel.add(mappedKeyLabel, BorderLayout.LINE_END);
        lowerPanel.add(costLabel, BorderLayout.LINE_END);

        add(nameLabel, BorderLayout.CENTER);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);
    }

    private JPanel createSubPanel() {
        JPanel subPanel = new JPanel();
        subPanel.setOpaque(false);
        subPanel.setLayout(new BorderLayout());
        subPanel.setBorder(new EmptyBorder(TEXT_PADDING * scale, TEXT_PADDING * scale, TEXT_PADDING * scale, TEXT_PADDING * scale));

        return subPanel;
    }

    private void retrieveCost() {
        try {
            cost = DefenseFactory.makeDefense(type).getCost();
            constructable = true;
        } catch (TypeNotSupportedException e) {
            LOG.log(Level.WARNING, "Unable to retrieve cost from unsupported defense type", e);
            cost = 0;
            constructable = false;
        }
    }

    private String getDefenseCost() {
        if (constructable) {
            return "Cost: " + cost;
        } else {
            return "Cost: ?";
        }
    }

    private boolean hasSufficientMoney(Game game) {
        return !constructable || game.getPlayer().getMoney() >= cost;
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();

        return new Dimension(SIZE * scale, SIZE * scale);
    }

    @Override
    public void onNotify(Game game) {
        DefenseType selectedDefenseType = game.getPlayer().getSelectedDefense();
        if (selectedDefenseType == type) {
            setBorder(activeBorder);
        } else {
            if (hasSufficientMoney(game)) {
                setBorder(inactiveBorder);
            } else {
                setBorder(inactiveBorderInsufficientMoney);
            }
        }

        if (hasSufficientMoney(game)) {
            if (constructable) {
                setOpaque(false);
            }
        } else {
            setBackground(BACKGROUND_INSUFFICIENT_MONEY);
            setOpaque(true);
        }
    }
}
