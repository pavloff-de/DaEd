package de.pavloff.daed.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NewDataEditorDialog extends DialogWrapper {

    private JPanel centerPanel;

    private JPanel northPanel = new JPanel();
    private JTextField fileNameText = new JTextField("data.csv");
    private JTextField delimiterText = new JTextField(",");

    public NewDataEditorDialog() {
        super(true);
        getHelpAction().setEnabled(true);
        setTitle("New Dataset");
        init();
    }

    protected JComponent createNorthPanel() {
        northPanel.setLayout(new GridBagLayout());
        northPanel.setPreferredSize(new Dimension(400, 100));

        JConstraints c = new JConstraints();

        c.setPos(0, 0);
        northPanel.add(new JLabel("File Name"), c);
        c.setPos(1, 0).setWeight(1, 0);
        northPanel.add(fileNameText, c);

        c.setPos(0, 1);
        northPanel.add(new JLabel("Delimiter"), c);
        c.setPos(1, 1).setWeight(1, 0);
        northPanel.add(delimiterText, c);

        return northPanel;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return centerPanel;
    }

    public String getSelectedFileName() {
        return fileNameText.getText();
    }

    public String getSelectedDelimiter() {
        return delimiterText.getText();
    }
}
