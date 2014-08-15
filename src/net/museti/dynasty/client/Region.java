package net.museti.dynasty.client;

import java.io.File;

public class Region {

	public int x;
	public int z;
	private Chunk[] region;
	public boolean realChunk = false;

	public Region(int x, int z) {
		this.x = x;
		this.z = z;
		realChunk = checkIfChunkExists();
		if (realChunk) {
			region = new Chunk[4];
			loadChunks();
		}
	}

	public void loadChunks() {
		Chunk c0 = new Chunk(x * 2, z * 2);
		Chunk c1 = new Chunk(x * 2 + 1, z * 2);
		Chunk c2 = new Chunk(x * 2, z * 2 + 1);
		Chunk c3 = new Chunk(x * 2 + 1, z * 2 + 1);
		region[0] = c0;
		region[1] = c1;
		region[2] = c2;
		region[3] = c3;
	}

	public boolean checkIfChunkExists() {
		File f0 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + z + ".json");
		File f1 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + (x + 1) + "," + z + ".json");
		File f2 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + x + "," + (z + 1) + ".json");
		File f3 = new File(System.getProperty("user.home") + "/AppData/Roaming/.mucache/" + Config.gTitle.toLowerCase() + "/chunks/chunk" + (x + 1) + "," + (z + 1) + ".json");
		if (!f0.exists())
			return false;
		if (!f1.exists())
			return false;
		if (!f2.exists())
			return false;
		if (!f3.exists())
			return false;
		return true;
	}

	public void renderChunks() {
		for (int i = 0; i < region.length; i++) {
			region[i].render();
		}
	}

	public void updateChunks() {
	}
}
