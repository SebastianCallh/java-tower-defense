package se.liu.ida.tddd78.towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Acts as the window that contains the game.
 */
public class GameWindow extends JFrame {
    private JPanel innerPanel;

    public GameWindow(String title) throws HeadlessException {
        super(title);

        this.innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    public void addInnerComponent(JComponent component, String placement) {
        innerPanel.add(component, placement);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension normalSize = super.getPreferredSize();
        Dimension contentSize = innerPanel.getPreferredSize();

        return new Dimension(normalSize.width + contentSize.width, normalSize.height + contentSize.height);
    }

    public void addLayeredComponent(JComponent component, Integer layer) {
        getLayeredPane().add(component, layer);
        component.setBounds(0, 0, (int) innerPanel.getPreferredSize().getWidth(), (int) innerPanel.getPreferredSize().getHeight());
    }

    public void create() {
        addLayeredComponent(innerPanel, JLayeredPane.DEFAULT_LAYER);
        pack();
        setVisible(true);
    }

}
