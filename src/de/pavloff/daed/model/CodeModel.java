package de.pavloff.daed.model;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class CodeModel {

    private JPanel codePanel;
    private JPanel codeView;

    public CodeModel() {
        codeView = new JPanel();
        codeView.add(new JLabel("Code Parts"));

        codePanel = new JPanel();
        codePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        codePanel.setLayout(new BorderLayout(10, 10));
        codePanel.add(new JBScrollPane(codeView), BorderLayout.CENTER);
    }

    public JPanel getView() {
        return codePanel;
    }
}
