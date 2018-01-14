package de.pavloff.daed.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataTableModel implements TableModel {

    private int rowCount;
    private int columnCount;
    private String[] columnNames;
    private String[][] tableData;

    public DataTableModel(String[] names, String[][] data) {
        columnNames = names;
        tableData = data;
        rowCount = data.length;
        if (rowCount > 0) {
            columnCount = data[0].length;
        } else {
            columnCount = 0;
        }
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex < columnNames.length && (columnIndex >= 0)) {
            return columnNames[columnIndex];
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // readonly table
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
