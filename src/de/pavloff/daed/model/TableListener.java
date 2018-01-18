package de.pavloff.daed.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableListener implements MouseListener {

    private TableModel tableModel;

    TableListener(TableModel model) {
        tableModel = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        tableModel.handleTableEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
