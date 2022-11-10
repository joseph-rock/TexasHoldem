package presentation.components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Scoreboard extends JTable {

    public Scoreboard() {
        super();
        this.setDefaultEditor(Object.class, null);
        this.setFocusable(false);
        this.setRowSelectionAllowed(false);
        this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setRowHeight(50);
        this.setOpaque(false);

        // Set col labels
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Rank");
        model.addColumn("Name");
        model.addColumn("Hand");
        model.addColumn("Encoding");
        this.setModel(model);

        // Set width of columns
        TableColumnModel colModel = this.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(10);
        colModel.getColumn(1).setPreferredWidth(100);
        colModel.getColumn(2).setPreferredWidth(10);
        colModel.getColumn(3).setPreferredWidth(10);
        this.setColumnModel(colModel);

        // Center text
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableModel tableModel = this.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            this.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

    }

}
