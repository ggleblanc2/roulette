package com.ggl.roulette.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.ggl.roulette.model.Wager;
import com.ggl.roulette.view.RouletteFrame;

public class WinningsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final WinningsPanel winningsPanel;
	
	public WinningsDialog(RouletteFrame frame, List<Wager> winners, 
			String number, String title) {
		super(frame.getFrame(), true);
		this.winningsPanel = new WinningsPanel(winners, number);
		setTitle(title);
		add(createMainPanel(), BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(frame.getFrame());
		setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(winningsPanel.getPanel(), BorderLayout.CENTER);
		
		JPanel innerPanel = new JPanel();
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		innerPanel.add(button);
		
		panel.add(innerPanel, BorderLayout.AFTER_LAST_LINE);
		
		return panel;
	}

	public WinningsPanel getWinningsPanel() {
		return winningsPanel;
	}

}
