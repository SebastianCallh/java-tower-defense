package se.liu.ida.tddd78.towerdefense.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Presents a text with the possibility of scaling.
 */
public class ScaledJLabel extends JLabel {
    private int scale;

    public ScaledJLabel(int scale) {
        this.scale = scale;

        setFont(getFont());
    }

    public ScaledJLabel(int scale, int size) {
        this.scale = scale;

        setFont(getFont().deriveFont((float) size));
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
