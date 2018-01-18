package de.pavloff.daed.model;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TableModel extends DefaultTableModel {

    private JPanel tablePanel;
    private JBTable tableView;
    private String[][] rowData;
    private String[] columnNames;

    public TableModel(String[][] data, String[] names) {
        super(data, names);

        rowData = data;
        columnNames = names;

        tableView = new JBTable(this);
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.addMouseListener(new TableListener(this));

        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        tablePanel.setLayout(new BorderLayout(10, 10));
        tablePanel.add(new JBScrollPane(tableView), BorderLayout.CENTER);
    }

    public JPanel getView() {
        return tablePanel;
    }

    public void handleTableEvent(MouseEvent e) {
        Point p = e.getPoint();

        int row = tableView.rowAtPoint(p);
        int col = tableView.columnAtPoint(p);

        // colName
        // cellValue
        // colType
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // readonly table
        return false;
    }
}
