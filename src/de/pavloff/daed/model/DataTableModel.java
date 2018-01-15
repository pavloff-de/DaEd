package de.pavloff.daed.model;

import javax.swing.table.DefaultTableModel;

public class DataTableModel extends DefaultTableModel {

    public DataTableModel(String[][] data, String[] names) {
        super(data, names);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // readonly table
        return false;
    }
}
