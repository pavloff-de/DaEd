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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class DataEditor extends UserDataHolderBase implements FileEditor {

    private static final String NAME = "Data Editor";

    private JPanel mainPanel;

    private String filePath;
    private String delimiter;

    DataEditor(String filePath) {
        this.filePath = filePath;

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIUtil.getEditorPaneBackground());

        RecommendPanel recommend = new RecommendPanel();
        mainPanel.add(recommend.getComponent(), BorderLayout.SOUTH);

        CodePanel code = new CodePanel();
        mainPanel.add(code.getComponent(), BorderLayout.WEST);

        TablePanel table = new TablePanel();
        mainPanel.add(table.getComponent(), BorderLayout.CENTER);
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
