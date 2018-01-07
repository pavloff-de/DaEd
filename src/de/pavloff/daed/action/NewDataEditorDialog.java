package de.pavloff.daed.action;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewDataEditorDialog extends DialogWrapper {

    private JTextField fileNameText = new JTextField("data.csv");
    private JRadioButton[] fileTypeChoices;

    private JPanel centerPanel = new JPanel();
    private JPanel northPanel = new JPanel();

    NewDataEditorDialog() {
        super(true);
        getHelpAction().setEnabled(true);
        setTitle("New Dataset");
        init();
    }

    protected JComponent createNorthPanel() {
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

    public String getSelectedFileType() {
        for (JRadioButton fileTypeChoice : fileTypeChoices) {
            if (fileTypeChoice.isSelected()) {
                return fileTypeChoice.getText();
            }
        }
        return "";
    }
}
