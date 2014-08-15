package net.museti.dynasty.client.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Terminal {

	public JFrame frame;
	private JTextArea tfa;

	public Terminal(String title) {
		frame = new JFrame(title);
		frame.setSize(new Dimension(400, 250));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		tfa = new JTextArea();
		tfa.setEditable(false);
		tfa.setBackground(Color.black);
		tfa.setForeground(Color.white);
		frame.getContentPane().add(tfa);
	}

	public void nText(String text) {
		tfa.append(text);
	}

	public void nLine(String text) {
		tfa.append(text + "\n");
	}
}
