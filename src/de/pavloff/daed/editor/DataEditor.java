package de.pavloff.daed.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.util.ui.UIUtil;
import de.pavloff.daed.ui.CodePanel;
import de.pavloff.daed.ui.RecommendPanel;
import de.pavloff.daed.ui.TablePanel;
import de.pavloff.daed.util.DataFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class DataEditor extends UserDataHolderBase implements FileEditor {

    private static final String NAME = "Data Editor";

    private JSplitPane mainPanel;

    private String delimiter = ",";
    private int sampleRows = 10;

    DataEditor(String filePath) {
        DataFileHelper dh = new DataFileHelper(filePath);

        String[][] data = dh.getData(sampleRows);
        String[] names = dh.getNames();

        if (names.length == 0 && data.length != 0 && data[0].length != 0) {
            names = new String[data[0].length];

            for (int i = 0; i < data[0].length; i++) {
                names[i] = "column" + i;
            }
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
