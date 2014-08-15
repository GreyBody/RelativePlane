package net.museti.avatar.animator;

import java.io.File;

public class Config {

	public static int videoWidth = 640;
	public static int videoHeight = 480;

	public static String projHome = "";
	public static String proj = "/a";
	public static int timeLengthMinutes = 60;
	public static int timeLengthFrames = 25;
	public static int frames = 1;

	public static void setHome() {
		String a = "G:/Files/cinema";
		String b = "C:/muSeti/cinema";
		File f0 = new File(a);
		if (!f0.exists()) {
			System.out.println("Using " + b + " as Home");
			projHome = b;
		} else {
			System.out.println("Using " + a + " as Home");
			projHome = a;
		}
	}
}
