package de.pavloff.daed.ui;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    public TablePanel() {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new BorderLayout(10, 10));

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JBScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public Component getComponent() {
        return this;
    }
}
