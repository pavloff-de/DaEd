package de.pavloff.daed.action;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NewDataEditorDialog extends DialogWrapper {

    private JTextField fileNameText = new JTextField("data.csv");

    private JPanel centerPanel = new JPanel();
    private JPanel northPanel = new JPanel();

    NewDataEditorDialog() {
        super(true);
        getHelpAction().setEnabled(true);
        setTitle("New Dataset");
        init();
    }

    protected JComponent createNorthPanel() {
        northPanel.setLayout(new GridBagLayout());
        northPanel.setPreferredSize(new Dimension(400, 140));

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        northPanel.add(new JLabel("File Name"), c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0;
        northPanel.add(fileNameText, c);

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
}
