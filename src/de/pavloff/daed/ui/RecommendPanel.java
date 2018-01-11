package de.pavloff.daed.ui;

import javax.swing.*;
import java.awt.*;

public class RecommendPanel extends JPanel {

    public RecommendPanel() {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(1, 200));
        add(new JLabel("Recommendations"));
    }

    public Component getComponent() {
        return this;
    }
}
