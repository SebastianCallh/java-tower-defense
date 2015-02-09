import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Seba on 2015-01-23.
 */
public class Frame extends JFrame {

    public Frame(String title, Board board) throws HeadlessException {
        super(title);
        this.setLayout(new BorderLayout());
        this.add(new Painter(board), BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.pack();
        this.setVisible(true);
    }
}
