package com.ggl.roulette.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.ggl.roulette.model.RouletteModel;

public class CallBoardPanel {
	
	private final CallTableModel tableModel;
	
	private final RouletteModel model;
	
	private final JPanel panel;
	
	private JTable table;

	public CallBoardPanel(RouletteModel model) {
		this.model = model;
		this.tableModel = new CallTableModel();
		this.panel = createCallPanel();
	}
	
	private JPanel createCallPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		for (int i = 0; i < 5; i++) {
			tableModel.addColumn("");
		}
		
		int tableWidth = 0;
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			int columnWidth = 60;
			column.setPreferredWidth(columnWidth);
			tableWidth += columnWidth;
		}
		
		table.getColumnModel().getColumn(0).setCellRenderer(new EvenRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(model.getRedColor()));
		table.getColumnModel().getColumn(2).setCellRenderer(new GreenRenderer(model.getGreenColor()));
		table.getColumnModel().getColumn(3).setCellRenderer(new ColorRenderer(Color.BLACK));
		table.getColumnModel().getColumn(4).setCellRenderer(new OddRenderer());

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(tableWidth + 50, 600));
		
		return panel;
	}
	
	public void addCall(String numberString, Color backgroundColor, boolean isBlack) {
		int number = Integer.valueOf(numberString);
		Object[] object = new Object[5];
		if (number == 0) {
			object[0] = false;
			object[1] = false;
			object[2] = numberString;
			object[3] = false;
			object[4] = false;
		} else {
			object[0] = number % 2 == 0;
			object[1] = !isBlack;
			object[2] = numberString;
			object[3] = isBlack;
			object[4] = number % 2 != 0;
		}
		
		tableModel.insertRow(0, object);
		if (tableModel.getRowCount() > 20) {
			tableModel.removeRow(20);
		}
	}

	public JPanel getPanel() {
		return panel;
	}
	
	private class CallTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 2:
				return String.class;
			default:
				return Boolean.class;
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		
	}
	
	private class EvenRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel();
		
		private JLabel label = new JLabel();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setHorizontalAlignment(JLabel.CENTER);
			if (value == null) {
				label.setText("");
			} else if ((Boolean) value) {
				label.setText("EVEN");
			} else {
				label.setText("");
			}
			panel.add(label);
			panel.setBackground(Color.WHITE);
			
			return panel;
		}
		
	}
	
	private class OddRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel();
		
		private JLabel label = new JLabel();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setHorizontalAlignment(JLabel.CENTER);
			if (value == null) {
				label.setText("");
			} else if ((Boolean) value) {
				label.setText("ODD");
			} else {
				label.setText("");
			}
			panel.add(label);
			panel.setBackground(Color.WHITE);
			
			return panel;
		}
		
	}
	
	private class ColorRenderer implements TableCellRenderer {
		
		private Color backgroundColor;
		
		private JPanel panel = new JPanel();

		private ColorRenderer(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (value == null) {
				panel.setBackground(Color.WHITE);
			} else if ((Boolean) value) {
				panel.setBackground(backgroundColor);
			} else {
				panel.setBackground(Color.WHITE);
			}
			
			return panel;
		}
	}
	
	private class GreenRenderer implements TableCellRenderer {
		
		private Color backgroundColor;
		
		private JLabel label = new JLabel();
		private JPanel panel = new JPanel();

		private GreenRenderer(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
			this.panel.setBorder(BorderFactory.createEmptyBorder());
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			String text = (String) value;
			label.setHorizontalAlignment(JLabel.CENTER);
			if (text.equals("00") || text.equals("0")) {
				panel.setBackground(backgroundColor);
				label.setForeground(Color.WHITE);
			} else {
				panel.setBackground(Color.WHITE);
				label.setForeground(Color.BLACK);
			}
			label.setText(text);
			panel.add(label);
			table.setRowHeight(panel.getPreferredSize().height);
			
			return panel;
		}
	}

}
