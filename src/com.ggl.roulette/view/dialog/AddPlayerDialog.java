package com.ggl.roulette.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.ggl.roulette.controller.AddPlayerListener;
import com.ggl.roulette.model.RouletteModel;
import com.ggl.roulette.view.RouletteFrame;

public class AddPlayerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final AddPlayerPanel addPlayerPanel;
	
	public AddPlayerDialog(RouletteFrame frame, RouletteModel model, String title) {
		super(frame.getFrame(), true);
		this.addPlayerPanel = new AddPlayerPanel(model);
		setTitle(title);
		add(createMainPanel(frame, model), BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(frame.getFrame());
		setVisible(true);
	}
	
	private JPanel createMainPanel(RouletteFrame frame, RouletteModel model) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(addPlayerPanel.getPanel(), BorderLayout.CENTER);
		
		JPanel innerPanel = new JPanel();
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new AddPlayerListener(frame, model, this));
		innerPanel.add(okButton);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		innerPanel.add(button);
		
		okButton.setPreferredSize(button.getPreferredSize());
		
		panel.add(innerPanel, BorderLayout.AFTER_LAST_LINE);
		
		return panel;
	}

	public AddPlayerPanel getAddPlayerPanel() {
		return addPlayerPanel;
	}

}
