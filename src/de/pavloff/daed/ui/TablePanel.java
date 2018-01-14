package de.pavloff.daed.ui;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import de.pavloff.daed.model.DataTableModel;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    public TablePanel(String[] names, String[][] data) {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new BorderLayout(10, 10));

        DataTableModel dataModel = new DataTableModel(names, data);
        JTable table = new JBTable(dataModel);
        JScrollPane scrollPane = new JBScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public Component getComponent() {
        return this;
    }
}
