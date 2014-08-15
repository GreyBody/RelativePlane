package net.museti.avatar.animator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.museti.dynasty.client.Start;

public class CamControl {

	private JFrame frame;
	private framePanel window = new framePanel();

	public CamControl() {
		frame = new JFrame("");
		frame.setSize(new Dimension(500, 350));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(window);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		window.setLayout(null);
		frame.setVisible(true);
		for (int i = 0; i < seconds.length; i++) {
			seconds[i] = new TimeSpot();
			seconds[i].sid = i;
		}
		File f0 = new File(Config.projHome + Config.proj + "/index.proj");
		if (f0.exists()) {
			BufferedReader br = null;
			String[] text = new String[Config.timeLengthMinutes * 60 * Config.timeLengthFrames];
			try {
				String line;
				br = new BufferedReader(new FileReader(Config.projHome + Config.proj + "/index.proj"));
				int i = 0;
				while ((line = br.readLine()) != null) {
					text[i] = line;
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			TimeSpot[] sec = new TimeSpot[Config.timeLengthMinutes * 60];
			for (int i = 0; i < sec.length; i++) {
				sec[i].sid = i;
			}
			for (int i = 0; i < text.length; i++) {
				int id = Integer.parseInt(text[i].split(" ")[1]);
				int frame = Integer.parseInt(text[i].split(" ")[2]);
				float camx = Float.parseFloat(text[i].split(" ")[3]);
				float camy = Float.parseFloat(text[i].split(" ")[4]);
				float camz = Float.parseFloat(text[i].split(" ")[5]);
				float pitch = Float.parseFloat(text[i].split(" ")[6]);
				float yaw = Float.parseFloat(text[i].split(" ")[7]);
				String chunk = text[i].split(" ")[8];
				String instruct = text[i].split(" ")[9];
				for (int j = 0; j < sec.length; j++) {
					if (id == sec[j].sid) {
						sec[j].set[frame] = true;
						sec[j].camx[frame] = camx;
						sec[j].camy[frame] = camy;
						sec[j].camz[frame] = camz;
						sec[j].pitch[frame] = pitch;
						sec[j].yaw[frame] = yaw;
						sec[j].chunk[frame] = chunk;
						sec[j].instructions[frame] = instruct;
					}
				}
			}
			seconds = sec;
		}
		add();
	}

	private int currentTime = 0;
	private int currentFrame = 0;
	private int var = 0;
	private buttonFrame[] frames = new buttonFrame[25];
	public TimeSpot[] seconds = new TimeSpot[Config.timeLengthMinutes * 60];
	private JTextField tftime;
	private JTextArea taview;

	private String origText = "";

	public void add() {
		int butSize = 32;
		int fbutSize = 52;
		JLabel lcontrol;
		JButton bup, bdown, bleft, bright, bascend, bdescend, brotateleft, brotateright, brotateup, brotatedown, bcamera, bsettime, bpreview;
		lcontrol = new JLabel("Camera Control");
		bup = new JButton();
		bdown = new JButton();
		bleft = new JButton();
		bright = new JButton();
		bascend = new JButton();
		bdescend = new JButton();
		brotateleft = new JButton();
		brotateright = new JButton();
		brotateup = new JButton();
		brotatedown = new JButton();
		bcamera = new JButton();
		bsettime = new JButton("Set");
		JButton bsave = new JButton("Save");
		bpreview = new JButton("View");
		tftime = new JTextField();
		taview = new JTextArea();
		taview.setLineWrap(true);
		bup.setIcon(new ImageIcon("res/arrowUp.png"));
		bdown.setIcon(new ImageIcon("res/arrowDown.png"));
		bleft.setIcon(new ImageIcon("res/arrowLeft.png"));
		bright.setIcon(new ImageIcon("res/arrowRight.png"));
		bascend.setIcon(new ImageIcon("res/arrowAscend.png"));
		bdescend.setIcon(new ImageIcon("res/arrowDescend.png"));
		brotateleft.setIcon(new ImageIcon("res/arrowRotateLeft.png"));
		brotateright.setIcon(new ImageIcon("res/arrowRotateRight.png"));
		brotateup.setIcon(new ImageIcon("res/arrowRotateUp.png"));
		brotatedown.setIcon(new ImageIcon("res/arrowRotateDown.png"));
		bcamera.setIcon(new ImageIcon("res/camera.png"));

		lcontrol.setBounds(5, 4, 100, 18);
		bup.setBounds(5 + (butSize + 5), 23, butSize, butSize);
		bdown.setBounds(5 + (butSize + 5), 23 + ((butSize + 5) * 2), butSize, butSize);
		bleft.setBounds(5, 23 + (butSize + 5), butSize, butSize);
		bright.setBounds(5 + ((butSize + 5) * 2), 23 + (butSize + 5), butSize, butSize);
		bascend.setBounds(5, 23, butSize, butSize);
		bdescend.setBounds(5 + ((butSize + 5) * 2), 23, butSize, butSize);
		brotateleft.setBounds(5, 23 + (butSize + 5) * 2, butSize, butSize);
		brotateright.setBounds(5 + (butSize + 5) * 2, 23 + (butSize + 5) * 2, butSize, butSize);
		brotateup.setBounds(5 + (butSize + 5) * 3, 23, butSize, butSize);
		brotatedown.setBounds(5 + (butSize + 5) * 3, 23 + (butSize + 5), butSize, butSize);
		bcamera.setBounds(5 + (butSize + 5), 23 + (butSize + 5), butSize, butSize);
		bsettime.setBounds(5 + (butSize + 5) * 6, 5, 64, butSize);
		bsave.setBounds(5 + (butSize + 5) * 4, 5 + (butSize + 5) * 2, 96, 64);
		bpreview.setBounds(275, 120, 210, 25);
		tftime.setBounds(5 + (butSize + 5) * 4, 10, 64, 24);
		taview.setBounds(275, 150, 210, 160);
		for (var = 0; var < frames.length; var++) {
			final int n = var;
			frames[var] = new buttonFrame("" + (var + 1));
			if (var < 5)
				frames[var].setBounds(5 + (fbutSize * var), 150, fbutSize, 32);
			else if (var >= 5 && var < 10)
				frames[var].setBounds(5 + (fbutSize * (var - 5)), 150 + 32, fbutSize, 32);
			else if (var >= 10 && var < 15)
				frames[var].setBounds(5 + (fbutSize * (var - 10)), 150 + 64, fbutSize, 32);
			else if (var >= 15 && var < 20)
				frames[var].setBounds(5 + (fbutSize * (var - 15)), 150 + 96, fbutSize, 32);
			else if (var >= 20)
				frames[var].setBounds(5 + (fbutSize * (var - 20)), 150 + 128, fbutSize, 32);
			frames[var].id = var;
			frames[var].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentFrame = n + 1;
					for (int i = 0; i < frames.length; i++) {
						frames[i].setBackground(Color.black);
					}
					frames[n].setBackground(Color.DARK_GRAY);
					if (seconds[currentTime].set[n])
						taview.setText(seconds[currentTime].instructions[n]);
				}
			});
			window.add(frames[var]);
		}
		bup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.walkForward(0.25f);
			}
		});
		bdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.walkBackwards(0.25f);
			}
		});
		bleft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.strafeLeft(0.25f);
			}
		});
		bright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.strafeRight(0.25f);
			}
		});
		bascend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.flyUp(0.25f);
			}
		});
		bdescend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.flyDown(0.25f);
			}
		});
		brotateleft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.yawinverse(2.5f);
			}
		});
		brotateright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.yaw(2.5f);
			}
		});
		brotateup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.pitchinverse(2.5f);
			}
		});
		brotatedown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.fpc.pitch(2.5f);
			}
		});
		bcamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if (s != null)
				//	setCamera(currentTime, currentFrame, s.fpc.getX(), s.fpc.getY(), s.fpc.getY(), s.fpc.getYaw(), s.fpc.getPitch(), "");
			}
		});
		bsettime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTime = Integer.parseInt(tftime.getText());
				System.out.println("Time Set To: " + currentTime);
			}
		});
		bsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTimeSpots();
			}
		});
		bpreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Start.initCommand(new String[] { "clearbuffer:", "" });
					seconds[currentTime].instructions[currentFrame] = taview.getText().replace(" ", "");
					String[] text = taview.getText().replace(" ", "").split(";");
					if (taview.getText().length() <1)
						return;
					for (int i = 0; i < text.length; i++) {
						String s0 = text[i].split(":")[0] + ":";
						String s1 = text[i].split(":")[1];
						String[] s2 = { s0, s1 };
						Start.initCommand(s2);
					}
				} catch (IOException e0) {
					e0.printStackTrace();
				}
			}
		});
		window.add(lcontrol);
		window.add(bup);
		window.add(bdown);
		window.add(bleft);
		window.add(bright);
		window.add(bascend);
		window.add(bdescend);
		window.add(brotateleft);
		window.add(brotateright);
		window.add(brotateup);
		window.add(brotatedown);
		window.add(bcamera);
		window.add(bsettime);
		window.add(bsave);
		window.add(bpreview);
		window.add(tftime);
		window.add(taview);
	}

	public void setCamera(int second, int frame, float x, float y, float z, float yaw, float pitch, String chunk) {
		for (int i = 0; i < seconds.length; i++) {
			if (i == seconds[i].sid) {
				seconds[i].set[frame] = true;
				seconds[i].camx[frame] = x;
				seconds[i].camy[frame] = y;
				seconds[i].camz[frame] = z;
				seconds[i].yaw[frame] = yaw;
				seconds[i].pitch[frame] = pitch;
				seconds[i].chunk[frame] = chunk;
				break;
			}
		}
	}

	public static void main(String[] args) {
		new CamControl();
	}

	public void saveTimeSpots() {
		try {
			File f0 = new File(Config.projHome + Config.proj + "/index.proj");
			if (!f0.exists())
				f0.createNewFile();
			FileWriter fw = new FileWriter(f0.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < seconds.length; i++) {
				//if (s != null) {
				for (int j = 0; j < 25; j++) {
					if (seconds[i].set[j]) {
						bw.write("fr " + seconds[i].sid + " " + j + " " + seconds[i].camx[j] + " " + seconds[i].camy[j] + " " + seconds[i].camz[j] + " " + seconds[i].yaw[j] + " " + seconds[i].pitch[j] + " " + seconds[i].chunk[j] + " " + seconds[i].instructions[j]);
						bw.newLine();
					}
				}
				//}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class framePanel extends JPanel {

	public void paintComponent(Graphics g) {
	}
}

class buttonFrame extends JButton {

	public int id;

	public buttonFrame(String t) {
		this.setText(t);
		this.setBackground(Color.black);
		this.setForeground(Color.LIGHT_GRAY);
		this.setBorder(null);
	}
}

class TimeSpot {
	int sid;
	boolean[] set = new boolean[25];
	float[] camx = new float[25];
	float[] camy = new float[25];
	float[] camz = new float[25];
	float[] yaw = new float[25];
	float[] pitch = new float[25];
	String[] chunk = new String[25];
	String[] instructions = new String[25];
}
