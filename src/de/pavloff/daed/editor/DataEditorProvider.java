package de.pavloff.daed.editor;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class DataEditorProvider implements ApplicationComponent, FileEditorProvider {

    private static final String NAME = "DataEditorProvider";
    private static final String EDITOR_TYPE_ID = "Data Editor";

    public DataEditorProvider() {
    }

    public boolean accept(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return "csv".equalsIgnoreCase(virtualFile.getExtension());
    }

    @NotNull
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new DataEditor(virtualFile.getPath());
    }

    @NotNull
    public String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    @NotNull
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
    }

}
