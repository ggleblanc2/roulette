package com.ggl.roulette.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.ggl.roulette.controller.PlayerSelectionListener;
import com.ggl.roulette.model.Player;
import com.ggl.roulette.model.RouletteModel;

public class PlayerPanel {
	
private final PlayerTableModel tableModel;

	private final Color blueBarColor;
	
	private final RouletteFrame frame;
	
	private final RouletteModel model;
	
	private final JPanel panel;
	
	private JTable table;

	public PlayerPanel(RouletteFrame frame, RouletteModel model) {
		this.frame = frame;
		this.model = model;
		this.blueBarColor = new Color(184, 207, 229);
		this.tableModel = new PlayerTableModel();
		this.panel = createPlayerPanel();
	}
	
	private JPanel createPlayerPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		String[] columnHeaders = { "Player Name", "Player Chip", "Buy In Amount",
				"Chip Value", "Current Amount", "Number of Chips"  
		};
		for (int i = 0; i < columnHeaders.length; i++) {
			tableModel.addColumn(columnHeaders[i]);
		}
		
		updateTableModel();
		
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new PlayerSelectionListener(frame, model)); 
		
		int tableWidth = 0;
		int[] columnWidths = { 200, 75, 75, 100, 100, 100 };
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(columnWidths[i]);
			tableWidth += columnWidths[i];
		}
		
		table.getColumnModel().getColumn(0).setCellRenderer(new StringRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new AmountRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new AmountRenderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new AmountRenderer());
		table.getColumnModel().getColumn(5).setCellRenderer(new CountRenderer());

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(tableWidth + 50, 600));
		
		return panel;
	}
	
	public void updateTableModel() {
		tableModel.setRowCount(0);
		for (Player player : model.getPlayers()) {
			addPlayer(player);
		}
		
	}
	
	private void addPlayer(Player player) {
		Object[] object = new Object[6];
		object[0] = player.getName();
		object[1] = player.getChipImage();
		object[2] = player.getBuyInAmount();
		object[3] = player.getChipValue();
		object[4] = player.getBalance();
		object[5] = player.getChipCount();
		
		tableModel.addRow(object);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public JTable getTable() {
		return table;
	}

	private class PlayerTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return BufferedImage.class;
			default:
				return Integer.class;
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		
	}
	
	private class ImageRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel();
		
		private JLabel label = new JLabel();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setIcon(new ImageIcon((BufferedImage) value));
			panel.add(label);
			if (isSelected) {
				panel.setBackground(blueBarColor);
			} else {
				panel.setBackground(Color.WHITE);
			}
			table.setRowHeight(panel.getPreferredSize().height);
			return panel;
		}
		
	}
	
	private class CountRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		private JLabel label = new JLabel();
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setHorizontalAlignment(JLabel.TRAILING);
			label.setText(String.format("%,d", (Integer) value));
			panel.add(label);
			if (isSelected) {
				panel.setBackground(blueBarColor);
			} else {
				panel.setBackground(Color.WHITE);
			}
			return panel;
		}
		
	}
	
	private class AmountRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		private JLabel label = new JLabel();
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setHorizontalAlignment(JLabel.TRAILING);
			label.setText("$" + String.format("%,d", (Integer) value) + ".00");
			panel.add(label);
			if (isSelected) {
				panel.setBackground(blueBarColor);
			} else {
				panel.setBackground(Color.WHITE);
			}
			return panel;
		}
		
	}
	
	private class StringRenderer implements TableCellRenderer {
		
		private JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));

		private JLabel label = new JLabel();
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			label.setText((String) value);
			panel.add(label);
			if (isSelected) {
				panel.setBackground(blueBarColor);
			} else {
				panel.setBackground(Color.WHITE);
			}
			return panel;
		}
		
	}

}
