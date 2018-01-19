package de.pavloff.daed.editor;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

public class DataEditorFactory {
    private static DataEditorFactory instance = new DataEditorFactory();

    private Map<String, DataEditor> fileToEditor = new Hashtable<>();

    public static DataEditorFactory getInstance() {
        return instance;
    }

    public DataEditor getEditor(String path) {
        String realPath = new File(path).getPath();
        DataEditor editor = fileToEditor.get(realPath);
        if (editor == null) {
            editor = new DataEditor(realPath);
            fileToEditor.put(realPath, editor);
        }
        return editor;
    }
}
