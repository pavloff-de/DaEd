package de.pavloff.daed.model;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class RecommendModel {

    private JPanel recommendPanel;
    private JPanel recommendView;

    public RecommendModel() {
        recommendView = new JPanel();
        recommendView.add(new JLabel("Recommendations"));

        recommendPanel = new JPanel();
        recommendPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        recommendPanel.setLayout(new BorderLayout(10, 10));
        recommendPanel.add(new JBScrollPane(recommendView), BorderLayout.CENTER);
    }

    public JPanel getView() {
        return recommendPanel;
    }
}
