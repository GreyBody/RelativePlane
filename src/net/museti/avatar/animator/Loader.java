package net.museti.avatar.animator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Loader {

	private JFrame frame;
	private JPanel window = new JPanel();

	public Loader() {
		Config.setHome();
		frame = new JFrame();
		frame.setSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(window);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		window.setLayout(null);
		add();
		frame.setVisible(true);
	}

	JTextField tfprojname = new JTextField();
	JTextField tfmaxLength = new JTextField();
	JTextField tfsize = new JTextField("640x480");

	public void add() {
		JButton bcreate = new JButton("Create");
		tfprojname.setBounds(5, 5, 85, 25);
		tfmaxLength.setBounds(5, 35, 85, 25);
		tfsize.setBounds(5, 65, 85, 25);
		bcreate.setBounds(5, 100, 85, 30);
		bcreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newProject(tfprojname.getText(), Integer.parseInt(tfmaxLength.getText()), tfsize.getText());
			}
		});
		window.add(tfprojname);
		window.add(tfmaxLength);
		window.add(tfsize);
		window.add(bcreate);
	}

	public static void main(String[] args) {
		new Loader();
	}

	public void newProject(String name, int maxLength, String size) {
		System.out.println("Attempting to create Project -" + name + "-");
		try {
			File f0 = new File(Config.projHome + "/" + name);
			File f0d0 = new File(Config.projHome + "/" + name + "/imgframes");
			File f1 = new File(Config.projHome + "/" + name + "/framebin.frame");
			File f2 = new File(Config.projHome + "/" + name + "/lights.world");
			File f3 = new File(Config.projHome + "/" + name + "/models.world");
			File f4 = new File(Config.projHome + "/" + name + "/chunks.world");
			File f25 = new File(Config.projHome + "/" + name + "/config.ini");
			if (!f0.exists())
				f0.mkdir();
			if (!f0d0.exists())
				f0d0.mkdir();
			if (!f1.exists())
				f1.createNewFile();
			if (!f2.exists())
				f2.createNewFile();
			if (!f3.exists())
				f3.createNewFile();
			if (!f4.exists())
				f4.createNewFile();
			if (!f25.exists())
				f25.createNewFile();
			FileWriter fw1 = new FileWriter(f1.getAbsoluteFile());
			FileWriter fw25 = new FileWriter(f25.getAbsoluteFile());
			BufferedWriter bw1 = new BufferedWriter(fw1);
			BufferedWriter bw25 = new BufferedWriter(fw25);
			for (int i = 0; i < maxLength; i++) {
				for (int j = 0; j < 25; j++) {
					bw1.write("fr " + i + " " + j + " 0 0 0 0 0");
					bw1.newLine();
				}
			}
			bw25.write("name:" + name);
			bw25.newLine();
			bw25.write("size:" + maxLength);
			bw25.newLine();
			bw25.write("width:" + size.split("x")[0]);
			bw25.newLine();
			bw25.write("height:" + size.split("x")[1]);
			bw25.newLine();
			bw1.close();
			bw25.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created Project -" + name + "- with -" + maxLength + "- frames");
	}

	public void loadProject(String name) {
	}
}
