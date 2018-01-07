package de.pavloff.daed.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class NewDataEditorAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        final Project project = dataContext.getData(PlatformDataKeys.PROJECT);
        assert project != null;

        VirtualFile selectedFile = dataContext.getData(PlatformDataKeys.VIRTUAL_FILE);

        NewDataEditorDialog editorDialog = new NewDataEditorDialog();
        editorDialog.show();

        if (editorDialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            String fileName = editorDialog.getSelectedFileName();

            if (fileName == null || selectedFile == null) {
                return;
            }
            if (!selectedFile.isDirectory()) {
                selectedFile = selectedFile.getParent();
            }
            if (selectedFile == null) {
                return;
            }

            final File f = new File(selectedFile.getPath(), fileName);

            if (!f.exists()) {
                return;
            }

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                public void run() {
                    VirtualFile v = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f);
                    assert v != null;

                    FileEditorManager.getInstance(project).openFile(v, true);
                }
            });
        }
    }
}
