package de.pavloff.daed.ui;

import java.awt.*;

public class JConstraints extends GridBagConstraints {

    public JConstraints() {
        gridx = 0;
        gridy = 0;
        gridwidth = 1;
        gridheight = 1;

        weightx = 0;
        weighty = 0;
        anchor = WEST;
        fill = HORIZONTAL;

        insets = new Insets(0, 0, 0, 0);
        ipadx = 0;
        ipady = 0;
    }

    /*
     * Specifies the cell containing the leading edge of the component's
     * display area, where the topmost cell has <code>y=0</code> and
     * the first cell in a row has <code>x=0</code>
     */
    public JConstraints setPos(int x, int y) {
        this.gridx = x;
        this.gridy = y;
        return this;
    }

    /*
     * Specifies the number of cells in a row <code>x</code> and
     * in a column <code>y</code> for the component's display area
     */
    public JConstraints setSize(int x, int y) {
        this.gridwidth = x;
        this.gridheight = y;
        return this;
    }

    /*
     defines a weighted distribution of the available space on the individual grid cells
     */
    public JConstraints setWeight(int x, int y) {
        this.weightx = x;
        this.weighty = y;
        return this;
    }
}
