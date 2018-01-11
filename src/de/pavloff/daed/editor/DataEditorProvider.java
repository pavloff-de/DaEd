package de.pavloff.daed.editor;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class DataEditorProvider implements ApplicationComponent, FileEditorProvider {
    /** The editor component name. */
    private static final String NAME = "DataEditorProvider";
    /** The editor type id. */
    private static final String EDITOR_TYPE_ID = "Data Editor";

    public DataEditorProvider() {}

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
