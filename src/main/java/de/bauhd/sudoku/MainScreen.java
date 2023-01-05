package de.bauhd.sudoku;

import de.bauhd.sudoku.lib.SudokuSolver;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public final class MainScreen extends JFrame {

    public static void main(String[] args) {
        new MainScreen();
    }

    private final byte[][] grid;

    private MainScreen() {
        super("Sudoku Solver");

        this.grid = new byte[9][9];

        final var table = new JTable(9, 9);
        final var renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        table.getModel().addTableModelListener(this::tableChanged);
        final var tableSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(tableSorter);
        this.add(table);

        final var button = new JButton("Solve");
        button.addActionListener(event -> {
            new SudokuSolver(this.grid).solve();
            for (var i = 0; i < this.grid.length; i++) {
                final var row = this.grid[i];
                for (int j = 0; j < row.length; j++) {
                    final var b = row[j];
                    table.setValueAt(b, i, j);
                }
            }
        });
        this.add(button);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                table.setBounds(MainScreen.this.getWidth() / 2 - 192 , (int) (0.2 * MainScreen.this.getHeight()), 384, table.getRowHeight() * table.getRowCount());
                button.setBounds(MainScreen.this.getWidth() / 2 - 64, (int) (0.8 * MainScreen.this.getHeight()), 128, 48);
            }
        });
        this.setLayout(null);
        this.setSize(512, 512);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void tableChanged(final TableModelEvent event) {
        this.grid[event.getFirstRow()][event.getColumn()] = (byte) Character.digit(((TableModel) event.getSource())
                .getValueAt(event.getFirstRow(), event.getColumn()).toString().charAt(0), 10);
    }
}
