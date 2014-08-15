package net.museti.dynasty.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.lwjgl.opengl.GL11;

public class Chunk {

	private int x;
	private int z;

	private float[] cmapRed = new float[16 * 16];
	private float[] cmapGreen = new float[16 * 16];
	private float[] cmapBlue = new float[16 * 16];
	private byte[] v0hmap = new byte[16 * 16];
	private byte[] v1hmap = new byte[16 * 16];
	private byte[] v2hmap = new byte[16 * 16];
	private byte[] v3hmap = new byte[16 * 16];

	public Chunk(int x, int z) {
		this.x = x;
		this.z = z;
		File f0 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + z + ".json");
		if (!f0.exists())
			return;
		loadChunkColorMap();
		loadHeightMap();
	}

	public void render() {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				GL11.glColor3f(cmapRed[x + z * 16], cmapGreen[x + z * 16], cmapBlue[x + z * 16]);
				GL11.glVertex3f(1.0f + (2.0f * x) + (2.0f * 16 * this.x), -1.0f + (2.0f * v0hmap[z + x * 16]), 1.0f + (2.0f * z) + (2.0f * 16 * this.z));
				GL11.glVertex3f(-1.0f + (2.0f * x) + (2.0f * 16 * this.x), -1.0f + (2.0f * v1hmap[z + x * 16]), 1.0f + (2.0f * z) + (2.0f * 16 * this.z));
				GL11.glVertex3f(-1.0f + (2.0f * x) + (2.0f * 16 * this.x), -1.0f + (2.0f * v2hmap[z + x * 16]), -1.0f + (2.0f * z) + (2.0f * 16 * this.z));
				GL11.glVertex3f(1.0f + (2.0f * x) + (2.0f * 16 * this.x), -1.0f + (2.0f * v3hmap[z + x * 16]), -1.0f + (2.0f * z) + (2.0f * 16 * this.z));
			}
		}
	}

	public void update() {
	}

	public boolean checkIfChunkExists() {
		File f0 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + z + ".json");
		if (!f0.exists())
			return false;
		else
			return true;
	}

	public void loadChunkColorMap() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + z + ".json"));
			JSONObject jsonObject = (JSONObject) obj;
			String tmap = (String) jsonObject.get("tmap");
			String[] tmapstr = tmap.split("-");
			for (int i = 0; i < tmapstr.length; i++) {
				String[] colours = tmapstr[i].split(",");
				cmapRed[i] = (float) (Integer.parseInt(colours[0]) / 255.0);
				cmapGreen[i] = (float) (Integer.parseInt(colours[1]) / 255.0);
				cmapBlue[i] = (float) (Integer.parseInt(colours[2]) / 255.0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void loadHeightMap() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + z + ".json"));
			JSONObject jsonObject = (JSONObject) obj;
			String v0hmapr = (String) jsonObject.get("v0hmap");
			String v1hmapr = (String) jsonObject.get("v1hmap");
			String v2hmapr = (String) jsonObject.get("v2hmap");
			String v3hmapr = (String) jsonObject.get("v3hmap");
			String[] v0 = v0hmapr.split("-");
			String[] v1 = v1hmapr.split("-");
			String[] v2 = v2hmapr.split("-");
			String[] v3 = v3hmapr.split("-");
			for (int i = 0; i < v0.length; i++) {
				v0hmap[i] = Byte.parseByte(v0[i]);
				v1hmap[i] = Byte.parseByte(v1[i]);
				v2hmap[i] = Byte.parseByte(v2[i]);
				v3hmap[i] = Byte.parseByte(v3[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
