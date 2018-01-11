package de.pavloff.daed.ui;

import javax.swing.*;
import java.awt.*;

public class CodePanel extends JPanel {

    public CodePanel() {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(400, 1));
        add(new JLabel("Code Parts"));
    }

    public Component getComponent() {
        return this;
    }
}
