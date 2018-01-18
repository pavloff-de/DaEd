package de.pavloff.daed.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.util.ui.UIUtil;
import de.pavloff.daed.model.CodeModel;
import de.pavloff.daed.model.RecommendModel;
import de.pavloff.daed.model.TableModel;
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

        CodeModel codeModel = new CodeModel();
        TableModel tableModel = new TableModel(data, names);
        RecommendModel recommendModel = new RecommendModel();

        JSplitPane topPanel = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, codeModel.getView(), tableModel.getView());
        topPanel.setDividerSize(3);

        mainPanel = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, topPanel, recommendModel.getView());
        mainPanel.setBackground(UIUtil.getEditorPaneBackground());
        mainPanel.setOneTouchExpandable(true);
        mainPanel.setDividerSize(3);

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
