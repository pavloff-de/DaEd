package de.pavloff.daed.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.util.ui.UIUtil;
import de.pavloff.daed.ui.CodePanel;
import de.pavloff.daed.ui.RecommendPanel;
import de.pavloff.daed.ui.TablePanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataEditor extends UserDataHolderBase implements FileEditor {

    private static final String NAME = "Data Editor";

    private JSplitPane mainPanel;

    private String delimiter = ",";
    private int sampleRows = 10;

    DataEditor(String filePath) {
        String[] names = new String[0];
        String[][] data = new String[0][0];

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            String[] values;

            if (line != null) {
                values = line.split(delimiter);
                names = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    names[i] = "column_" + i;
                }
                data = new String[sampleRows][values.length];
            }

            int currentRow = 0;
            while (line != null && currentRow < sampleRows) {
                values = line.split(delimiter);
                data[currentRow] = values;
                line = br.readLine();
                currentRow += 1;
            }

            br.close();

        } catch (IOException e) {
            Messages.showErrorDialog("An error has occurred " +
                    "while reading the file " + filePath +
                    "\n" + e.getMessage(), "Error");
        }

        CodePanel code = new CodePanel();
        TablePanel table = new TablePanel(data, names);
        RecommendPanel recommend = new RecommendPanel();

        JSplitPane topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, code, table);
        topPanel.setOneTouchExpandable(true);
        mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, recommend);
        mainPanel.setBackground(UIUtil.getEditorPaneBackground());

        topPanel.setDividerSize(3);
        mainPanel.setDividerSize(3);
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return mainPanel;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setState(@NotNull FileEditorState state) {
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void selectNotify() {

    }

    @Override
    public void deselectNotify() {
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
    }
}
