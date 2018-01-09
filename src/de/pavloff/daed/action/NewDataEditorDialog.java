package de.pavloff.daed.action;

import com.intellij.openapi.ui.DialogWrapper;
import de.pavloff.daed.ui.JConstraints;
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
        northPanel.setPreferredSize(new Dimension(400, 70));

        JConstraints c = new JConstraints();

        c.setPos(0, 0);
        northPanel.add(new JLabel("File Name"), c);

        c.setPos(1,0).setWeight(1,0);
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
