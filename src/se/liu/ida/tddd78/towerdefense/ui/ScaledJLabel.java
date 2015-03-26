package se.liu.ida.tddd78.towerdefense.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sebastian on 2015-03-26.
 */
public class ScaledJLabel extends JLabel {
    private int scale;

    public ScaledJLabel(int scale) {
        this.scale = scale;

        setFont(getFont());
    }

    private Font createScaledFont(Font font) {
        return font.deriveFont((float) font.getSize() * scale);
    }

    @Override
    public void setFont(Font font) {
        if (this.scale == 0) {
            super.setFont(font);
        } else {
            super.setFont(createScaledFont(font));
        }
    }
}
