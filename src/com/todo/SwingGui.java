package com.todo;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SwingGui extends JFrame {
	static JPanel TablePn = new TablePanel();

	public SwingGui() {
		setTitle("Seokmin's TodoList App");

		JPanel pn = new JPanel();

		GridLayout gl = new GridLayout(1, 1);
		pn.setLayout(gl);

		pn.add(TablePn);

		setContentPane(pn);
		setSize(1100, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
