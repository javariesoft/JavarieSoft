/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.fungsi;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author JavarieSoft
 */
public class DecimalFormatRenderer extends DefaultTableCellRenderer {
////    private static final DecimalFormat formatter = new DecimalFormat("#0.00");
    private static final DecimalFormat formatter = new DecimalFormat("###,###,###,###.00");
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
// First format the cell value as require
        value = formatter.format((Number) value);
        setHorizontalAlignment(SwingConstants.RIGHT);
        return super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        
    }
}
