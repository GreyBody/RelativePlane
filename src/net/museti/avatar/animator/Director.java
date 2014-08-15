package net.museti.avatar.animator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.museti.dynasty.client.Start;

public class Director {

	private JFrame frame;
	private JPanel window = new JPanel();

	//private Start start = new Start();
	//private ImageMaker im = new ImageMaker(start);

	public Director() {
		frame = new JFrame();
		frame.setSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(window);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		window.setLayout(null);
		frame.setVisible(true);
		add();
	}

	private JProgressBar pb = new JProgressBar();

	public void add() {
		JButton bcaptureimages = new JButton("Capture Images");
		JButton bcompileimages = new JButton("Compile Images");
		JButton bexit = new JButton("Exit");
		bcaptureimages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bcompileimages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		bcaptureimages.setBounds(5, 5, 125, 30);
		bcompileimages.setBounds(5, 40, 125, 30);
		bexit.setBounds(5, 75, 125, 30);
		pb.setBounds(5, 110, 125, 20);
		window.add(bcaptureimages);
		window.add(bcompileimages);
		window.add(bexit);
		window.add(pb);
	}

	public static void main(String[] args) {
		new Director();
	}
}
