package net.museti.dynasty.client.util;

import java.io.File;

import net.museti.dynasty.client.Config;

public class FileManager {

	public FileManager() {
		firstRun();
	}

	public void firstRun() {
		String dir = System.getProperty("user.home") + "/AppData/Roaming/.mucache";
		File f0 = new File(dir);
		File f1 = new File(dir + File.separator + Config.gTitle.toLowerCase());
		if (!f0.exists())
			f0.mkdir();
		if (!f1.exists())
			f1.mkdir();
	}

	public static void main(String[] args) {
		new FileManager();
	}
}
